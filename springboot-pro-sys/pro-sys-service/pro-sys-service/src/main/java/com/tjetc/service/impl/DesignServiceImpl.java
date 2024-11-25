package com.tjetc.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tjetc.common.JsonResult;
import com.tjetc.dao.DesignMapper;
import com.tjetc.entity.Design;
import com.tjetc.entity.Design;
import com.tjetc.service.DesignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class DesignServiceImpl implements DesignService {

    @Value("${token.expiration}")
    private int tokeRxpiration;
    @Autowired
    private DesignMapper designMapper;
    LocalDateTime now=LocalDateTime.now();

    @Override
    public JsonResult findDesignPage(int reid, Integer pageNo, Integer pageSize,String status) {
        //设置页码和每页数量
        Page<Design> page = Page.of(pageNo, pageSize);
//把adminPage传入mapper查询中，adminPage就是分页结果
        Page<Design> designPage =  designMapper.selectDesignByRequestIdAndStatus(page,reid,status);
//convert方法转换Page中 records集合元素类型 Admin--->AdProducerView
        //new Function<Admin, Object>() {}表示创建一个实现Function接口的匿名类对象

        return JsonResult.success(designPage);
    }

    @Override
    public JsonResult designDeleteById(int id) {
        int rows= designMapper.deleteById(id);
        if(rows>0){return JsonResult.success("删除成功");}
        else{ return JsonResult.fail("删除失败");}
    }

    @Override
    public JsonResult save(Design design) {
        //通过判断id值有无判断新增还是更新，id值无新增，id值有就是更新
        Design design1=null;
        LocalDateTime now= LocalDateTime.now();
        //更新
//根据id查询Admin信息
        design1 = designMapper.selectById(design.getId());
        //要更新的字段
        design.setUpdateTime(now);
        designMapper.updateById(design);
        int rows=designMapper.updateById(design);
//要判断更新影响行数
        if(rows>0)
        {
            return JsonResult.success("更新成功",null);
        }
        else{return JsonResult.fail("更新失败");}

    }
    @Override
    public JsonResult findById(int id) {
        Design design=designMapper.selectById(id);
        return JsonResult.success(design);
    }
}
