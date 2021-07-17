package com.example.train.security.Handler;

import com.example.train.security.pojo.UserDetail;
import com.example.train.utils.JSONUtil;
import com.example.train.utils.JWTTokenUtil;
import com.example.train.utils.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * @Description 登录成功处理类
 */
@Slf4j
@Component
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

//    @Autowired
//    private RedisUtils redisUtils;

    /**
     * 登录成功返回结果
     * @param request
     * @param response
     * @param authentication
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication)
            throws IOException, ServletException {

        // 组装JWT
        UserDetail securityUser = (UserDetail) authentication.getPrincipal();
        System.out.println(securityUser);
        List<SimpleGrantedAuthority> authorities =
                (List<SimpleGrantedAuthority>) securityUser.getAuthorities();
        List<String> list = new ArrayList<>();
        for (SimpleGrantedAuthority authority : authorities) {
            list.add(authority.getAuthority());
        }
        //获取token
        String token = JWTTokenUtil.getJwtToken(
                String.valueOf(securityUser.getId()),
                securityUser.getUsername(),
                list);
        //获取当前时间
        Date date = new Date(System.currentTimeMillis());
        //将token和时间写入到redis
//        redisUtils.set(token,String.valueOf(date));
        String res = null;
        if (token != null && token != "")
            res = JSONUtil.JSONSuccessToString(token);
        else
            res = JSONUtil.JSONFailedToString("未能获得");
//        String res = JSONUtil.JSONToString(true,token,"","ERROR");
        ResponseUtil.out(response, res);
    }
}
