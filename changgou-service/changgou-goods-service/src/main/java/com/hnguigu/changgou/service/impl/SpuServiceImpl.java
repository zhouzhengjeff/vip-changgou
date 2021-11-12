package com.hnguigu.changgou.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hnguigu.changgou.common.exception.ChanggouException;
import com.hnguigu.changgou.common.util.IdWorker;
import com.hnguigu.changgou.dto.GoodsDTO;
import com.hnguigu.changgou.entity.Brand;
import com.hnguigu.changgou.entity.Category;
import com.hnguigu.changgou.entity.Sku;
import com.hnguigu.changgou.entity.Spu;
import com.hnguigu.changgou.mapper.SpuMapper;
import com.hnguigu.changgou.service.BrandService;
import com.hnguigu.changgou.service.CategoryService;
import com.hnguigu.changgou.service.SkuService;
import com.hnguigu.changgou.service.SpuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class SpuServiceImpl extends ServiceImpl<SpuMapper, Spu> implements SpuService {

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private BrandService brandService;

    @Autowired
    private SkuService skuService;

    @Override
    public void addSpu(GoodsDTO goodsDTO) {
        if (ObjectUtils.isEmpty(goodsDTO)) {
            throw new ChanggouException("");
        }

        Spu spu = goodsDTO.getSpu();
        List<Sku> skuList = goodsDTO.getSkuList();

        if (ObjectUtils.isEmpty(spu)) {
            throw new ChanggouException("");
        }

        spu.setId(String.valueOf(idWorker.nextId()));
        this.save(spu);

        for (Sku sku : skuList) {
            sku.setId(String.valueOf(this.idWorker.nextId()));
            sku.setSpuId(spu.getId());
            sku.setCategoryId(spu.getCategory1Id());

            // TODO 根据categoryId查询categoryName
            Category category = this.categoryService.getById(spu.getCategory1Id());
            if (!ObjectUtils.isEmpty(category)) {
                sku.setCategoryName(category.getName());
            }

            Brand brand = this.brandService.getById(spu.getBrandId());
            if (!ObjectUtils.isEmpty(brand)) {
                sku.setBrandName(brand.getName());
            }

            sku.setCreateTime(new Date());
            sku.setUpdateTime(new Date());

            this.skuService.save(sku);

        }
    }
}
