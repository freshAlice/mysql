package com.test.Template;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by Administrator on 8/14/2018.
 */
public abstract class ExcelTemplate<T> {
    private static  final String defualtDateFormate = "yyyy-MM-dd HH:mm:ss";

    public void createReport(HttpServletResponse response){
        try {
            //获取报表名
            String reportName = getReportName();
            //获取列名
            List<String> cloumName = getCloumName();
            //获取报表数据
            List<T> reportData = getReportData();
            HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
            HSSFSheet sheet = hssfWorkbook.createSheet();
            //设置表头
            setReportHead(hssfWorkbook, sheet, cloumName);
            //填充数据
            setReportData(hssfWorkbook, sheet, reportData, cloumName);
            //返回报表
            String codedFileName = java.net.URLEncoder.encode(reportName, "UTF-8");
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-disposition", "attachment;filename=" + codedFileName + ".xlsx");
            OutputStream ouputStream = response.getOutputStream();
            hssfWorkbook.write(ouputStream);
            ouputStream.flush();
            ouputStream.close();
        }catch (Exception e){
            e.printStackTrace();
        }

    }



    private void setReportData(HSSFWorkbook hssfWorkbook,HSSFSheet sheet,List<T> reportData,List<String> cloumName){
        if(reportData.isEmpty()){
            return;
        }
        List<String> dataCloumName = getDataCloumName();
        //无序列指定
        if(dataCloumName==null||dataCloumName.isEmpty()){
            noOraderSetReportData(hssfWorkbook,sheet,reportData,cloumName);
        }else{//有序列指定
            oraderSetReportData(hssfWorkbook,sheet,reportData,cloumName,dataCloumName);
        }
    }

    private void oraderSetReportData(HSSFWorkbook hssfWorkbook,HSSFSheet sheet,List<T> reportData,List<String> cloumName,List<String> dataCloumName){
        //行循环
        for (int i = 0; i < reportData.size(); i++) {
            HSSFRow row = sheet.createRow(i + 1);
            //列循环
            T data = reportData.get(i);
            for (int j = 0; j <cloumName.size();j++ ) {
                Object value = getGetMethodValue(data, dataCloumName.get(j));
                if (value==null){
                    continue;
                }
                //如果为Date类型则转换为字符串
                if("Date".equals(value.getClass().getSimpleName())){
                    value = Date2String(value);
                }
                HSSFCell cell = row.createCell(j);
                cell.setCellValue(value.toString());
                //设置列的样式
                cell.setCellStyle(getCellStyle(hssfWorkbook));
                //设置列的宽度
                sheet.setColumnWidth(j, value.toString().getBytes().length*2*256);
            }
        }
    }



    private void noOraderSetReportData(HSSFWorkbook hssfWorkbook,HSSFSheet sheet,List<T> reportData,List<String> cloumName){
        Class<?> aClass1 = reportData.get(0).getClass();
        //获取类中所有的属性
        Field[] declaredFields = aClass1.getDeclaredFields();
        //行循环
        for (int i = 0; i < reportData.size(); i++) {
            HSSFRow row = sheet.createRow(i + 1);
            //列循环
            T data = reportData.get(i);
            for (int j = 0,k=0; j <cloumName.size();k++ ) {
                Object value = getGetMethodValue(data, declaredFields[k].getName());
                if (value==null){
                    continue;
                }
                //如果为Date类型则转换为字符串
                if("Date".equals(declaredFields[k].getType().getSimpleName())){
                    value = Date2String(value);
                }
                HSSFCell cell = row.createCell(j);
                cell.setCellValue(value.toString());
                //设置列的样式
                cell.setCellStyle(getCellStyle(hssfWorkbook));
                //设置列的宽度
                sheet.setColumnWidth(j, 5000);
                j++;
            }
        }
    }


    private String Date2String(Object value){
        String dateFormate = getDateFormate();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormate);
        return simpleDateFormat.format(value);
    }



    private Object getGetMethodValue(T data,String methodName){
        if("serialVersionUID".equals(methodName)){
            return null;
        }
        Object value = null;
        //获取对象get方法
        Class<?> aClass = data.getClass();
        methodName = ("get"+methodName.substring(0,1).toUpperCase()+methodName.substring(1));
        try {
            Method method = aClass.getMethod(methodName);
            value = method.invoke(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }


    /**
     * @Author Stephen Hu
     * @Description //TODO 设置报表头
     * @Date 15:52 2018/7/4
     * @Param [hssfWorkbook, sheet, cloumName]
     * @return void
     **/
    private void setReportHead(HSSFWorkbook hssfWorkbook,HSSFSheet sheet,List<String> cloumName){
        HSSFRow row = sheet.createRow(0);
        for (int i = 0; i < cloumName.size(); i++) {
            //创建列
            HSSFCell cell = row.createCell(i);
            cell.setCellValue(cloumName.get(i));
            //设置列的样式
            cell.setCellStyle(getCellStyle(hssfWorkbook));
            //设置列的宽度
            sheet.setColumnWidth(i, cloumName.get(i).getBytes().length*2*256);
        }
    }




    /**
     * @Author Stephen Hu
     * @Description //TODO 获取样式
     * @Date 15:52 2018/7/4
     * @Param [hssfWorkbook]
     * @return org.apache.poi.hssf.usermodel.HSSFCellStyle
     **/
    private HSSFCellStyle getCellStyle(HSSFWorkbook hssfWorkbook){
        //创建设置属性对象
        HSSFCellStyle CellStyle = hssfWorkbook.createCellStyle();
        //设置居中
        CellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        CellStyle.setAlignment(HorizontalAlignment.RIGHT);
        CellStyle.setBorderBottom(BorderStyle.THIN); //下边框
        CellStyle.setBorderLeft(BorderStyle.THIN);//左边框
        CellStyle.setBorderTop(BorderStyle.THIN);//上边框
        CellStyle.setBorderRight(BorderStyle.THIN);//右边框
        //设置字体
        HSSFFont font = hssfWorkbook.createFont();
        font.setFontName("微软雅黑");
        font.setFontHeightInPoints((short) 12);//设置字体大小
        CellStyle.setFont(font);
        return CellStyle;

    }

    public abstract String getReportName();

    public abstract List<String> getCloumName();

    public abstract  List<T> getReportData();

    /**
     * @Author Stephen Hu
     * @Description //TODO
     * 1.此方法不重写 默认按照对象的属性顺序对应表头
     * 例：
     * 表头 A B C
     * 类属性 private int id; private String name;private int age;
     * 则对应： A       B           C
     *          id值    name值      age值
     * 2.此方法重写  按照此方法指定的类属性顺序对应表头
     * 例：
     * 表头 A B C
     * 类属性 private int id; private String name;private int age;
     * 重写方法返回的list ["name","id","age"]
     * 则对应：A        B       C
     *        name值    id值    age值
     * @Date 17:47 2018/7/4
     * @Param []
     * @return java.util.List<java.lang.String>
     **/
    public List<String> getDataCloumName(){return null;};

    /**
     * @Author Stephen Hu
     * @Description //TODO 如有date类型需要转成相应的格式 默认格式yyyy-MM-dd
     * @Date 18:13 2018/7/4
     * @Param [dateFormate]
     * @return java.lang.String
     **/
    public String getDateFormate(){
        return defualtDateFormate;
    }

}
