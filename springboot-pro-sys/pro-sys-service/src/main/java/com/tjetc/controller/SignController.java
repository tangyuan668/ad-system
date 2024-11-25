package com.tjetc.controller;

import com.tjetc.common.JsonResult;
import com.tjetc.config.FileUploadUtil;
import com.tjetc.entity.Sign;
import com.tjetc.service.SignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("sign")
public class SignController {
    @Value(("${file.basePath}"))
    private String fileBasePath;
    @Autowired
    private SignService signService;


    @RequestMapping("signpage")
    public JsonResult signpage(@RequestParam(value="prid",required = false,defaultValue = "") int prid,
                                 @RequestParam(value = "pageNo",required = false,defaultValue = "1" )Integer pageNo,
                                 @RequestParam(value = "pageSize",required = false,defaultValue = "2" )Integer pageSize,
                                 @RequestParam(value="name",required = false) String name)
    {
        return signService.findSignPage(prid, pageNo, pageSize,name);

    }
    @RequestMapping("signdelete/{id}")
    public JsonResult signdelete(@PathVariable("id") int id){
        return signService.signDeleteById(id);


    }
    @RequestMapping("signsave")
    public JsonResult signsave(@RequestBody Sign sign)
    {
        return signService.save(sign);

    }
    @RequestMapping("detail/{id}")
    public JsonResult detail(@PathVariable("id")int id){
        return signService.findById(id);


    }

    @RequestMapping("upload/image")
    public JsonResult uploadImage(@RequestParam("imageFile") MultipartFile imageFile) {
        String imagePath= FileUploadUtil.upload(imageFile,fileBasePath,"image");
        return JsonResult.success(imagePath);
    }




}
