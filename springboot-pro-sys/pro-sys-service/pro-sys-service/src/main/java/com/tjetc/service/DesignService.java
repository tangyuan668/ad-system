package com.tjetc.service;

import com.tjetc.common.JsonResult;
import com.tjetc.entity.Design;

public interface DesignService {
    JsonResult findDesignPage(int reid, Integer pageNo, Integer pageSize, String status);
    JsonResult designDeleteById(int id);
    JsonResult save(Design design);
    JsonResult findById(int id);
    
    
}
