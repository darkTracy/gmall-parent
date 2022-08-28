package com.atguigu.gmall.product.service;


import com.atguigu.gmall.model.product.BaseCategory3;
import com.atguigu.gmall.model.to.CategoryViewTo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface BaseCategory3Service extends IService<BaseCategory3> {

    List<BaseCategory3> getCategory2Child(Long c2Id);

    CategoryViewTo getCategoryView(Long c3Id);
}
