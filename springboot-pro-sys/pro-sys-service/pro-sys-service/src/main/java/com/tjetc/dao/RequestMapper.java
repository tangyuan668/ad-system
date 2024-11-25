package com.tjetc.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tjetc.entity.Channel;
import com.tjetc.entity.Request;
import org.apache.ibatis.annotations.Param;

public interface RequestMapper extends BaseMapper<Request> {
    Page<Request> selectRequestPageLikeTitle(Page<Request> page, @Param("title") String title);

    Request selectByTitle(String title);


}
