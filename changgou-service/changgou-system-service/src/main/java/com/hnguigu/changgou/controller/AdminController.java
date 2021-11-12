package com.hnguigu.changgou.controller;


import com.hnguigu.changgou.common.vo.Result;
import com.hnguigu.changgou.common.vo.StatusCode;
import com.hnguigu.changgou.entity.Admin;
import com.hnguigu.changgou.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/admin/add")
    public Result<Admin> add(@RequestBody Admin admin) {
        return new Result(true, StatusCode.OK, "管理员添加成功", this.adminService.addAdmin(admin));
    }

    @PostMapping("/admin/login")
    public Result<Admin> login(String loginName, String password) {
        Boolean flag = this.adminService.authenticate(loginName, password);
        if (flag) {
            return new Result(true, StatusCode.OK, "登录成功", null);
        }

        return new Result(false, StatusCode.LOGINERROR, "登录失败", null);
    }


}
