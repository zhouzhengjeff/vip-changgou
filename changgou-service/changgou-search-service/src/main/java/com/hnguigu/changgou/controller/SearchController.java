package com.hnguigu.changgou.controller;

import com.hnguigu.changgou.common.vo.Result;
import com.hnguigu.changgou.common.vo.StatusCode;
import com.hnguigu.changou.dto.SkuInfo;
import com.hnguigu.changou.dto.SkuInfoQueryDTO;
import com.hnguigu.changou.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SearchController {

    @Autowired
    private SearchService searchService;

    @GetMapping("/search/search")
    public Result<List<SkuInfo>> search(@RequestBody SkuInfoQueryDTO skuInfoQueryDTO) {
        return new Result(true, StatusCode.OK, "关键字搜索成功",
                this.searchService.search(skuInfoQueryDTO));
    }

    @GetMapping("/search/searchPagination/{pageNum}/{pageSize}")
    public Result<Page<SkuInfo>> search(@RequestBody SkuInfoQueryDTO skuInfoQueryDTO,
                                        @PathVariable("pageNum") Integer pageNum,
                                        @PathVariable("pageSize") Integer pageSize) {
        return new Result(true, StatusCode.OK, "关键字搜索成功",
                this.searchService.search(skuInfoQueryDTO, pageNum, pageSize));
    }
}
