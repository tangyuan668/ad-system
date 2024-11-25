package com.tjetc.controller;

import com.tjetc.common.JsonResult;
import com.tjetc.service.AdminService;
import com.tjetc.service.ChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("channel")
public class ChannelController {
    @Value(("${file.basePath}"))
    private String fileBasePath;
    @Autowired
    private ChannelService channelService;
    @RequestMapping("channelpage")
    public JsonResult channelpage(@RequestParam(value="name",required = false,defaultValue = "") String name,
                                  @RequestParam(value = "pageNo",required = false,defaultValue = "1" )Integer pageNo,
                                  @RequestParam(value = "pageSize",required = false,defaultValue = "2" )Integer pageSize)
    {
        return channelService.findChannelPage(name, pageNo, pageSize);

    }

    @RequestMapping("channeldelete/{id}")
    public JsonResult channeldelete(@PathVariable("id") int id){
        return channelService.channelDeleteById(id);


    }
    @RequestMapping("channelsave")
    public JsonResult channelpage(@RequestParam(value="name",required = false,defaultValue = "") String name,
                                  @RequestParam(value = "type",required = false)String type,
                                  @RequestParam(value = "description",required = false)String description,
                                  @RequestParam(value = "id",required = false)int id)
    {
        return channelService.save(name, type, description,id);

    }
    @RequestMapping("detail/{id}")
    public JsonResult detail(@PathVariable("id")int id){
        return channelService.findById(id);


    }




}
