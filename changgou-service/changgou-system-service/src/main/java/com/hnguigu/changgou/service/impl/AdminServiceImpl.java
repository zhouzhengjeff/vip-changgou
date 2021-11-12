package com.hnguigu.changgou.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hnguigu.changgou.common.exception.ChanggouException;
import com.hnguigu.changgou.entity.Admin;
import com.hnguigu.changgou.mapper.AdminMapper;
import com.hnguigu.changgou.service.AdminService;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

@Service
@Transactional
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {

    @Override
    public Admin addAdmin(Admin admin) {
        if (ObjectUtils.isEmpty(admin)) {
            throw new ChanggouException("");
        }

        String password = BCrypt.hashpw(admin.getPassword(), BCrypt.gensalt());
        admin.setPassword(password);
        this.save(admin);
        return admin;
    }

    @Override
    public Boolean authenticate(String loginName, String password) {
        Admin admin = this.getOne(Wrappers.lambdaQuery(Admin.class).eq(Admin::getLoginName,
                loginName));
        if (ObjectUtils.isEmpty(admin)) {
            return false;
        }
        if (BCrypt.checkpw(password, admin.getPassword())) {
            return true;
        }
        return false;
    }
}
