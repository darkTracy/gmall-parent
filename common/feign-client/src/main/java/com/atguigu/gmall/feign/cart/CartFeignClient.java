package com.atguigu.gmall.feign.cart;

import com.atguigu.gmall.common.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("service-cart")
@RequestMapping("/api/inner/rpc/cart")
public interface CartFeignClient {
    @GetMapping("addToCart")
    Result<Object> addToCart(@RequestParam("skuId")Long skuId, @RequestParam("num") Integer num);
    //删除购物车中物品
    @GetMapping("/deleteChecked")
    Result deleteChecked();
}
