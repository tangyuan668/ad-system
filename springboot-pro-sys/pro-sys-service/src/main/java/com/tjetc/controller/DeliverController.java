package com.tjetc.controller;

import com.tjetc.common.JsonResult;
import com.tjetc.entity.Deliver;
import com.tjetc.service.DeliverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("deliver")
public class DeliverController {

    @Value(("${file.basePath}"))
    private String fileBasePath;
    @Autowired
    private DeliverService deliverService;
    @RequestMapping("deliverpage")
    public JsonResult deliverpage(@RequestParam(value="reid",required = false,defaultValue = "") int reid,
                                   @RequestParam(value = "pageNo",required = false,defaultValue = "1" )Integer pageNo,
                                   @RequestParam(value = "pageSize",required = false,defaultValue = "2" )Integer pageSize,
                                   @RequestParam(value="status",required = false,defaultValue = "全部") String status,
                                  @RequestParam(value="name",required = false) String name)
    {
        return deliverService.findDeliverPage(reid, pageNo, pageSize,status,name);

    }
    @RequestMapping("deliverdelete/{id}")
    public JsonResult deliverdelete(@PathVariable("id") int id){
        return deliverService.deliverDeleteById(id);


    }
    @RequestMapping("deliversave")
    public JsonResult deliversave(@RequestBody Deliver deliver)
    {
        return deliverService.save(deliver);

    }
    @RequestMapping("detail/{id}")
    public JsonResult detail(@PathVariable("id")int id){
        return deliverService.findById(id);


    }










}
