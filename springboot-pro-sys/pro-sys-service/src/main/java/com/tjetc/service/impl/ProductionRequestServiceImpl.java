package com.tjetc.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tjetc.common.JsonResult;
import com.tjetc.dao.AdProducerMapper;
import com.tjetc.dao.ProductionRequestMapper;
import com.tjetc.dao.ProposalMapper;
import com.tjetc.dao.RequestMapper;
import com.tjetc.entity.*;
import com.tjetc.entity.ProductionRequest;
import com.tjetc.service.ProductionRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
@Service
public class ProductionRequestServiceImpl implements ProductionRequestService {
    @Value("${token.expiration}")
    private int tokeRxpiration;
    @Autowired
    private ProductionRequestMapper productionRequestMapper;
    @Autowired
    private RequestMapper requestMapper;
    @Autowired
    private ProposalMapper proposalMapper;
    @Autowired
    private AdProducerMapper adProducerMapper;

    LocalDateTime now=LocalDateTime.now();

    @Override
    public JsonResult findProductionRequestPage(int prid, Integer pageNo, Integer pageSize, String name,int pdid) {
        //设置页码和每页数量
        Page<ProductionRequest> page = Page.of(pageNo, pageSize);
//把adminPage传入mapper查询中，adminPage就是分页结果
        Page<ProductionRequest> productionRequestPage =  productionRequestMapper.selectProductionRequestByProductionRequestIdAndNameAndProducerId(page,prid,name,pdid);
//convert方法转换Page中 records集合元素类型 Admin--->AdProducerView
        //new Function<Admin, Object>() {}表示创建一个实现Function接口的匿名类对象

        return JsonResult.success(productionRequestPage);
    }

    @Override
    public JsonResult productionRequestDeleteById(int id) {
        int rows= productionRequestMapper.deleteById(id);
        if(rows>0){return JsonResult.success("删除成功");}
        else{ return JsonResult.fail("删除失败");}
    }

    @Override
    public JsonResult save(ProductionRequest productionRequest) {
        //通过判断id值有无判断新增还是更新，id值无新增，id值有就是更新

        ProductionRequest productionRequest1=null;
        LocalDateTime now= LocalDateTime.now();
        //更新
//根据id查询Admin信息
        if(productionRequest.getId()==null||productionRequest.getId()<=0)
        {

            Proposal existProposal= proposalMapper.selectById(productionRequest.getProposalId());
            if(existProposal==null)
            {return  JsonResult.fail("没有对应的提案");}
            //新增
            AdProducer existAdProducer= adProducerMapper.selectById(productionRequest.getAdproducerId());
            if(existAdProducer==null)
            {return  JsonResult.fail("没有对应的职工号");}

            productionRequest.setUpdateTime(now);
            productionRequest.setCreateTime(now);
            productionRequestMapper.insert(productionRequest);
            return JsonResult.success("新增成功",null);


        }
        else {
            productionRequest1 = productionRequestMapper.selectById(productionRequest.getId());

            Proposal existProposal= proposalMapper.selectById(productionRequest.getProposalId());
            if(existProposal==null)
            {return  JsonResult.fail("没有对应的提案");}
            //新增
            AdProducer existAdProducer= adProducerMapper.selectById(productionRequest.getAdproducerId());
            if(existAdProducer==null)
            {return  JsonResult.fail("没有对应的职工号");}


            //要更新的字段
            productionRequest.setUpdateTime(now);
            productionRequestMapper.updateById(productionRequest);
            int rows = productionRequestMapper.updateById(productionRequest);
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
        ProductionRequest productionRequest=productionRequestMapper.selectById(id);
        return JsonResult.success(productionRequest);
    }
}
