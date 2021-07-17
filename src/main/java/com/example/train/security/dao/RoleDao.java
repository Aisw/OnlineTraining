package com.example.train.security.dao;

import com.example.train.security.pojo.Role;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
/**
 * 角色
 */
public interface RoleDao {

    /**
     * 根据账户查询角色
     * @param account
     * @return
     */
    List<Role> selectByAccount(String account);

    /**
     * 为用户插入角色
     * @param role
     * @return
     */
    boolean insertRole(Role role);
}
