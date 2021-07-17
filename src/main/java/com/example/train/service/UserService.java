package com.example.train.service;

import com.example.train.dao.UserDao;
import com.example.train.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户查询service层
 */
@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    /**
     * 通过id查询名称
     * @param id
     * @return
     */
    public User findById(Integer id){
        return userDao.selectById(id);
    }
}
