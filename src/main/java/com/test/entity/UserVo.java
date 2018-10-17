package com.test.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 8/1/2018.
 */
public class UserVo implements Serializable,Cloneable{
    public UserVo(){}

    private Integer userId;
    private String userName;
    private String sex;
    private String phoneNo;

    public UserVo(String userName,String sex){
        this.userName = userName;
        this.sex = sex;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    @Override
    public String toString() {
        return "UserVo{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", sex='" + sex + '\'' +
                ", phoneNo='" + phoneNo + '\'' +
                '}';
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
