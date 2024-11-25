package com.tjetc.controller;

import com.tjetc.common.JsonResult;
import com.tjetc.entity.ProductionRequest;
import com.tjetc.service.ProductionRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("productionRequest")
public class ProductionRequestController {
    @Value(("${file.basePath}"))
    private String fileBasePath;
    @Autowired
    private ProductionRequestService productionRequestService;
    @RequestMapping("productionRequestpage")
    public JsonResult productionRequestpage(@RequestParam(value="prid",required = false) int prid,
                                   @RequestParam(value = "pageNo",required = false,defaultValue = "1" )Integer pageNo,
                                   @RequestParam(value = "pageSize",required = false,defaultValue = "2" )Integer pageSize,
                                   @RequestParam(value="name",required = false) String name,
                                   @RequestParam(value="pdid",required = false) int pdid)
    {
        return productionRequestService.findProductionRequestPage(prid, pageNo, pageSize,name,pdid);

    }
    @RequestMapping("productionRequestdelete/{id}")
    public JsonResult productionRequestdelete(@PathVariable("id") int id){
        return productionRequestService.productionRequestDeleteById(id);


    }
    @RequestMapping("productionRequestsave")
    public JsonResult productionRequestsave(@RequestBody ProductionRequest productionRequest)
    {
        return productionRequestService.save(productionRequest);

    }
    @RequestMapping("detail/{id}")
    public JsonResult detail(@PathVariable("id")int id){
        return productionRequestService.findById(id);


    }





}
