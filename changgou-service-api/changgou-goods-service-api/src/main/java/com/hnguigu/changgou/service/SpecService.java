package com.hnguigu.changgou.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hnguigu.changgou.entity.Spec;
import com.hnguigu.changgou.vo.SpecVO;

import java.util.List;

public interface SpecService extends IService<Spec> {

    /**
     * 根据规格名称查询规格列表
     *
     * @param name
     * @return
     */
    List<Spec> findSpecListByName(String name);

    /**
     * 根据类别名称查询规格
     *
     * @param name 类别名称
     * @return SpecVO
     */
    List<SpecVO> findSpecVOListByCategoryName(String name);
}
