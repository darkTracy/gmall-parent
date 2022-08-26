package com.atguigu.gmall.product.controller;


import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.product.service.FileUploadService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Api(tags = "文件上传控制器")
@RequestMapping("/admin/product")
@RestController
public class FileuploadController {
    @Autowired
    FileUploadService fileUploadService;
    @ApiOperation(value = "文件上传")
    @PostMapping("/fileUpload")
    public Result fileupload(@RequestPart("file")MultipartFile file) throws Exception {

        String url = fileUploadService.upload(file);

        return Result.ok(url);
    }
    @PostMapping("/reg")
    public Result hahaah(@RequestParam("username")String username,
                         @RequestParam("password")String password,
                         @RequestParam("email")String email,
                         @RequestPart("header")MultipartFile[] header,
                         @RequestPart("sfz")MultipartFile sfz,
                         @RequestPart("shz")MultipartFile shz,
                         @RequestParam("ah")String[] ah,
                         @RequestHeader("Cache-Control") String cache,
                         @CookieValue("jsessionid") String jsessionid){
        Map<String,Object> result = new HashMap<>();
        result.put("用户名：",username);
        result.put("密码：",password);
        result.put("邮箱：",email);

        result.put("头像文件大小？",header.length);
        result.put("生活照文件大小？",sfz.getSize());
        result.put("身份证文件大小？",shz.getSize());
        result.put("爱好", Arrays.asList(ah));
        result.put("cache",cache);

        return Result.ok(result);
    }
}
