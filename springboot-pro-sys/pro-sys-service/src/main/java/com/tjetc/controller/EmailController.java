package com.tjetc.controller;

import com.tjetc.common.JsonResult;
import com.tjetc.entity.Design;
import com.tjetc.entity.Email;
import com.tjetc.service.EmailService;
import com.tjetc.service.ProposalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("email")
public class EmailController {
    @Value(("${file.basePath}"))
    private String fileBasePath;
    @Autowired
    private EmailService emailService;
    @RequestMapping("emailsave")
    public JsonResult designsave(@RequestBody Email email)
    {
        return emailService.save(email);

    }
    @RequestMapping("emaildelete/{id}")
    public JsonResult emaildelete(@PathVariable("id") int id){
        return emailService.emailDeleteById(id);


    }

    @RequestMapping("emailget/{cuid}")
    public JsonResult  emailget(@PathVariable("cuid") Integer cuid)
    {
        return emailService.getAllEmails(cuid);

    }

    @RequestMapping("emailupdate")
    public JsonResult emailUpdate(@RequestBody Email email)
    {
        return emailService.updateEmail(email);

    }
    @RequestMapping("emailnum/{cuid}")
    public JsonResult emailUpdate(@PathVariable("cuid") Integer cuid)
    {
        return emailService.getNums(cuid);

    }


}
