package com.test.interceptor;

import com.test.Template.ExcelTemplate;
import com.test.annotation.Excel;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @Author Stephen Hu
 * @Description //TODO 报表注解生成拦截器
 * @Date 18:01 2018/8/13
 **/
public class ExcelHandlerInterceptor implements HandlerInterceptor {


    /**
     * @return boolean
     * @Author Stephen Hu
     * @Description //TODO Controller处理之前
     * @Date 18:21 2018/8/13
     * @Param [httpServletRequest, httpServletResponse, o]
     **/
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        return true;
    }

    /**
     * @return void
     * @Author Stephen Hu
     * @Description //TODO Controller处理之后，视图返回之前
     * @Date 18:21 2018/8/13
     * @Param [httpServletRequest, httpServletResponse, o, modelAndView]
     **/
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        if (o instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) o;
            //含有Excle注解
            Excel excleAnnotation;
            if ((excleAnnotation = getExcleAnnotation(handlerMethod)) != null) {
                final String reportName = excleAnnotation.name();
                final List<String> cloumName = Arrays.asList(excleAnnotation.columns());
                final List<String> dataClounmName = Arrays.asList(excleAnnotation.sqlColumns());
                Map<String, Object> model = modelAndView.getModel();
                Object obj = model.get("excle_type_mode_name");
                List list = null;
                if (obj instanceof List) {
                    list = (List) obj;
                }
                final List data = list;
                new ExcelTemplate() {
                    @Override
                    public String getReportName() {
                        return reportName;
                    }

                    @Override
                    public List<String> getCloumName() {
                        return cloumName;
                    }

                    @Override
                    public List getReportData() {
                        return data;
                    }

                    @Override
                    public List<String> getDataCloumName() {
                        return dataClounmName;
                    }
                }.createReport(httpServletResponse);
                modelAndView.clear();
            }
        }
    }

    /**
     * @return boolean
     * @Author Stephen Hu
     * @Description //TODO 是否有excle注解
     * @Date 11:00 2018/8/14
     * @Param [handlerMethod]
     **/
    private Excel getExcleAnnotation(HandlerMethod handlerMethod) {
        Method method = handlerMethod.getMethod();
        Excel annotation = method.getAnnotation(Excel.class);
        return annotation;
    }


    /**
     * @return void
     * @Author Stephen Hu
     * @Description //TODO 视图返回之后
     * @Date 18:21 2018/8/13
     * @Param [httpServletRequest, httpServletResponse, o, e]
     **/
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }


}
