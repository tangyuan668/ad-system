package com.tjetc.service;

import com.tjetc.common.JsonResult;
import com.tjetc.entity.Deliver;

public interface DeliverService {

    JsonResult findDeliverPage(int reid, Integer pageNo, Integer pageSize, String status,String name);
    JsonResult deliverDeleteById(int id);
    JsonResult save(Deliver deliver);
    JsonResult findById(int id);
    
    
    
}
