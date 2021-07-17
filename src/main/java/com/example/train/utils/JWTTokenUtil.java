package com.example.train.utils;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * JWT工具类
 * @author zwq
 * @date 2020-04-04
 **/
@Slf4j
public class JWTTokenUtil {

    /**
     * token过期时间
     */
    public static final long EXPIRE = 1000 * 60 * 60 * 24;
    /**
     *秘钥
     */
    public static final String APP_SECRET = "ukc8BDbRigUDaY6pZFfWus2jZWLPHO";

    /**
     * 生成token字符串的方法
     * @return String token token令牌
     */
    public static String getJwtToken(String id, String name) {
        System.out.println(id + " " + name);
        JwtBuilder builder = Jwts.builder();
        // 设置头信息
        builder.setHeaderParam("typ", "JWT");
        builder.setHeaderParam("alg", "HS256");

        // 设置载荷
        builder.setSubject("user");
        builder.setIssuedAt(new Date());
        builder.setExpiration(new Date(System.currentTimeMillis() + EXPIRE));

        // 设置签名
        builder.claim("id", id);
        builder.claim("name", name);
        JwtBuilder jwtBuilder = builder.signWith(SignatureAlgorithm.HS256, APP_SECRET);
        String token = jwtBuilder.compact();

        return token;
    }

    public static String getJwtToken(String id, String name, List<String> authorities) {
        JwtBuilder builder = Jwts.builder();
        // 设置头信息
        builder.setHeaderParam("typ", "JWT");
        builder.setHeaderParam("alg", "HS256");

        // 设置载荷
        builder.setSubject("user");
        builder.setIssuedAt(new Date());
        builder.setExpiration(new Date(System.currentTimeMillis() + EXPIRE));

        // 设置签名
        builder.claim("id", id);
        builder.claim("name", name);
        builder.claim("authorities",authorities);
        String token = builder.signWith(SignatureAlgorithm.HS256, APP_SECRET).compact();

        return token;
    }

    public static String getMemberIdByJwtToken(String token) {
        if (StringUtils.isEmpty(token)) {
            return "";
        }
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(token);
        Claims claims = claimsJws.getBody();

        return (String) claims.get("id");
    }

    public static List<String> getAuthoritiesByJWTToken(String token){
        if (StringUtils.isEmpty(token)){
            return null;
        }
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(token);
        Claims claims = claimsJws.getBody();
        return (List<String>) claims.get("authorities");
    }

}
