package com.hnguigu.changgou.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hnguigu.changgou.entity.CategoryBrand;
import com.hnguigu.changgou.mapper.CategoryBrandMapper;
import com.hnguigu.changgou.service.CategoryBrandService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CategoryBrandServiceImpl extends ServiceImpl<CategoryBrandMapper, CategoryBrand> implements CategoryBrandService {
}
