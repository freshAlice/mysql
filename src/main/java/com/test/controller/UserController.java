package com.test.controller;

import com.test.Template.ExcelTemplate;
import com.test.annotation.Excel;
import com.test.entity.UserVo;
import com.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.time.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 8/1/2018.
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    @Qualifier("userServiceImpl")
    private UserService userService;

    @RequestMapping(value = "/getUserById", method = RequestMethod.GET)
    public @ResponseBody
    UserVo queryUserList(UserVo userVo){
       return userService.getUserById(userVo);
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public @ResponseBody
    Map<String,Object> addUser(@RequestBody UserVo userVo){
        return userService.insertUser(userVo);
    }

    @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
    public @ResponseBody
    Map<String,Object> updateUser(@RequestBody UserVo userVo){
        return userService.updateUser(userVo);
    }

    @RequestMapping(value = "/testClone", method = RequestMethod.GET)
    public @ResponseBody Map<String, Object> test(){
        Map<String,Object> resultMap = new HashMap<>();
        UserVo userVo = new UserVo("Alice","F");
        UserVo userVo1 = null;
        try {
            userVo1 = (UserVo) userVo.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        resultMap.put("resultMsg",userVo==userVo1);
        resultMap.put("username",userVo.getUserName()==userVo1.getUserName());
        return resultMap;
    }

    @RequestMapping(value = "/testDate", method = RequestMethod.GET)
    public @ResponseBody Map<String, Object> testDate(){
        Map<String,Object> resMap = new HashMap<>();
        LocalDate localdate = LocalDate.now();
        resMap.put("LocalDate", localdate);
        resMap.put("LocalTime", LocalTime.now());
        resMap.put("LocalDateTime", LocalDateTime.now());
        resMap.put("ZonedDateTime", ZonedDateTime.now());
        LocalDate date = LocalDate.of(2018,8,7);
        resMap.put("localdate.equals(date)",localdate.equals(date));
        LocalDate birthOfDate = LocalDate.of(1992,12,29);
        MonthDay monthDay = MonthDay.of(birthOfDate.getMonth(),birthOfDate.getDayOfMonth());
        MonthDay currentMonthDay = MonthDay.from(localdate);
        if(currentMonthDay.equals(monthDay)){
            resMap.put("isBirthDay",true);
        }
        return resMap;
    }

    @RequestMapping(value = "/testExcel", method = RequestMethod.GET)
    @Excel(name = "testExport", columns = {"userId","userName","sex","phoneNo"}, sqlColumns = {"userId"})
    public @ResponseBody Map<String, Object> testExcel(UserVo userVo){
        Map<String,Object> resMap = new HashMap<>();
        UserVo returnVo= userService.getUserById(userVo);
        List<UserVo> userVoList = new ArrayList<>();
        userVoList.add(returnVo);
        ModelAndView modelView = new ModelAndView();
        resMap.put("list",userVoList);
        modelView.addObject("excle_type_mode_name",userVoList);
        return resMap;
    }

    @RequestMapping(value = "/testThread", method = RequestMethod.GET)
    public @ResponseBody Map<String, Object> testThread(UserVo userVo){
        Map<String,Object> resMap = new HashMap<>();


        return resMap;
    }

}
