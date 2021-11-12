package com.hnguigu.changgou.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "tb_category")
public class Category implements Serializable {

    private static final long serialVersionUID = 7854412970750274164L;

    @TableId(value = "id", type = IdType.AUTO)
    @TableField(value = "id")
    private Integer id;//分类ID

    @TableField(value = "name")
    private String name;//分类名称

    @TableField(value = "goods_num")
    private Integer goodsNum;//商品数量

    @TableField(value = "is_show")
    private String isShow;//是否显示

    @TableField(value = "is_menu")
    private String isMenu;//是否导航

    @TableField(value = "seq")
    private Integer seq;//排序

    @TableField(value = "parent_id")
    private Integer parentId;//上级ID

    @TableField(value = "template_id")
    private Integer templateId;//模板ID
}
