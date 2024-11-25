package com.tjetc.service;

import com.tjetc.common.JsonResult;
import com.tjetc.common.advertiser.vo.param.AdvertiserParam;

public interface ChannelService {
    JsonResult findChannelPage(String username, Integer pageNo, Integer pageSize);
    JsonResult channelDeleteById(int id);
    JsonResult save(String name,String type,String description,int id);
    JsonResult findById(int id);



}
