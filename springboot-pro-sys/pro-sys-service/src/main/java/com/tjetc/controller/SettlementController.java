package com.tjetc.controller;

import com.tjetc.common.JsonResult;
import com.tjetc.entity.Settlement;
import com.tjetc.service.SettlementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("settlement")
public class SettlementController {
    @Value(("${file.basePath}"))
    private String fileBasePath;
    @Autowired
    private SettlementService settlementService;


    @RequestMapping("settlementpage")
    public JsonResult settlementpage(@RequestParam(value="siid",required = false,defaultValue = "") int siid,
                               @RequestParam(value = "pageNo",required = false,defaultValue = "1" )Integer pageNo,
                               @RequestParam(value = "pageSize",required = false,defaultValue = "2" )Integer pageSize,
                               @RequestParam(value="name",required = false) String name,
                                     @RequestParam(value="status",required = false) String status )
    {
        return settlementService.findSettlementPage(siid, pageNo, pageSize,name,status);

    }
    @RequestMapping("settlementdelete/{id}")
    public JsonResult settlementdelete(@PathVariable("id") int id){
        return settlementService.settlementDeleteById(id);


    }
    @RequestMapping("settlementsave")
    public JsonResult settlementsave(@RequestBody Settlement settlement)
    {
        return settlementService.save(settlement);

    }
    @RequestMapping("detail/{id}")
    public JsonResult detail(@PathVariable("id")int id){
        return settlementService.findById(id);


    }

    @RequestMapping("updateAmout")
    public JsonResult updateAmout(){
        return settlementService.updateAllAmout();


    }
@RequestMapping("updatePaymentTime/{id}")
    public JsonResult updatePaymentTime(@PathVariable("id") int id)
{
    return settlementService.updatePaymentTime(id);


}



}
