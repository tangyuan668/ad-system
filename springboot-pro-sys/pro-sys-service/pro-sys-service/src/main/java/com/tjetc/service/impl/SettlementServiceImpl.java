package com.tjetc.service.impl;
import java.util.Map;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tjetc.common.JsonResult;
import com.tjetc.dao.SettlementMapper;
import com.tjetc.entity.Settlement;
import com.tjetc.entity.Settlement;
import com.tjetc.service.SettlementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class SettlementServiceImpl implements SettlementService {

    @Value("${token.expiration}")
    private int tokeRxpiration;
    @Autowired
    private SettlementMapper settlementMapper;
    LocalDateTime now=LocalDateTime.now();

    @Override
    public JsonResult findSettlementPage(int siid, Integer pageNo, Integer pageSize,String name,String status) {
        //设置页码和每页数量
        Page<Settlement> page = Page.of(pageNo, pageSize);
//把adminPage传入mapper查询中，adminPage就是分页结果
        Page<Settlement> settlementPage =  settlementMapper.selectSettlementPageLikeNameAndProposalId(page,name,status,siid);
//convert方法转换Page中 records集合元素类型 Admin--->AdProducerView
        //new Function<Admin, Object>() {}表示创建一个实现Function接口的匿名类对象

        return JsonResult.success(settlementPage);
    }

    @Override
    public JsonResult settlementDeleteById(int id) {
        int rows= settlementMapper.deleteById(id);
        if(rows>0){return JsonResult.success("删除成功");}
        else{ return JsonResult.fail("删除失败");}
    }

    @Override
    public JsonResult save(Settlement settlement) {
        //通过判断id值有无判断新增还是更新，id值无新增，id值有就是更新
        Settlement settlement1=null;
        LocalDateTime now= LocalDateTime.now();
        if(settlement.getId()==null||settlement.getId()<=0)
        {
            settlement.setUpdateTime(now);
            settlement.setCreateTime(now);
            settlementMapper.insert(settlement);
            return JsonResult.success("新增成功",null);

        }


        else {
            //更新
//根据id查询Admin信息
            settlement1 = settlementMapper.selectById(settlement.getId());
            //要更新的字段
            settlement.setUpdateTime(now);
            settlementMapper.updateById(settlement);
            int rows = settlementMapper.updateById(settlement);
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
        Settlement settlement=settlementMapper.selectById(id);
        return JsonResult.success(settlement);
    }

    @Transactional
    public JsonResult updateAllAmout() {
        // 获取所有需要更新的记录
        List<Long> signIds = settlementMapper.findAllSignIds();

        // 用于记录成功和失败的情况
        List<Long> successIds = new ArrayList<>();
        List<Long> failIds = new ArrayList<>();

        for (Long signId : signIds) {
            int rowsAffected = settlementMapper.updateAmoutBySignId(signId);
            if (rowsAffected > 0) {
                successIds.add(signId);
            } else {
                failIds.add(signId);
            }
        }

        // 如果有失败的情况，可以选择回滚或者继续
        if (!failIds.isEmpty()) {
            // 如果要回滚整个事务
            throw new RuntimeException("Update failed for some records: " + failIds);


        }

        // 全部成功
        return JsonResult.success("Amount updated successfully", successIds);
    }
}
