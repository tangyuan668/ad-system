package com.tjetc.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tjetc.entity.Design;
import com.tjetc.entity.Proposal;
import org.apache.ibatis.annotations.Param;

public interface DesignMapper extends BaseMapper<Design> {
    Page<Design> selectDesignByRequestIdAndStatus(Page<Design> page, @Param("reid") int reid, @Param("status") String status);
Design findById(int id);

}
