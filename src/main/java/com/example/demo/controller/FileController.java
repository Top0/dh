package com.example.demo.controller;

import com.example.demo.common.exception.BusinessException;
import com.example.demo.common.response.RetResult;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import static com.example.demo.common.exception.RetCode.INTERNAL_SERVER_ERROR;

@RestController
@RequestMapping("/api/files")
public class FileController {

    private static final String UPLOAD_DIR = "uploads/";

    @PostMapping("/upload")
    public RetResult<String> uploadFile(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            throw new BusinessException(INTERNAL_SERVER_ERROR, "Please select a file to upload.");
        }

        try {
            // 确保存储目录存在
            Files.createDirectories(Paths.get(UPLOAD_DIR));

            // 将文件保存到本地文件系统或云存储服务
            Path path = Paths.get(UPLOAD_DIR + file.getOriginalFilename());
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

            return RetResult.success("File uploaded successfully: " + file.getOriginalFilename());
        } catch (Exception e) {
            throw new BusinessException(INTERNAL_SERVER_ERROR, "Failed to upload file: " + e.getMessage());
        }
    }


    @GetMapping("/download/{filename}")
    public void downloadFile(@PathVariable String filename, HttpServletResponse response) {
        Path filePath = Paths.get(UPLOAD_DIR, filename);
        if (!Files.exists(filePath)) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        try (FileInputStream inputStream = new FileInputStream(filePath.toFile());
             OutputStream outputStream = response.getOutputStream()) {

            // 设置响应头
            response.setContentType("application/octet-stream");
            response.setContentLengthLong(Files.size(filePath));
            response.setHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");

            // 禁用缓存
            response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
            response.setHeader("Pragma", "no-cache");
            response.setDateHeader("Expires", 0);

            // 将文件内容写入响应流
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            outputStream.flush();

        } catch (IOException e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            e.printStackTrace();
        }
    }
}
