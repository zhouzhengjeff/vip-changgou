package com.hnguigu.changgou.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hnguigu.changgou.dto.GoodsDTO;
import com.hnguigu.changgou.entity.Spu;

public interface SpuService extends IService<Spu> {

    /**
     * 添加商品(spu)同时添加(sku)
     *
     * @param goodsDTO
     */
    void addSpu(GoodsDTO goodsDTO);
}


