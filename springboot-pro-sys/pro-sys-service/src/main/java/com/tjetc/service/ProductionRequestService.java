package com.tjetc.service;

import com.tjetc.common.JsonResult;
import com.tjetc.entity.ProductionRequest;

public interface ProductionRequestService {

    JsonResult findProductionRequestPage(int prid, Integer pageNo, Integer pageSize, String name,int pdid);
    JsonResult productionRequestDeleteById(int id);
    JsonResult save(ProductionRequest productionRequest);
    JsonResult findById(int id);


}
