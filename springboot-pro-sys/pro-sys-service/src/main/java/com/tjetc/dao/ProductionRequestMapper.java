package com.tjetc.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tjetc.entity.ProductionRequest;
import org.apache.ibatis.annotations.Param;

public interface ProductionRequestMapper extends BaseMapper<ProductionRequest> {
    Page<ProductionRequest> selectProductionRequestByProductionRequestIdAndNameAndProducerId(Page<ProductionRequest> page, @Param("prid") int prid, @Param("name") String name, @Param("pdid") int pdid);

}
