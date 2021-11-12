package com.hnguigu.changgou.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hnguigu.changgou.entity.FastDFSFile;

public interface FastDFSFileService extends IService<FastDFSFile> {

    String[] uploadFile(FastDFSFile fastDFSFile);
}
