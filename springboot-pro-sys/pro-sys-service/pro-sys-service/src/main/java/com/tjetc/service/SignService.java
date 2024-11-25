package com.tjetc.service;

import com.tjetc.common.JsonResult;
import com.tjetc.entity.Sign;

public interface SignService {
    JsonResult findSignPage(int prid, Integer pageNo, Integer pageSize, String name);
    JsonResult signDeleteById(int id);
    JsonResult save(Sign sign);
    JsonResult findById(int id);
    
    
    
}
