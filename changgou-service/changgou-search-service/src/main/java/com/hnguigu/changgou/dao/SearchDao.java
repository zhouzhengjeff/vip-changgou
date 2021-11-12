package com.hnguigu.changgou.dao;

import com.hnguigu.changou.dto.SkuInfo;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface SearchDao extends ElasticsearchRepository<SkuInfo, Long> {
}
