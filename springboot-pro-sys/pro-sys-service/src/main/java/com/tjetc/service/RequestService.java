package com.tjetc.service;

import com.tjetc.common.JsonResult;
import com.tjetc.entity.Request;

public interface RequestService {
    JsonResult findRequestPage(String title, Integer pageNo, Integer pageSize,int cuid,String status);
    JsonResult requestDeleteById(int id);
    JsonResult save(Request request);
    JsonResult findById(int id);


}
