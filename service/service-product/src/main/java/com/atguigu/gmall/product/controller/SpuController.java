package com.atguigu.gmall.product.controller;

import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.model.product.SpuImage;
import com.atguigu.gmall.model.product.SpuInfo;
import com.atguigu.gmall.product.service.SpuImageService;
import com.atguigu.gmall.product.service.SpuInfoService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/admin/product")
@RestController
public class SpuController {

    @Autowired
    SpuInfoService spuInfoService;

    @Autowired
    SpuImageService spuImageService;

    @GetMapping("/{pn}/{ps}")
    public Result getSpuPage(@PathVariable("pn") Long pn,
                             @PathVariable("ps") Long ps,
                             @RequestParam("category3Id") Long category3Id){
        Page<SpuInfo> page = new Page<>(pn,ps);

        QueryWrapper<SpuInfo> wrapper = new QueryWrapper<>();
        wrapper.eq("category3_id",category3Id);

        //分页查询
        Page<SpuInfo> result = spuInfoService.page(page, wrapper);

        return Result.ok(result);

    }
    @PostMapping("/saveSpuInfo")
    public Result saveSpuInfo(@RequestBody SpuInfo info){

        //spu_info大保存。spu_info、spu_image、spu_sale_attr、spu_sale_attr_value
        spuInfoService.saveSpuInfo(info);
        return Result.ok();
    }

    @GetMapping("/spuImageList/{spuId}")
    public Result spuImageList(@PathVariable("spuId")Long spuId){

        QueryWrapper<SpuImage> wrapper = new QueryWrapper<>();
        wrapper.eq("spu_id",spuId);
        List<SpuImage> list = spuImageService.list(wrapper);
        return Result.ok(list);
    }

}
