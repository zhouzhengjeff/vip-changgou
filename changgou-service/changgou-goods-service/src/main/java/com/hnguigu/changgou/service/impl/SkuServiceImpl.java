package com.hnguigu.changgou.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hnguigu.changgou.common.exception.ChanggouException;
import com.hnguigu.changgou.dto.SkuDTO;
import com.hnguigu.changgou.entity.Sku;
import com.hnguigu.changgou.mapper.SkuMapper;
import com.hnguigu.changgou.service.SkuService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class SkuServiceImpl extends ServiceImpl<SkuMapper, Sku> implements SkuService {

    @Override
    public List<SkuDTO> findSkuDTOListBySpuId(String spuId) {
        List<SkuDTO> skuDTOList = new ArrayList<>();

        if (StringUtils.isEmpty(spuId)) {
            throw new ChanggouException("");
        }

        List<Sku> skuList = this.list(Wrappers.lambdaQuery(Sku.class).eq(Sku::getSpuId, spuId));
        SkuDTO skuDTO = new SkuDTO();
        for (Sku sku : skuList) {
            BeanUtils.copyProperties(sku, skuDTO);
//            skuDTO.setIsDefault(null);

            skuDTO.setId(Long.parseLong(sku.getId()));
            skuDTO.setCategoryId(Long.valueOf(sku.getCategoryId()));
            skuDTO.setSpuId(Long.parseLong(sku.getSpuId()));
            skuDTO.setPrice(Long.valueOf(sku.getPrice()));

            String spec = sku.getSpec();
            Map<String, Object> specMap = JSON.parseObject(spec, Map.class);
            skuDTO.setSpecMap(specMap);
            skuDTOList.add(skuDTO);
        }

        return skuDTOList;
    }
}
