package com.tjetc.controller;

import com.tjetc.common.JsonResult;
import com.tjetc.common.JwtTokenUtil;
import com.tjetc.dao.AdProducerMapper;
import com.tjetc.service.AdProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
//@CrossOrigin表示后端controller的方法允许跨域，不安全，不推荐
@RestController
@RequestMapping("adproducer")
//@CrossOrigin
public class LoginController {
@Autowired
private AdProducerService adproducerService;
    @RequestMapping("customer/login")
    public JsonResult login(@RequestParam("username") String username,
                            @RequestParam("password") String password, HttpSession session) {
return adproducerService.login(username, password);

    }
}