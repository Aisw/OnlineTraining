package com.example.train.security.service;
import com.example.train.security.dao.RoleDao;
import com.example.train.security.dao.UserDetailDao;
import com.example.train.security.pojo.Role;
import com.example.train.security.pojo.UserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserDetailService implements UserDetailsService {

    @Autowired
    private UserDetailDao userDetailDao;

    @Autowired
    private RoleDao roleDao;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserDetail userDetail = userDetailDao.selectByUserName(s);
        System.out.println(userDetail);
        List<Role> roleList = roleDao.selectByAccount(userDetail.getAccount());
        List<GrantedAuthority> grantedAuthorities = roleList.stream().map(r -> {
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_" + r.getRole());
            return grantedAuthority;
        }).collect(Collectors.toList());
        userDetail.setAuthorities(grantedAuthorities);
        System.out.println(userDetail);
        return userDetail;
    }

    public UserDetail selectById(Integer id){
        UserDetail userDetail = userDetailDao.selectById(id);
        return userDetail;
    }

}
