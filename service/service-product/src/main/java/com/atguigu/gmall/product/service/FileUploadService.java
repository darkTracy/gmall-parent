package com.atguigu.gmall.product.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {

    String upload(MultipartFile file) throws Exception;

}
