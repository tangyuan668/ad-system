package com.tjetc.service;

import com.fasterxml.jackson.core.JsonGenerator;
import com.tjetc.common.JsonResult;
import com.tjetc.entity.Settlement;

public interface SettlementService {
    JsonResult findSettlementPage(int siid, Integer pageNo, Integer pageSize, String name,String status);
    JsonResult settlementDeleteById(int id);
    JsonResult save(Settlement settlement);
    JsonResult findById(int id);
JsonResult updateAllAmout();
JsonResult updatePaymentTime(int id);


}
