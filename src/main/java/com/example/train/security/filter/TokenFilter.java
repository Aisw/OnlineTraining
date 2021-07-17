package com.example.train.security.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@Component
public class TokenFilter implements Filter {

//    @Autowired
//    private RedisUtils redisUtils;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        // 获取请求头中JWT的Token
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;


        String url = request.getServletPath();

        url = url.substring(1);
        int index = url.indexOf("/");
        if (index > 0) {

            url = url.substring(0, index);
        }
        if (url.equals("src")){
            System.out.println(url+"---");
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }
//        System.out.println(url);

        String token = request.getHeader("token");
        System.out.println(token);
//        if (token == null || token == "") {
//            response.sendError(404, "token不存在");
//        } else{
//            String time = redisUtils.get(token);
//            if (time == "" || time == null){
//                response.sendError(404,"token不存在");
////                ResponseUtil.out((HttpServletResponse) servletResponse,"请重新登录");
//            }
//            else {
////                System.out.println(time+" time");
//                if (TimeUtil.isTimeout(time))
//                    filterChain.doFilter(servletRequest,servletResponse);
//                else
//                    ResponseUtil.out((HttpServletResponse) servletResponse,"token逾期");
//            }
//        }
    }

}
