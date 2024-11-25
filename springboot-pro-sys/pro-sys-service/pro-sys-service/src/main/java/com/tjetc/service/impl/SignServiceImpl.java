package com.tjetc.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tjetc.common.JsonResult;
import com.tjetc.dao.SignMapper;
import com.tjetc.entity.Sign;
import com.tjetc.entity.Sign;
import com.tjetc.service.SignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class SignServiceImpl implements SignService {

    @Value("${token.expiration}")
    private int tokeRxpiration;
    @Autowired
    private SignMapper signMapper;
    LocalDateTime now=LocalDateTime.now();

    @Override
    public JsonResult findSignPage(int prid, Integer pageNo, Integer pageSize,String name) {
        //设置页码和每页数量
        Page<Sign> page = Page.of(pageNo, pageSize);
//把adminPage传入mapper查询中，adminPage就是分页结果
        Page<Sign> signPage =  signMapper.selectSignPageLikeNameAndProposalId(page,name,prid);
//convert方法转换Page中 records集合元素类型 Admin--->AdProducerView
        //new Function<Admin, Object>() {}表示创建一个实现Function接口的匿名类对象

        return JsonResult.success(signPage);
    }

    @Override
    public JsonResult signDeleteById(int id) {
        int rows= signMapper.deleteById(id);
        if(rows>0){return JsonResult.success("删除成功");}
        else{ return JsonResult.fail("删除失败");}
    }

    @Override
    public JsonResult save(Sign sign) {
        //通过判断id值有无判断新增还是更新，id值无新增，id值有就是更新
        Sign sign1=null;
        LocalDateTime now= LocalDateTime.now();
        if(sign.getId()==null||sign.getId()<=0)
        {
            sign.setUpdateTime(now);
            sign.setCreateTime(now);
            signMapper.insert(sign);
            return JsonResult.success("新增成功",null);

        }


     else {
            //更新
//根据id查询Admin信息
            sign1 = signMapper.selectById(sign.getId());
            //要更新的字段
            sign.setUpdateTime(now);
            signMapper.updateById(sign);
            int rows = signMapper.updateById(sign);
//要判断更新影响行数
            if (rows > 0) {
                return JsonResult.success("更新成功", null);
            } else {
                return JsonResult.fail("更新失败");
            }
        }
    }
    @Override
    public JsonResult findById(int id) {
        Sign sign=signMapper.selectById(id);
        return JsonResult.success(sign);
    }
}