package com.tjetc.service.impl;

import com.tjetc.common.JsonResult;
import com.tjetc.dao.EmailMapper;
import com.tjetc.dao.ProposalMapper;
import com.tjetc.dao.RequestMapper;
import com.tjetc.entity.Email;
import com.tjetc.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class EmailServiceImpl implements EmailService {
    @Value("${token.expiration}")
    private int tokeRxpiration;
    @Autowired
    private EmailMapper emailMapper;
    @Autowired
    private RequestMapper requestMapper;
    LocalDateTime now=LocalDateTime.now();

    @Override
    public JsonResult save(Email email) {
        email.setUpdateTime(now);
        email.setCreateTime(now);
        emailMapper.insert(email);
        return JsonResult.success("新增成功",null);
    }

    @Override
    public JsonResult emailDeleteById(int id) {
        int rows= emailMapper.deleteById(id);
        if(rows>0){return JsonResult.success("删除成功");}
        else{ return JsonResult.fail("删除失败");}

    }

    @Override
    public JsonResult getAllEmails(Integer cuid) {
        return JsonResult.success(emailMapper.getAllEmail(cuid));
    }

    @Override
    public JsonResult updateEmail(Email email) {
       emailMapper.updateById(email);
       return JsonResult.success("更新成功");
    }

    @Override
    public JsonResult getNums(Integer cuid) {
        emailMapper.getNum(cuid);
        return JsonResult.success("成功获取",emailMapper.getNum(cuid));
    }
}
