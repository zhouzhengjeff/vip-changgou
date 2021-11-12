package com.hnguigu.changgou.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hnguigu.changgou.dto.BrandQueryDTO;
import com.hnguigu.changgou.entity.Brand;
import com.hnguigu.changgou.mapper.BrandMapper;
import com.hnguigu.changgou.service.BrandService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

@Service
@Transactional
public class BrandServiceImpl extends ServiceImpl<BrandMapper, Brand> implements BrandService {

    @Override
    public IPage selectPage(Integer pageNum, Integer pageSize) {
        if (pageNum == null || pageNum <= 0) {
            throw new IllegalArgumentException("xxxxxxxxx");
        }

        if (pageSize == null || pageSize <= 0) {
            throw new IllegalArgumentException("xxxxxxxxx");
        }

        Page<Brand> page = Page.of(pageNum, pageSize);
        return this.page(page);
    }

    @Override
    public IPage selectPage(Integer pageNum, Integer pageSize, BrandQueryDTO brandQueryDTO) {
        LambdaQueryWrapper<Brand> wrapper = Wrappers.lambdaQuery(Brand.class);

        if (pageNum == null || pageNum <= 0) {
            throw new IllegalArgumentException("xxxxxxxxx");
        }

        if (pageSize == null || pageSize <= 0) {
            throw new IllegalArgumentException("xxxxxxxxx");
        }

        if (ObjectUtils.isEmpty(brandQueryDTO)) {
            return this.selectPage(pageNum, pageSize);
        }

        Page<Brand> page = Page.of(pageNum, pageSize);
        String name = brandQueryDTO.getName();
        String letter = brandQueryDTO.getLetter();
        if (!StringUtils.isEmpty(name)) {
            wrapper.like(Brand::getName, name);
        }

        if (!StringUtils.isEmpty(letter)) {
            wrapper.like(Brand::getLetter, letter);
        }

        /*try {
            int i = 10 / 0;
        } catch (Exception e) {
            throw new ChanggouException("你很扯，你除零了");
        }*/


        /*LambdaQueryWrapper<Brand> wrapper =
                new QueryWrapper<Brand>().lambda().like(Brand::getName, name);*/

        return this.page(page, wrapper);
    }
}
