package com.hnguigu.changgou.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hnguigu.changgou.entity.FastDFSFile;
import com.hnguigu.changgou.mapper.FastDFSFileMapper;
import com.hnguigu.changgou.service.FastDFSFileService;
import com.hnguigu.changgou.util.FastDFSUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class FastDFSFileServiceImpl extends ServiceImpl<FastDFSFileMapper, FastDFSFile> implements FastDFSFileService {

    @Override
    public String[] uploadFile(FastDFSFile fastDFSFile) {
        String[] uploadResults = FastDFSUtils.upload(fastDFSFile);
        return uploadResults;
    }
}
