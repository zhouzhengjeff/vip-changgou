package com.hnguigu.changou.dto;

import java.io.Serializable;
import java.util.LinkedHashMap;

/**
 * 封装搜索条件对象
 */
public class SkuInfoQueryDTO extends SkuInfo implements Serializable {

    private static final long serialVersionUID = -1440853065714687731L;

    private String priceRange;

    private LinkedHashMap<String, String> orderMap;

    public String getPriceRange() {
        return priceRange;
    }

    public void setPriceRange(String priceRange) {
        this.priceRange = priceRange;
    }

    public LinkedHashMap<String, String> getOrderMap() {
        return orderMap;
    }

    public void setOrderMap(LinkedHashMap<String, String> orderMap) {
        this.orderMap = orderMap;
    }
}
