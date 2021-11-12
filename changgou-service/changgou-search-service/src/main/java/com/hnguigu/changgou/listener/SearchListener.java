package com.hnguigu.changgou.listener;

import com.hnguigu.changgou.common.exception.ChanggouException;
import com.hnguigu.changgou.common.vo.Result;
import com.hnguigu.changgou.dto.SkuDTO;
import com.hnguigu.changgou.feign.SkuFeignClient;
import com.hnguigu.changou.dto.SkuInfo;
import com.hnguigu.changou.service.SearchService;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

//@Component
public class SearchListener {

    @Autowired(required = false)
    private SkuFeignClient skuFeignClient;

    @Autowired
    private SearchService searchService;

    @RabbitListener(queues = "SpuPullQueue")
    public void receiveMessage(String message) {
        List<SkuInfo> skuInfoList = new ArrayList<>();
        String spuId = message;

        if (StringUtils.isEmpty(spuId)) {
            throw new ChanggouException("");
        }

        // TODO 根据spuId查询skuList
        Result<List<SkuDTO>> skuDTOListResult = this.skuFeignClient.findSkuDTOListBySpuId(spuId);
        List<SkuDTO> skuList = skuDTOListResult.getData();

        SkuInfo skuInfo = new SkuInfo();
        for (SkuDTO skuDTO : skuList) {
            BeanUtils.copyProperties(skuDTO, skuInfo);
            skuInfoList.add(skuInfo);
        }

        this.searchService.importSkuInfoList(skuInfoList);
    }
}
