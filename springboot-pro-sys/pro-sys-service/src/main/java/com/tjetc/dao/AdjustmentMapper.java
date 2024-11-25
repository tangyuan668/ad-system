package com.tjetc.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tjetc.entity.Adjustment;
import com.tjetc.entity.Adjustment;
import org.apache.ibatis.annotations.Param;

public interface AdjustmentMapper extends BaseMapper<Adjustment> {

    Page<Adjustment> selectAdjustmentByDesignIdAndStatus(Page<Adjustment> page, @Param("deid") int deid, @Param("status") String status);
}
