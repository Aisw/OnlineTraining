package com.example.train.security.Handler;

import com.alibaba.fastjson.JSONObject;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * 登录退出处理类
 */
@Component
public class LogoutSucessHandler implements LogoutSuccessHandler {

//    @Autowired
//    private RedisUtils redisUtils;

    @Override
    public void onLogoutSuccess(HttpServletRequest request,
                                HttpServletResponse response,
                                Authentication authentication) throws IOException, ServletException {

        // 获取请求头中JWT的Token
        String token = request.getHeader("token");
//        String time = redisUtils.get(token);
//        if (time != null)
//            redisUtils.delete(token);

        PrintWriter printWriter = response.getWriter();
        response.setHeader("Content-Type", "application/json;charset=utf-8");
        Map<String, String> msgMap = new HashMap<>();
        msgMap.put("result", "0");
        msgMap.put("msg", "退出成功");
        printWriter.write(new JSONObject().toJSONString(msgMap));
        printWriter.flush();
        printWriter.close();
    }
}
