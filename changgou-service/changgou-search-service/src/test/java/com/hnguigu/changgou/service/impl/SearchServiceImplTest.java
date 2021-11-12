package com.hnguigu.changgou.service.impl;

import com.hnguigu.changou.service.SearchService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class SearchServiceImplTest {

    @Autowired
    private SearchService searchService;

    @Test
    public void createIndex() {
        this.searchService.createIndex();
    }
}
