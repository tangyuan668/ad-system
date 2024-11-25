package com.tjetc.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tjetc.entity.Deliver;
import org.apache.ibatis.annotations.Param;

public interface DeliverMapper extends BaseMapper<Deliver> {
    Page<Deliver> selectDeliverByRequestIdAndStatusAndName(Page<Deliver> page, @Param("reid") int reid, @Param("status") String status,@Param("name")String name);


}
