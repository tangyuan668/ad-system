package com.tjetc.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tjetc.entity.Settlement;
import com.tjetc.entity.Sign;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SettlementMapper extends BaseMapper<Settlement> {

    Page<Settlement> selectSettlementPageLikeNameAndProposalId (Page<Settlement> page, @Param("name") String name, @Param("status") String status,@Param("siid") int siid);
    List<Long> findAllSignIds();
    int updateAmoutBySignId(@Param("signId") Long signId);
}
