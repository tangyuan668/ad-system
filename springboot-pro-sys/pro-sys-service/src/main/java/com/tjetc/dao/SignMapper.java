package com.tjetc.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tjetc.entity.Request;
import com.tjetc.entity.Sign;
import org.apache.ibatis.annotations.Param;

public interface SignMapper extends BaseMapper<Sign> {
    Page<Sign> selectSignPageLikeNameAndProposalId (Page<Sign> page, @Param("name") String name,@Param("prid")int prid);


}
