package com.hnguigu.changgou.feign;

import com.hnguigu.changgou.common.vo.Result;
import com.hnguigu.changgou.dto.SkuDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(value = "goods-service")
public interface SkuFeignClient {

    @GetMapping("/sku/findSkuDTOListBySpuId/{spuId}")
    public Result<List<SkuDTO>> findSkuDTOListBySpuId(@PathVariable("spuId") String spuId);
}
