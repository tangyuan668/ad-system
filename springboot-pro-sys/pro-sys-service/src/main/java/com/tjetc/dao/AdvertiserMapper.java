package com.tjetc.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tjetc.entity.Advertiser;
import com.tjetc.entity.Advertiser;
import org.apache.ibatis.annotations.Param;

//BaseMapper 提供了很多方法让我们使用，例如：单表操作的增删改查
public interface AdvertiserMapper extends BaseMapper<Advertiser> {
Advertiser selectByUsernameAndPassword(@Param("username") String username
        ,@Param("password") String password);

    /**
     * 根据username进行模型匹配分页查询
      * @param username
     * @return
     */
Page<Advertiser> selectPageLikeUsername(Page<Advertiser> page, @Param("username") String username);
Advertiser selectByUsername(String username);
}
