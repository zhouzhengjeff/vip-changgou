package com.hnguigu.changgou.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hnguigu.changgou.dto.SkuDTO;
import com.hnguigu.changgou.entity.Sku;
import com.hnguigu.changgou.entity.Spu;

import java.util.List;

public interface SkuService extends IService<Sku> {

    List<SkuDTO> findSkuDTOListBySpuId(String spuId);
}
