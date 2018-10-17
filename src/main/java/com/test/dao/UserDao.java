package com.test.dao;

import com.test.entity.UserVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 8/1/2018.
 */
@Repository
public interface UserDao {
    UserVo getUserById(@Param("userVo")UserVo userVo);
    int insertUser(@Param("userVo")UserVo userVo);
    int updateUser(@Param("userVo")UserVo userVo);
}
