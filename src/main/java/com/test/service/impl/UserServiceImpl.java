package com.test.service.impl;

import com.test.dao.UserDao;
import com.test.entity.UserVo;
import com.test.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 8/1/2018.
 */
@Service(value = "userServiceImpl")
@EnableCaching
@CacheConfig(cacheNames="userCache")
@Transactional(propagation= Propagation.REQUIRED,readOnly=false,rollbackFor=Exception.class)
public class UserServiceImpl implements UserService{

    private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDao userDao;

    @Override
    @Cacheable(key="#p0")
    public UserVo getUserById(UserVo userVo) {
        logger.info("-----------UserServiceImpl.getUserById start--------------------");
        return userDao.getUserById(userVo);
    }

    @Override
    @CachePut(key="#p0.userId")
    public Map<String, Object> insertUser(UserVo userVo) {
        Map<String, Object> map = new HashMap<>();
        try{
            userDao.insertUser(userVo);
            map.put("resultMsg","success");
        }catch (Exception e){
            map.put("resultMsg","failed");
        }
        return map;
    }

    @Override
    @CachePut(key="#p0.userId")
    public Map<String, Object> updateUser(UserVo userVo) {
        Map<String, Object> map = new HashMap<>();
        try{
            userDao.updateUser(userVo);
            map.put("resultMsg","success");
        }catch (Exception e){
            map.put("resultMsg","failed");
        }
        return map;
    }

    @Override
    public Map<String, Object> testThread() {

        return null;
    }
}
