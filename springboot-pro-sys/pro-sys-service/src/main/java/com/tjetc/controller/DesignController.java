package com.tjetc.controller;

import com.tjetc.common.JsonResult;
import com.tjetc.entity.Design;
import com.tjetc.service.DesignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("design")
public class DesignController {
    @Value(("${file.basePath}"))
    private String fileBasePath;
    @Autowired
    private DesignService designService;


    @RequestMapping("designpage")
    public JsonResult designpage(@RequestParam(value="reid",required = false,defaultValue = "") int reid,
                                   @RequestParam(value = "pageNo",required = false,defaultValue = "1" )Integer pageNo,
                                   @RequestParam(value = "pageSize",required = false,defaultValue = "2" )Integer pageSize,
                                   @RequestParam(value="status",required = false,defaultValue = "全部") String status)
    {
        return designService.findDesignPage(reid, pageNo, pageSize,status);

    }
    @RequestMapping("designdelete/{id}")
    public JsonResult designdelete(@PathVariable("id") int id){
        return designService.designDeleteById(id);


    }
    @RequestMapping("designsave")
    public JsonResult designsave(@RequestBody Design design)
    {
        return designService.save(design);

    }
    @RequestMapping("detail/{id}")
    public JsonResult detail(@PathVariable("id")int id){
        return designService.findById(id);


    }
    
    
    
    
    
    
}
