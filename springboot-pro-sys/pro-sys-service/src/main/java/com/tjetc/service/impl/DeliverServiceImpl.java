package com.tjetc.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tjetc.common.JsonResult;
import com.tjetc.dao.DeliverMapper;
import com.tjetc.dao.ProductionRequestMapper;
import com.tjetc.dao.RequestMapper;
import com.tjetc.entity.Deliver;
import com.tjetc.entity.ProductionRequest;
import com.tjetc.entity.Request;
import com.tjetc.service.DeliverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
@Service
public class DeliverServiceImpl implements DeliverService {

    @Value("${token.expiration}")
    private int tokeRxpiration;
    @Autowired
    private DeliverMapper deliverMapper;
    @Autowired
    private RequestMapper requestMapper;
    @Autowired
            private ProductionRequestMapper productionRequestMapper;
    LocalDateTime now=LocalDateTime.now();

    @Override
    public JsonResult findDeliverPage(int reid, Integer pageNo, Integer pageSize, String status,String name) {
        //设置页码和每页数量
        Page<Deliver> page = Page.of(pageNo, pageSize);
//把adminPage传入mapper查询中，adminPage就是分页结果
        Page<Deliver> deliverPage =  deliverMapper.selectDeliverByRequestIdAndStatusAndName(page,reid,status,name);
//convert方法转换Page中 records集合元素类型 Admin--->AdProducerView
        //new Function<Admin, Object>() {}表示创建一个实现Function接口的匿名类对象

        return JsonResult.success(deliverPage);
    }

    @Override
    public JsonResult deliverDeleteById(int id) {
        int rows= deliverMapper.deleteById(id);
        if(rows>0){return JsonResult.success("删除成功");}
        else{ return JsonResult.fail("删除失败");}
    }

    @Override
    public JsonResult save(Deliver deliver) {
        //通过判断id值有无判断新增还是更新，id值无新增，id值有就是更新

        Deliver deliver1=null;
        LocalDateTime now= LocalDateTime.now();
        //更新
//根据id查询Admin信息
        if(deliver.getId()==null||deliver.getId()<=0)
        {

            ProductionRequest existProductionRequest = productionRequestMapper.selectById(deliver.getRequestId());
            if(existProductionRequest==null)
            {return  JsonResult.fail("没有对应的制作需求");}
            //新增


            deliver.setUpdateTime(now);
            deliver.setCreateTime(now);
            deliverMapper.insert(deliver);
            return JsonResult.success("新增成功",null);


        }
        else {
            deliver1 = deliverMapper.selectById(deliver.getId());


            ProductionRequest existProductionRequest = productionRequestMapper.selectById(deliver.getRequestId());
            if(existProductionRequest==null)
            {return  JsonResult.fail("没有对应的广告制作需求");}


            //要更新的字段
            deliver.setUpdateTime(now);
            deliverMapper.updateById(deliver);
            int rows = deliverMapper.updateById(deliver);
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
        Deliver deliver=deliverMapper.selectById(id);
        return JsonResult.success(deliver);
    }










}
