package com.tjetc.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tjetc.entity.Admin;
import com.tjetc.entity.Channel;
import org.apache.ibatis.annotations.Param;

public interface ChannelMapper extends BaseMapper<Channel> {
    Page<Channel> selectChannelPageLikeName(Page<Channel> page, @Param("name") String name);
    int deleteChannel(int id);
    Channel selectByName(String name);



}
