package com.jd.edi.controller;

import com.jd.edi.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.UUID;

@RestController
@Slf4j
@RequestMapping("/common")
public class CommonController {


    @Value("${common.path:}")
    private String basePath;

    @PostMapping("/upload")
    public R<String> upload(MultipartFile file) {

        String originName = file.getOriginalFilename();
        String uuid = UUID.randomUUID().toString().substring(0, 13);
        String fileName = uuid + originName.substring(originName.lastIndexOf("."));
        log.info("upload file name: {}",basePath + File.separator + fileName);

        // 创建一个目录对象
        File dir = new File(basePath);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        try {
          // 将临时文件存到指定位置
          file.transferTo(new File(basePath + File.separator + fileName));
        } catch (IOException e) {
            log.error("上传文件【{}】失败： {}", fileName, e.getMessage());
        }
        return R.success(fileName);
    }


    @GetMapping("/download")
    public void download(String name, HttpServletResponse response) {
        try {
            // 输入流
            FileInputStream fileInputStream = new FileInputStream(new File(basePath + File.separator + name));
            // 输出流
            ServletOutputStream outputStream = response.getOutputStream();
            response.setContentType("image/jpeg");

            int len = 0;
            byte[] bytes = new byte[1024];
            while ((len = fileInputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, len);
                outputStream.flush();
            }

            // 关闭资源流
            outputStream.close();
            fileInputStream.close();
        }catch (Exception e) {
            log.error("下载文件失败【{}】, {}", name, e.getMessage());
        }
    }
}
