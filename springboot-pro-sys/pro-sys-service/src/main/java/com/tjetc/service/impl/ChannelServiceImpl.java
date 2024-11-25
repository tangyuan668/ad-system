package com.tjetc.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tjetc.common.JsonResult;
import com.tjetc.common.advertiser.vo.param.AdvertiserParam;
import com.tjetc.dao.AdminMapper;
import com.tjetc.dao.ChannelMapper;
import com.tjetc.entity.Advertiser;
import com.tjetc.entity.Channel;
import com.tjetc.service.AdminService;
import com.tjetc.service.ChannelService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class ChannelServiceImpl implements ChannelService {
    @Value("${token.expiration}")
    private int tokeRxpiration;
    @Autowired
    private ChannelMapper channelMapper;
    @Override
    public JsonResult findChannelPage(String username, Integer pageNo, Integer pageSize) {
        //设置页码和每页数量
        Page<Channel> page = Page.of(pageNo, pageSize);
//把adminPage传入mapper查询中，adminPage就是分页结果
        Page<Channel> channelPage =  channelMapper.selectChannelPageLikeName(page,username);
//convert方法转换Page中 records集合元素类型 Admin--->AdProducerView
        //new Function<Admin, Object>() {}表示创建一个实现Function接口的匿名类对象

        return JsonResult.success(channelPage);
    }

    @Override
    public JsonResult channelDeleteById(int id) {
        int rows= channelMapper.deleteChannel(id);
        if(rows>0){return JsonResult.success("删除成功");}
        else{ return JsonResult.fail("删除失败");}
    }

    @Transactional
    @Override
    public JsonResult save(String name,String type,String description,int id) {
        //通过判断id值有无判断新增还是更新，id值无新增，id值有就是更新
Channel channel=null;
        LocalDateTime now= LocalDateTime.now();
        if(id<=0)
        {//新增
            //判断用户名或则和密码不能为空
            if(StringUtils.isAnyBlank(name,type))
            {
                return JsonResult.fail("新增渠道，名字和类型不能为空");
            }
            //判断用户名不能重复
            Channel existChannel = channelMapper.selectByName(name);
            if(existChannel!=null)
            {return  JsonResult.fail("用户名已存在");}
            //新增
            channel=new Channel();
channel.setDescription(description);
           channel.setType(type);
           channel.setName(name);
            channel.setUpdateTime(now);
            channel.setCreateTime(now);
            channelMapper.insert(channel);
            return JsonResult.success("新增成功",null);
        }
        else{
            //更新
//根据id查询Advertiser信息
            channel = channelMapper.selectById(id);
            if(channel==null)
            {
                return JsonResult.fail("用户不存在，更新失败");
            }
            //要更新的字段
            channel.setId(id);
            channel.setDescription(description);
            channel.setName(name);
            channel.setType(type);
            channel.setUpdateTime(now);
            channelMapper.updateById(channel);
            int rows=channelMapper.updateById(channel);
//要判断更新影响行数
            if(rows>0)
            {
                return JsonResult.success("更新成功",null);
            }
            else{return JsonResult.fail("更新失败");}

        }




    }

    @Override
    public JsonResult findById(int id) {
        Channel channel=channelMapper.selectById(id);
        return JsonResult.success(channel);
    }

}
