package com.tjetc.service;

import com.tjetc.common.JsonResult;
import com.tjetc.entity.Proposal;
import com.tjetc.entity.Request;

public interface ProposalService {
    JsonResult findProposalPage(int reid, Integer pageNo, Integer pageSize,String status);
    JsonResult proposalDeleteById(int id);
    JsonResult save(Proposal proposal);
    JsonResult findById(int id);

}
