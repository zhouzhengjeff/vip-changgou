package com.hnguigu.changgou.service.impl;

import com.hnguigu.changgou.common.exception.ChanggouException;
import com.hnguigu.changgou.dao.SearchDao;
import com.hnguigu.changou.dto.SkuInfo;
import com.hnguigu.changou.dto.SkuInfoQueryDTO;
import com.hnguigu.changou.service.SearchService;
import org.apache.commons.collections.MapUtils;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.*;

@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    private SearchDao searchDao;

    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Override
    public void createIndex() {
        this.elasticsearchRestTemplate.createIndex(SkuInfo.class);
    }

    @Override
    public void importSkuInfoList(List<SkuInfo> skuInfoList) {
        if (CollectionUtils.isEmpty(skuInfoList)) {
            throw new ChanggouException("");
        }

        this.searchDao.saveAll(skuInfoList);
    }

    @Override
    public List<SkuInfo> search(SkuInfoQueryDTO skuInfoQueryDTO) {
        List<SkuInfo> skuInfoList = new ArrayList<>();
        Iterable<SkuInfo> iterable = null;

        if (ObjectUtils.isEmpty(skuInfoQueryDTO)) {
            iterable = this.searchDao.findAll();
        } else {
            //  关键字查询
            String name = skuInfoQueryDTO.getName();
//            String categoryName = skuInfoQueryDTO.getCategoryName();
            String brandName = skuInfoQueryDTO.getBrandName();
            Map<String, Object> specMap = skuInfoQueryDTO.getSpecMap();
            String priceRange = skuInfoQueryDTO.getPriceRange();
            LinkedHashMap<String, String> orderMap = skuInfoQueryDTO.getOrderMap();

            NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
            BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

            // 关键字搜索
            if (!StringUtils.isEmpty(name)) {
                MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("name", name);
                boolQueryBuilder.must(matchQueryBuilder);
            } else {
                iterable = this.searchDao.findAll();
            }

            // 品牌过滤
            if (!StringUtils.isEmpty(brandName)) {
                TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("brandName", brandName);
                boolQueryBuilder.must(termQueryBuilder);
            }

            // 规格过滤
            // {"spec_网络制式"："电信4G","spec_显示屏尺寸":"4.0‐4.9英寸"}
            if (MapUtils.isNotEmpty(specMap)) {
                for (Iterator<Map.Entry<String, Object>> iterator =
                     specMap.entrySet().iterator(); iterator.hasNext(); ) {
                    Map.Entry<String, Object> entry = iterator.next();
                    String key = entry.getKey();
                    Object value = entry.getValue();

                    TermQueryBuilder termQueryBuilder =
                            QueryBuilders.termQuery("specMap." + key.split("_")[1] + ".keyword"
                                    , value);

                    boolQueryBuilder.must(termQueryBuilder);
                }
            }

            // 价格区间
            if (!StringUtils.isEmpty(priceRange)) {
                if (priceRange.contains("-")) {

                    String[] priceRangeArray = priceRange.split("-");
                    String beginPriceString = priceRangeArray[0];
                    String endPriceString = priceRangeArray[1];

                    long beginPrice = Long.parseLong(beginPriceString);
                    long endPrice = Long.parseLong(endPriceString);

                    RangeQueryBuilder rangeQueryBuilder =
                            QueryBuilders.rangeQuery("price").gte(beginPrice).lte(endPrice);
                    boolQueryBuilder.must(rangeQueryBuilder);
                } else {
                    RangeQueryBuilder rangeQueryBuilder =
                            QueryBuilders.rangeQuery("price").gte(Long.parseLong(priceRange));
                    boolQueryBuilder.must(rangeQueryBuilder);
                }
            }

            // 排序
            FieldSortBuilder fieldSortBuilder = null;
            if (MapUtils.isNotEmpty(orderMap)) {
                for (Iterator<Map.Entry<String, String>> iterator =
                     orderMap.entrySet().iterator(); iterator.hasNext(); ) {
                    Map.Entry<String, String> entry = iterator.next();
                    String orderField = entry.getKey();
                    String orderValue = entry.getValue();

                    fieldSortBuilder =
                            SortBuilders.fieldSort(orderField).order(orderValue.equals("ASC") ?
                                    SortOrder.ASC : SortOrder.DESC);
                }
            }

            // 高亮
            HighlightBuilder.Field field = new HighlightBuilder.Field("name")
                    .preTags("<span style='color:red'>").postTags("</span>");
            NativeSearchQuery nativeSearchQuery =
                    nativeSearchQueryBuilder.withQuery(boolQueryBuilder)
                                            .withSort(fieldSortBuilder)
                                            .withHighlightFields(field)
                                            .build();

            iterable = this.searchDao.search(nativeSearchQuery);
        }

        Iterator<SkuInfo> iterator = iterable.iterator();
        while (iterator.hasNext()) {
            SkuInfo skuInfo = iterator.next();
            skuInfoList.add(skuInfo);
        }

        return skuInfoList;
    }

    @Override
    public Page<SkuInfo> search(SkuInfoQueryDTO skuInfoQueryDTO, Integer pageNum,
                                Integer pageSize) {
        Page<SkuInfo> page = null;
        PageRequest pageRequest = PageRequest.of(pageNum - 1, pageSize);

        List<SkuInfo> skuInfoList = new ArrayList<>();
        Iterable<SkuInfo> iterable = null;

        if (ObjectUtils.isEmpty(skuInfoQueryDTO)) {
            page = this.searchDao.findAll(pageRequest);
        } else {
            //  关键字查询
            String name = skuInfoQueryDTO.getName();
            if (!StringUtils.isEmpty(name)) {
                MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("name", name);
                page = this.searchDao.search(matchQueryBuilder, pageRequest);
            } else {
                page = this.searchDao.findAll(pageRequest);
            }
        }

        return page;
    }
}
