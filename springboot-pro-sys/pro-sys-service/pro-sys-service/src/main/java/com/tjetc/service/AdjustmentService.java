package com.tjetc.service;

import com.tjetc.common.JsonResult;
import com.tjetc.entity.Adjustment;
import com.tjetc.entity.Adjustment;

public interface AdjustmentService {
    JsonResult findAdjustmentPage(int deid, Integer pageNo, Integer pageSize, String status);
    JsonResult adjustmentDeleteById(int id);
    JsonResult save(Adjustment adjustment);
    JsonResult findById(int id);
}
