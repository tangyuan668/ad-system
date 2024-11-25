package com.tjetc.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tjetc.common.JsonResult;
import com.tjetc.common.JwtTokenUtil;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {
  @Autowired
    private ObjectMapper objectMapper;

    //执行时机，在执行controller方法之前执行preHandle，
    // 所以可以校验用户时候登录，已经登录过了，放行，为登录就返回未登录，防止访问controller的方法

    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
//       //获取session
//        HttpSession session = request.getSession();
//        Object username = session.getAttribute("username");
//        if(username==null)
//        {//未登录，返回json提示未登录
//            String result="{\"code\":2000,\"msg\":\"未登录或登录已过期，请重新登录\"}";
//            //解决中文乱码
//            response.setHeader("Content-Type","application/json;charset=utf-8");
////把信息输出到页面
//            response.getWriter().write(result);
//            //禁止执行后面controller方法
//          return   false;
//
//        }
        //已经登录过了，放行
        //校验token
        //过去token(请求头中获取token ,定义key是token ,value是token的值)
        String tokenValue = request.getHeader("token");
        if (tokenValue != null && !"".equals(tokenValue.trim())) {
            try {


                Claims claims = JwtTokenUtil.parseJwt(tokenValue);
                return true;
            } catch (Exception e) {
                //返回json 未登陆或者登陆过期
                log.error("解析token异常，{}", e.getMessage());
            }


        }
        //解决中文乱码
     response.setHeader("Content-Type","application/json;charset=utf-8");
        //返回json 未登录或者登录过期
        JsonResult result = JsonResult.fail(-1, "未登录或者登录过期");
        //使用jackson的ObjectMapper对象把JsonResult对象转换成json字符串
        String json=objectMapper.writeValueAsString(result);
        //把json字符串输出到前端
        response.getWriter().write(json);


        return false;
    }


}