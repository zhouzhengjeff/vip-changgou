package com.hnguigu.changgou.controller;


import com.hnguigu.changgou.common.vo.Result;
import com.hnguigu.changgou.common.vo.StatusCode;
import com.hnguigu.changgou.service.SpecService;
import com.hnguigu.changgou.vo.SpecVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class SpecController {

    @Autowired
    private SpecService specService;

    @GetMapping("/spec/findByName")
    public Result findByName(String name) {
        return new Result(true, StatusCode.OK, "规格查询成功", this.specService.findSpecListByName(name));
    }

    @GetMapping("/spec/findByCategoryName")
    public Result<SpecVO> findByCategoryName(String name) {
        return new Result(true, StatusCode.OK, "根据类别名称查询规格成功",
                this.specService.findSpecVOListByCategoryName(name));
    }
}
