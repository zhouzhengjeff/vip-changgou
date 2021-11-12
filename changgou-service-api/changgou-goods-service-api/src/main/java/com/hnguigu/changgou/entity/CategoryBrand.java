package com.hnguigu.changgou.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "tb_category_brand")
public class CategoryBrand implements Serializable {

    private static final long serialVersionUID = 4767390588988759576L;

//    @TableId(value = "category_id")
    private Integer categoryId;

//    @TableId(value = "brand_id")
    private Integer brandId;
}
