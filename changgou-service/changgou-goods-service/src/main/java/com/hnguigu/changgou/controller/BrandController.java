package com.hnguigu.changgou.controller;


import com.hnguigu.changgou.common.vo.Result;
import com.hnguigu.changgou.common.vo.StatusCode;
import com.hnguigu.changgou.dto.BrandQueryDTO;
import com.hnguigu.changgou.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class BrandController {

    @Autowired
    private BrandService brandService;

    @GetMapping("/brand/findAll")
    public Result findAll() {
        return new Result(true, StatusCode.OK, "品牌查询成功", this.brandService.list());
    }

    @GetMapping("/brand/findPage/{pageNum}/{pageSize}")
    public Result findPage(@PathVariable("pageNum") Integer pageNum,
                           @PathVariable("pageSize") Integer pageSize) {
        return new Result(true, StatusCode.OK, "品牌查询成功", this.brandService.selectPage(pageNum,
                pageSize));
    }

    @GetMapping("/brand/findPageByCondition/{pageNum}/{pageSize}")
    public Result findPageByCondition(@PathVariable("pageNum") Integer pageNum,
                                      @PathVariable("pageSize") Integer pageSize,
                                      @RequestBody BrandQueryDTO brandQueryDTO) {
        return new Result(true, StatusCode.OK, "品牌查询成功", this.brandService.selectPage(pageNum,
                pageSize, brandQueryDTO));
    }
}
