package com.example.train.config;

import com.example.train.security.Handler.LogoutSucessHandler;
import com.example.train.security.Handler.MyAuthenticationFailureHandler;
import com.example.train.security.Handler.MyAuthenticationSuccessHandler;
import com.example.train.security.Handler.UserAuthAccessDeniedHandler;
import com.example.train.security.filter.JWTAuthenticationTokenFilter;
import com.example.train.security.filter.TokenFilter;
import com.example.train.security.service.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailService userDetailService;

    @Autowired
    private MyAuthenticationFailureHandler authenticationFailureHandler;

    @Autowired
    private MyAuthenticationSuccessHandler authenticationSucessHandler;

    @Autowired
    private LogoutSucessHandler logoutSucessHandler;

    @Autowired
    private UserAuthAccessDeniedHandler userAuthAccessDeniedHandler ;

//    @Autowired
//    private TokenFilter tokenFilter;


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .authorizeRequests()
                .antMatchers("/login", "/logout","/*/shows","/*/show",
                        "/*/detail","/*/org","/ide/**","/*/load","/and/**",
                        "/rec/**","/file/**","/swiper/**"
                        ).permitAll()
                .antMatchers("/*/delete","/*/update", "/*/upload",
                        "/*/add","/*/getId","/pros/*").hasRole("admin")
//                .antMatchers("/**").hasRole("admin")

                .anyRequest().authenticated()
                .and()
                .exceptionHandling().accessDeniedHandler(userAuthAccessDeniedHandler)
                .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .successHandler(authenticationSucessHandler)
                .failureHandler(authenticationFailureHandler)
                .and()
                .logout()
                .logoutUrl("/logout")
                .deleteCookies("JSESSIONID")
                .logoutSuccessHandler(logoutSucessHandler)
                .and()
                // ????????????
                .cors()
                .and()
                // ??????????????????????????????
                .csrf().disable();

        // ??????Token?????????session
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        // ????????????
        http.headers().cacheControl();

        http.addFilter(new JWTAuthenticationTokenFilter(authenticationManager()));

//        http.addFilterBefore(
////                tokenFilter,
//                null,
//                JWTAuthenticationTokenFilter.class
//        );

    }



    @Bean
    //????????????????????????????????????????????????PasswordEncoder ???encode???????????????????????????/???????????????
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // ??????????????????userDetailsService
        auth.userDetailsService(userDetailService)
                //????????????????????? PasswordEncoder ??????
                .passwordEncoder(passwordEncoder());
    }

//    @Bean
//    public DefaultWebSecurityExpressionHandler userSecurityExpressionHandler(){
//        DefaultWebSecurityExpressionHandler handler = new DefaultWebSecurityExpressionHandler();
//        handler.setPermissionEvaluator(new PermissionFilter());
//        return handler;
//    }

}
