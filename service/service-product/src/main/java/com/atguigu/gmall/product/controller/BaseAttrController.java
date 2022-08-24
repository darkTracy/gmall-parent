package com.atguigu.gmall.product.controller;

import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.model.product.BaseAttrInfo;
import com.atguigu.gmall.model.product.BaseAttrValue;
import com.atguigu.gmall.product.service.BaseAttrInfoService;
import com.atguigu.gmall.product.service.BaseAttrValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("admin/product")
public class BaseAttrController {
    @Autowired(required = false)
    BaseAttrInfoService baseAttrInfoService;
    @Autowired(required = false)
    BaseAttrValueService baseAttrValueService;
    @GetMapping("attrInfoList/{category1Id}/{category2Id}/{category3Id}")
    public Result getAttrInfoList(@PathVariable("category1Id")Long category1Id,
                                  @PathVariable("category2Id")Long category2Id,
                                  @PathVariable("category3Id")Long category3Id){
        List<BaseAttrInfo> infos = baseAttrInfoService.getAttrInfoAndValueByCategoryId(category1Id,category2Id,category3Id);
        return Result.ok(infos);
    }
    @PostMapping("/saveAttrInfo")
    public Result saveAttrInfo(@RequestBody BaseAttrInfo info){
        baseAttrInfoService.saveAttrInfo(info);
        return Result.ok();
    }
    @GetMapping("/getAttrValueList/{attrId}")
    public Result getAttrValueList(@PathVariable("attrId")Long attrId){

        List<BaseAttrValue> values = baseAttrValueService.getAttrValueList(attrId);
        return Result.ok(values);
    }
}
