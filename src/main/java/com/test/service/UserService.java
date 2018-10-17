package com.test.service;

import com.test.entity.UserVo;

import java.util.Map;

/**
 * Created by Administrator on 8/1/2018.
 */
public interface UserService {
    UserVo getUserById(UserVo userVo);
    Map<String, Object> insertUser(UserVo userVo);
    Map<String, Object> updateUser(UserVo userVo);
    Map<String, Object> testThread();
}
