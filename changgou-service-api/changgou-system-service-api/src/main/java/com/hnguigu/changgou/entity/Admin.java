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
@TableName(value = "tb_admin")
public class Admin implements Serializable {

    private static final long serialVersionUID = 7507230043727402965L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField(value = "login_name")
    private String loginName;

    @TableField(value = "password")
    private String password;

    @TableField(value = "status")
    private String status;
}
