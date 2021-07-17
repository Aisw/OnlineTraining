package com.example.train.security.dao;

import com.example.train.security.pojo.UserDetail;
import org.springframework.stereotype.Repository;

@Repository
/**
 * 用户
 */
public interface UserDetailDao {

    /**
     * 根据用户名查询用户
     * @param userName
     * @return
     */
    UserDetail selectByUserName(String userName);

    /**
     * 根据id查询用户
     * @param id
     * @return
     */
    UserDetail selectById(Integer id);
}
