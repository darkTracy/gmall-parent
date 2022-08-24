package com.atguigu.gmall.product.controller;

import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.model.product.BaseTrademark;
import com.atguigu.gmall.product.service.BaseTrademarkService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/admin/product")
@RestController
public class BaseTrademarkController {
    @Autowired
    BaseTrademarkService baseTrademarkService;
    @GetMapping("/baseTrademark/{pn}/{size}")
    public Result baseTrademark(@PathVariable("pn")Long pn,
                                @PathVariable("size")Long size){
        Page<BaseTrademark> page =new Page<>(pn,size);
        Page<BaseTrademark> pageResult = baseTrademarkService.page(page);

        return Result.ok(pageResult);
    }
    @GetMapping("/baseTrademark/get/{id}")
    public Result getBaseTrademark(@PathVariable("id")Long id){
        BaseTrademark trademark = baseTrademarkService.getById(id);
        return Result.ok(trademark);
    }
    @PutMapping("/baseTrademark/update")
    public Result updatebaseTrademark(@RequestBody BaseTrademark trademark){
        baseTrademarkService.updateById(trademark);
        return Result.ok();
    }
    @PostMapping("/baseTrademark/save")
    public Result savebaseTrademark(@RequestBody BaseTrademark trademark){
        baseTrademarkService.save(trademark);
        return Result.ok();
    }
    @DeleteMapping("/baseTrademark/remove/{tid}")
    public Result deletebaseTrademark(@PathVariable("tid")Long tid){
        baseTrademarkService.removeById(tid);
        return Result.ok();
    }
}
