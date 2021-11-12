package com.hnguigu.changgou.controller;


import com.hnguigu.changgou.common.exception.ChanggouException;
import com.hnguigu.changgou.common.vo.Result;
import com.hnguigu.changgou.common.vo.StatusCode;
import com.hnguigu.changgou.entity.FastDFSFile;
import com.hnguigu.changgou.service.FastDFSFileService;
import com.hnguigu.changgou.util.FastDFSUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@CrossOrigin
public class FastDFSFileController {

    @Autowired
    private FastDFSFileService fastDFSFileService;

    @PostMapping("/fastdfs/upload")
    public Result<FastDFSFile> upload(MultipartFile file) {
        String url = null;
        try {
            String originalFilename = file.getOriginalFilename();
            byte[] content = file.getBytes();
            String extFileName = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);


            FastDFSFile fastDFSFile = new FastDFSFile(originalFilename, content, extFileName, "",
                    "");

            String[] uploadResults = this.fastDFSFileService.uploadFile(fastDFSFile);

            url = FastDFSUtils.getTrackerUrl() + uploadResults[0] + "/" + uploadResults[1];

        } catch (IOException e) {
            throw new ChanggouException("");
        }

        return new Result(true, StatusCode.OK, "文件上传成功", url);
    }
}
