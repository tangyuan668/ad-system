package com.tjetc.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tjetc.common.JsonResult;
import com.tjetc.dao.AdjustmentMapper;
import com.tjetc.entity.Adjustment;
import com.tjetc.entity.Adjustment;
import com.tjetc.service.AdjustmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
@Service
public class AdjustmentServiceImpl implements AdjustmentService {
    @Value("${token.expiration}")
    private int tokeRxpiration;
    @Autowired
    private AdjustmentMapper adjustmentMapper;
    LocalDateTime now=LocalDateTime.now();

    @Override
    public JsonResult findAdjustmentPage(int deid, Integer pageNo, Integer pageSize,String status) {
        //设置页码和每页数量
        Page<Adjustment> page = Page.of(pageNo, pageSize);
//把adminPage传入mapper查询中，adminPage就是分页结果
        Page<Adjustment> adjustmentPage =  adjustmentMapper.selectAdjustmentByDesignIdAndStatus(page,deid,status);
//convert方法转换Page中 records集合元素类型 Admin--->AdProducerView
        //new Function<Admin, Object>() {}表示创建一个实现Function接口的匿名类对象

        return JsonResult.success(adjustmentPage);
    }

    @Override
    public JsonResult adjustmentDeleteById(int id) {
        int rows= adjustmentMapper.deleteById(id);
        if(rows>0){return JsonResult.success("删除成功");}
        else{ return JsonResult.fail("删除失败");}
    }

    @Override
    public JsonResult save(Adjustment adjustment) {
        //通过判断id值有无判断新增还是更新，id值无新增，id值有就是更新
        Adjustment adjustment1=null;
        LocalDateTime now= LocalDateTime.now();
        //更新
//根据id查询Admin信息
        adjustment1 = adjustmentMapper.selectById(adjustment.getId());
        //要更新的字段
        adjustment.setUpdateTime(now);
        adjustmentMapper.updateById(adjustment);
        int rows=adjustmentMapper.updateById(adjustment);
//要判断更新影响行数
        if(rows>0)
        {
            return JsonResult.success("更新成功",null);
        }
        else{return JsonResult.fail("更新失败");}

    }
    @Override
    public JsonResult findById(int id) {
        Adjustment adjustment=adjustmentMapper.selectById(id);
        return JsonResult.success(adjustment);
    }
}
