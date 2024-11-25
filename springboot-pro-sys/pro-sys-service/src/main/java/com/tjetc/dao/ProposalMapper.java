package com.tjetc.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tjetc.entity.Channel;
import com.tjetc.entity.Proposal;
import com.tjetc.entity.Request;
import org.apache.ibatis.annotations.Param;

public interface ProposalMapper extends BaseMapper<Proposal> {

    Page<Proposal> selectProposalByRequestIdAndStatus(Page<Proposal> page, @Param("reid") int reid,@Param("status") String status,@Param("adid")Integer adid);





}
