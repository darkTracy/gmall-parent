package com.atguigu.gmall.product.controller;

import com.atguigu.gmall.common.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("admin/product")
public class BaseAttrController {
    @Autowired
    @GetMapping("attrInfoList/{category1Id}/{category2Id}/{category3Id}")


    public Result attrInfoList(){

    }
}
