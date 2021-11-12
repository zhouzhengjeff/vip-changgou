package com.hnguigu.changgou.controller;

import com.hnguigu.changgou.common.vo.Result;
import com.hnguigu.changgou.common.vo.StatusCode;
import com.hnguigu.changgou.dto.GoodsDTO;
import com.hnguigu.changgou.service.SpuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SpuController {

    @Autowired
    private SpuService spuService;

    @PostMapping("/spu/add")
    public Result add(@RequestBody GoodsDTO goodsDTO) {
        this.spuService.addSpu(goodsDTO);
        return new Result(true, StatusCode.OK, "SPU与SKU列表的保存成功", null);
    }
}
