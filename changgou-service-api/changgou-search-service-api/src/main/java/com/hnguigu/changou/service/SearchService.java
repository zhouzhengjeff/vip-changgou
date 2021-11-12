package com.hnguigu.changou.service;

import com.hnguigu.changou.dto.SkuInfo;
import com.hnguigu.changou.dto.SkuInfoQueryDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SearchService {

    void createIndex();

    void importSkuInfoList(List<SkuInfo> skuInfoList);

    List<SkuInfo> search(SkuInfoQueryDTO skuInfoQueryDTO);

    Page<SkuInfo> search(SkuInfoQueryDTO skuInfoQueryDTO, Integer pageNum,Integer pageSize);

}
