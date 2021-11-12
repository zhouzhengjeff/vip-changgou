package com.hnguigu.changgou.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hnguigu.changgou.entity.Spec;
import com.hnguigu.changgou.mapper.SpecMapper;
import com.hnguigu.changgou.service.SpecService;
import com.hnguigu.changgou.vo.SpecVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@Transactional
public class SpecServiceImpl extends ServiceImpl<SpecMapper, Spec> implements SpecService {

    @Autowired
    private SpecMapper specMapper;

    @Override
    public List<Spec> findSpecListByName(String name) {
        return this.list(Wrappers.lambdaQuery(Spec.class).eq(Spec::getName, name));
    }

    @Override
    public List<SpecVO> findSpecVOListByCategoryName(String name) {
        if (StringUtils.isEmpty(name)) {
            throw new IllegalArgumentException("");
        }

        return this.specMapper.findSpecVOsByCategoryName(name);
    }
}
