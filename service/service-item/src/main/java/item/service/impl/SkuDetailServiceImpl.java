package item.service.impl;

import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.model.to.SkuDetailTo;
import item.feign.SkuDetailFeignClient;
import item.service.SkuDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SkuDetailServiceImpl implements SkuDetailService {

    @Autowired
    SkuDetailFeignClient skuDetailFeignClient;


    @Override
    public SkuDetailTo getSkuDetail(Long skuId) {
        SkuDetailTo detailTo = new SkuDetailTo();
        //远程调用商品服务查询
        Result<SkuDetailTo> skuDetail = skuDetailFeignClient.getSkuDetail(skuId);

        return skuDetail.getData();
    }
}
