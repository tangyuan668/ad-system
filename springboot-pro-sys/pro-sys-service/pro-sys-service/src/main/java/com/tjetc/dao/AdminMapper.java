package com.tjetc.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tjetc.entity.Admin;
import com.tjetc.entity.Channel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

//BaseMapper 提供了很多方法让我们使用，例如：单表操作的增删改查
public interface AdminMapper extends BaseMapper<Admin> {
Admin selectByUsernameAndPassword(@Param("username") String username
        ,@Param("password") String password);

    /**
     * 根据username进行模型匹配分页查询
      * @param username
     * @return
     */
Page<Admin> selectPageLikeUsername(Page<Admin> page, @Param("username") String username);
Admin selectByUsername(String username);
    Page<Channel> selectChannelPageLikeName(Page<Channel> page, @Param("name") String name);
    int deleteChannel(int id);
}
