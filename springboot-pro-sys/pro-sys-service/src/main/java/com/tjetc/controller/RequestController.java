package com.tjetc.controller;

import com.tjetc.common.JsonResult;
import com.tjetc.entity.Request;
import com.tjetc.service.RequestService;
import com.tjetc.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("request")
public class RequestController {
    @Value(("${file.basePath}"))
    private String fileBasePath;
    @Autowired
    private RequestService requestService;
    @RequestMapping("requestpage")
    public JsonResult requestpage(@RequestParam(value="title",required = false,defaultValue = "") String title,
                                  @RequestParam(value = "pageNo",required = false,defaultValue = "1" )Integer pageNo,
                                  @RequestParam(value = "pageSize",required = false,defaultValue = "2" )Integer pageSize,
                                  @RequestParam(value = "cuid",required = false)Integer cuid,
                                  @RequestParam(value="status",required = false,defaultValue = "全部") String status )
    {
        return requestService.findRequestPage(title, pageNo, pageSize,cuid,status);

    }
    @RequestMapping("requestdelete/{id}")
    public JsonResult requestdelete(@PathVariable("id") int id){
        return requestService.requestDeleteById(id);


    }
    @RequestMapping("requestsave")
    public JsonResult requestpage(@RequestBody Request request)
    {
        return requestService.save(request);

    }
    @RequestMapping("detail/{id}")
    public JsonResult detail(@PathVariable("id")int id){
        return requestService.findById(id);


    }
    
    



}
