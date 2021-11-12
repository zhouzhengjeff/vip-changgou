package com.hnguigu.changgou.dto;

import com.hnguigu.changgou.entity.Sku;
import com.hnguigu.changgou.entity.Spu;

import java.io.Serializable;
import java.util.List;

public class GoodsDTO implements Serializable {

    private static final long serialVersionUID = 3270488666481338283L;
    private Spu spu;
    private List<Sku> skuList;

    public Spu getSpu() {
        return spu;
    }

    public void setSpu(Spu spu) {
        this.spu = spu;
    }

    public List<Sku> getSkuList() {
        return skuList;
    }

    public void setSkuList(List<Sku> skuList) {
        this.skuList = skuList;
    }
}
