package com.atguigu.gmall.product.mapper;


import com.atguigu.gmall.model.product.SkuInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;


public interface SkuInfoMapper extends BaseMapper<SkuInfo> {


    void updateIsSale(@Param("skuId") Long skuId,
                      @Param("sale") int sale);
}




