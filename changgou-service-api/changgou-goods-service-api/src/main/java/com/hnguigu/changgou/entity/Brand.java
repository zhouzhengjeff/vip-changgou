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
@TableName(value = "tb_brand")
public class Brand implements Serializable {

    private static final long serialVersionUID = -3146398743282913151L;

    @TableId(value = "id", type = IdType.AUTO)
    @TableField(value = "id")
    private Integer id;
    private String name;
    private String image;
    private String letter;
    private Integer seq;
}
