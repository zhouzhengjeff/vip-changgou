package com.hnguigu.changgou.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hnguigu.changgou.entity.Admin;

public interface AdminService extends IService<Admin> {

    Admin addAdmin(Admin admin);

    Boolean authenticate(String loginName, String password);
}
