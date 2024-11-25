package com.tjetc.controller;

import com.tjetc.common.JsonResult;
import com.tjetc.entity.Proposal;
import com.tjetc.service.ProposalService;
import com.tjetc.service.ProposalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping ("proposal")
public class ProposalController {
    @Value(("${file.basePath}"))
    private String fileBasePath;
    @Autowired
    private ProposalService proposalService;
    @RequestMapping("proposalpage")
    public JsonResult proposalpage(@RequestParam(value="reid",required = false,defaultValue = "") int reid,
                                  @RequestParam(value = "pageNo",required = false,defaultValue = "1" )Integer pageNo,
                                  @RequestParam(value = "pageSize",required = false,defaultValue = "2" )Integer pageSize,
                                   @RequestParam(value="status",required = false,defaultValue = "全部") String status)
    {
        return proposalService.findProposalPage(reid, pageNo, pageSize,status);

    }
    @RequestMapping("proposaldelete/{id}")
    public JsonResult proposaldelete(@PathVariable("id") int id){
        return proposalService.proposalDeleteById(id);


    }
    @RequestMapping("proposalsave")
    public JsonResult proposalsave(@RequestBody Proposal proposal)
    {
        return proposalService.save(proposal);

    }
    @RequestMapping("detail/{id}")
    public JsonResult detail(@PathVariable("id")int id){
        return proposalService.findById(id);


    }





}
