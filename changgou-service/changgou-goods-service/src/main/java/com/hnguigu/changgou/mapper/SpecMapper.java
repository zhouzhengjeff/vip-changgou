package com.hnguigu.changgou.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hnguigu.changgou.entity.Spec;
import com.hnguigu.changgou.vo.SpecVO;

import java.util.List;

public interface SpecMapper extends BaseMapper<Spec> {

    List<SpecVO> findSpecVOsByCategoryName(String name);
}
