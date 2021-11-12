package com.hnguigu.changgou.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hnguigu.changgou.dto.BrandQueryDTO;
import com.hnguigu.changgou.entity.Brand;

public interface BrandService extends IService<Brand> {

    /*（1）查询全部列表数据
（2）根据ID查询实体数据
（3）增加
（4）修改
（5）删除
（6）条件查询
（7）分页查询
（8）分页+条件查询*/

    IPage selectPage(Integer pageNum, Integer pageSize);

    IPage selectPage(Integer pageNum, Integer pageSize, BrandQueryDTO brandQueryDTO);
}
