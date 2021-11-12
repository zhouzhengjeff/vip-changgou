package com.hnguigu.changgou.controller;

import com.hnguigu.changgou.common.vo.Result;
import com.hnguigu.changgou.common.vo.StatusCode;
import com.hnguigu.changgou.dto.SkuDTO;
import com.hnguigu.changgou.service.SkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SkuController {

    @Autowired
    private SkuService skuService;

    @GetMapping("/sku/findSkuDTOListBySpuId/{spuId}")
    public Result<List<SkuDTO>> findSkuDTOListBySpuId(@PathVariable("spuId") String spuId) {
        return new Result(true, StatusCode.OK, "根据SPU编号查询SKU列表成功",
                this.skuService.findSkuDTOListBySpuId(spuId));
    }
}
