package com.example.train.dao;

import com.example.train.pojo.User;
import org.springframework.stereotype.Repository;

@Repository
/**
 * 前端客户信息dao层
 */
public interface UserDao {

    User selectById(Integer id);
}
