package com.tjetc.controller;

import com.tjetc.common.FileUploadUtil;
import com.tjetc.common.JsonResult;
import com.tjetc.common.adproducer.vo.param.AdProducerParam;
import com.tjetc.service.AdProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("adproducer")
public class AdProducerController {
    @Value(("${file.basePath}"))
    private String fileBasePath;
    @Autowired
    private AdProducerService adproducerService;
    @RequestMapping("detail/{id}")
    public JsonResult detail(@PathVariable("id")Long id){
        return adproducerService.findById(id);


    }
@RequestMapping("page")
    public JsonResult page(@RequestParam(value="username",required = false,defaultValue = "") String username,
                           @RequestParam(value = "pageNo",required = false,defaultValue = "1" )Integer pageNo,
                           @RequestParam(value = "pageSize",required = false,defaultValue = "2" )Integer pageSize)
{
    return adproducerService.finPage(username, pageNo, pageSize);

}
@RequestMapping("delete/{id}")
public JsonResult delete(@PathVariable("id") long id){
return adproducerService.deleteById(id);


}
    @RequestMapping("upload/image")
    public JsonResult uploadImage(@RequestParam("imageFile") MultipartFile imageFile) {
       String imagePath=FileUploadUtil.upload(imageFile,fileBasePath,"image");
        return JsonResult.success(imagePath);
    }
    //接收参数格式是json
    @RequestMapping("save")
    public JsonResult save(@RequestBody AdProducerParam adproducerParam){

return  adproducerService.save(adproducerParam);

    }

}
