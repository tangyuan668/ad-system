package com.tjetc.controller;

import com.tjetc.common.JsonResult;
import com.tjetc.entity.Adjustment;
import com.tjetc.service.AdjustmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("adjustment")
public class AdjustmentController {
    @Value(("${file.basePath}"))
    private String fileBasePath;
    @Autowired
    private AdjustmentService adjustmentService;


    @RequestMapping("adjustmentpage")
    public JsonResult adjustmentpage(@RequestParam(value="deid",required = false,defaultValue = "") int deid,
                                 @RequestParam(value = "pageNo",required = false,defaultValue = "1" )Integer pageNo,
                                 @RequestParam(value = "pageSize",required = false,defaultValue = "2" )Integer pageSize,
                                 @RequestParam(value="status",required = false,defaultValue = "全部") String status)
    {
        return adjustmentService.findAdjustmentPage(deid, pageNo, pageSize,status);

    }
    @RequestMapping("adjustmentdelete/{id}")
    public JsonResult adjustmentdelete(@PathVariable("id") int id){
        return adjustmentService.adjustmentDeleteById(id);


    }
    @RequestMapping("adjustmentsave")
    public JsonResult adjustmentsave(@RequestBody Adjustment adjustment)
    {
        return adjustmentService.save(adjustment);

    }
    @RequestMapping("detail/{id}")
    public JsonResult detail(@PathVariable("id")int id){
        return adjustmentService.findById(id);


    }
    
    
    
    
    
    
}
