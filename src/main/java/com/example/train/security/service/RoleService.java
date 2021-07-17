package com.example.train.security.service;

import com.example.train.security.dao.RoleDao;
import com.example.train.security.pojo.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    @Autowired
    private RoleDao roleDao;

    /**
     * 通过账户获取登录信息
     * @param account
     * @return
     */
    public List<Role> findByAccount(String account){
        return
                roleDao.selectByAccount(account);
    }

    /**
     * 为用户添加权限
     * @param role
     * @return
     */
    public boolean addRole(Role role){
        return roleDao.insertRole(role);
    }
}
