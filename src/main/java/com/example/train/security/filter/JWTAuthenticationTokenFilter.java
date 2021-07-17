package com.example.train.security.filter;

import com.example.train.OnlineTrainingApplication;
import com.example.train.security.pojo.UserDetail;
import com.example.train.utils.JWTTokenUtil;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.core.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
/**
 * jwt拦截器
 */
public class JWTAuthenticationTokenFilter extends BasicAuthenticationFilter {

    public JWTAuthenticationTokenFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws IOException, ServletException {
        // 获取请求头中JWT的Token
        String token = request.getHeader("token");
        if (null!=token) {
            try {
                // 截取JWT前缀
                Integer id = Integer.parseInt(JWTTokenUtil.getMemberIdByJwtToken(token));

                List<String> list = JWTTokenUtil.getAuthoritiesByJWTToken(token);
                List<SimpleGrantedAuthority> authorities = new ArrayList<>();
                for (String s : list) {
                    authorities.add(new SimpleGrantedAuthority(s));
                }
                UserDetail userDetail = new UserDetail();
                userDetail.setId(id);
                userDetail.setAuthorities(authorities);

                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(userDetail, userDetail.getId(), authorities);//, authorities

                SecurityContextHolder.getContext().setAuthentication(authentication);
            } catch (ExpiredJwtException e){
                log.info("Token过期");
            } catch (Exception e) {
                log.info("Token无效");
            }
        }
        filterChain.doFilter(request, response);
        return;
    }
}

