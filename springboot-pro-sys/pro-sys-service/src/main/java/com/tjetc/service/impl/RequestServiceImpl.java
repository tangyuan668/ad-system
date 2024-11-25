package com.tjetc.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tjetc.common.JsonResult;
import com.tjetc.dao.CustomerMapper;
import com.tjetc.dao.RequestMapper;
import com.tjetc.entity.Admin;
import com.tjetc.entity.Channel;
import com.tjetc.entity.Request;
import com.tjetc.service.RequestService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
@Service
public class RequestServiceImpl implements RequestService {
    @Value("${token.expiration}")
    private int tokeRxpiration;
    @Autowired
    private RequestMapper requestMapper;
    LocalDateTime now=LocalDateTime.now();
    @Override
    public JsonResult findRequestPage(String title, Integer pageNo, Integer pageSize,int cuid,String status) {
        //设置页码和每页数量
        Page<Request> page = Page.of(pageNo, pageSize);
//把adminPage传入mapper查询中，adminPage就是分页结果
        Page<Request> requestPage =  requestMapper.selectRequestPageLikeTitle(page,title,cuid,status);
//convert方法转换Page中 records集合元素类型 Admin--->AdProducerView
        //new Function<Admin, Object>() {}表示创建一个实现Function接口的匿名类对象

        return JsonResult.success(requestPage);
    }

    @Override
    public JsonResult requestDeleteById(int id) {
        int rows= requestMapper.deleteById(id);
        if(rows>0){return JsonResult.success("删除成功");}
        else{ return JsonResult.fail("删除失败");}
    }

    @Override
    public JsonResult save(Request request) {
        //通过判断id值有无判断新增还是更新，id值无新增，id值有就是更新



    Request request1=null;
        LocalDateTime now= LocalDateTime.now();
        if(request.getId()==null||request.getId()<=0)
        {//新增

            //判断用户名不能重复
            Request existRequest = requestMapper.selectByTitle(request.getTitle());
            if(existRequest!=null)
            {return  JsonResult.fail("广告需求不可同名");}
            //新增


            request.setUpdateTime(now);
            request.setCreateTime(now);
            requestMapper.insert(request);
            return JsonResult.success("新增成功",null);
        }
        else{
            //更新
//根据id查询Admin信息
            request1 = requestMapper.selectById(request.getId());
            if(request1==null)
            {
                return JsonResult.fail("需求不存在，更新失败");
            }

            //要更新的字段
request.setUpdateTime(now);
            requestMapper.updateById(request);
            int rows=requestMapper.updateById(request);
//要判断更新影响行数
            if(rows>0)
            {
                return JsonResult.success("更新成功",null);
            }
            else{return JsonResult.fail("更新失败");}

        }

    }

    @Override
    public JsonResult findById(int id) {
        Request request=requestMapper.selectById(id);
        return JsonResult.success(request);
    }
}
