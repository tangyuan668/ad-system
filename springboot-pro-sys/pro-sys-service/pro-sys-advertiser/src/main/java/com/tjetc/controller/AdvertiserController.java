package com.tjetc.controller;

import com.tjetc.common.FileUploadUtil;
import com.tjetc.common.JsonResult;
import com.tjetc.common.advertiser.vo.param.AdvertiserParam;
import com.tjetc.service.AdvertiserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("advertiser")
public class AdvertiserController {
    @Value(("${file.basePath}"))
    private String fileBasePath;
    @Autowired
    private AdvertiserService advertiserService;
    @RequestMapping("detail/{id}")
    public JsonResult detail(@PathVariable("id")Long id){
        return advertiserService.findById(id);


    }
@RequestMapping("page")
    public JsonResult page(@RequestParam(value="username",required = false,defaultValue = "") String username,
                           @RequestParam(value = "pageNo",required = false,defaultValue = "1" )Integer pageNo,
                           @RequestParam(value = "pageSize",required = false,defaultValue = "2" )Integer pageSize)
{
    return advertiserService.finPage(username, pageNo, pageSize);

}
@RequestMapping("delete/{id}")
public JsonResult delete(@PathVariable("id") long id){
return advertiserService.deleteById(id);


}
    @RequestMapping("upload/image")
    public JsonResult uploadImage(@RequestParam("imageFile") MultipartFile imageFile) {
       String imagePath=FileUploadUtil.upload(imageFile,fileBasePath,"image");
        return JsonResult.success(imagePath);
    }
    //接收参数格式是json
    @RequestMapping("save")
    public JsonResult save(@RequestBody AdvertiserParam advertiserParam){

return  advertiserService.save(advertiserParam);

    }

}
