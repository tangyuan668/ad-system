package com.tjetc.config;

import com.tjetc.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//@Configuration
public class MyWebMvcConfig implements WebMvcConfigurer {
    //@Autowired
    private LoginInterceptor loginInterceptor;
    //台南佳自定义的LoginInterceptor
    String[] excludePaths={"/**/login","/**/*.js","/image/**","/error","/register"};
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //对所有的的请求路径进行拦截，执行loginInterceptor的preHandle方法
        registry.addInterceptor(loginInterceptor).addPathPatterns("/**")
                //排除掉哪些url不被拦截
                .excludePathPatterns(excludePaths);
    }
}
