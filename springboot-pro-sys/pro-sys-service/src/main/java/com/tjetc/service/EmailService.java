package com.tjetc.service;

import com.tjetc.common.JsonResult;
import com.tjetc.entity.Email;

public interface EmailService {
    JsonResult save(Email email);
JsonResult emailDeleteById(int id);
JsonResult getAllEmails(Integer cuid);
JsonResult updateEmail(Email email);
    JsonResult getNums(Integer cuid);
}
