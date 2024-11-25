package com.tjetc.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tjetc.common.JsonResult;
import com.tjetc.dao.ProposalMapper;
import com.tjetc.entity.Proposal;
import com.tjetc.entity.Proposal;
import com.tjetc.service.ProposalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.xml.ws.ServiceMode;
import java.time.LocalDateTime;
@Service
public class ProposalServiceImpl implements ProposalService {
    @Value("${token.expiration}")
    private int tokeRxpiration;
    @Autowired
    private ProposalMapper proposalMapper;
    LocalDateTime now=LocalDateTime.now();

    @Override
    public JsonResult findProposalPage(int reid, Integer pageNo, Integer pageSize,String status) {
        //设置页码和每页数量
        Page<Proposal> page = Page.of(pageNo, pageSize);
//把adminPage传入mapper查询中，adminPage就是分页结果
        Page<Proposal> proposalPage =  proposalMapper.selectProposalByRequestIdAndStatus(page,reid,status);
//convert方法转换Page中 records集合元素类型 Admin--->AdProducerView
        //new Function<Admin, Object>() {}表示创建一个实现Function接口的匿名类对象

        return JsonResult.success(proposalPage);
    }

    @Override
    public JsonResult proposalDeleteById(int id) {
        int rows= proposalMapper.deleteById(id);
        if(rows>0){return JsonResult.success("删除成功");}
        else{ return JsonResult.fail("删除失败");}
    }

    @Override
    public JsonResult save(Proposal proposal) {
        //通过判断id值有无判断新增还是更新，id值无新增，id值有就是更新
        Proposal proposal1=null;
        LocalDateTime now= LocalDateTime.now();
            //更新
//根据id查询Admin信息
            proposal1 = proposalMapper.selectById(proposal.getId());
            //要更新的字段
        proposal.setUpdateTime(now);
            proposalMapper.updateById(proposal);
            int rows=proposalMapper.updateById(proposal);
//要判断更新影响行数
            if(rows>0)
            {
                return JsonResult.success("更新成功",null);
            }
            else{return JsonResult.fail("更新失败");}

        }
    @Override
    public JsonResult findById(int id) {
        Proposal proposal=proposalMapper.selectById(id);
        return JsonResult.success(proposal);
    }


    }



