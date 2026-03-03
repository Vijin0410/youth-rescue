package com.rescue.common.util;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.io.ClassPathResource;

import java.io.InputStream;

/**
 * 文件工具类
 * @author wanggang
 * @date 2024/7/16
 */
public class FileUtil {

    /**
     * 获取静态目录下的文件
     * @param templatePath 文件地址
     */
    public static void getStaticFile(String templatePath,HttpServletResponse response){
        ServletOutputStream outputStream = null;
        try {
            ClassPathResource resource = new ClassPathResource(templatePath);
            InputStream stream = resource.getInputStream();
            // 将InputStream内容写入到response的输出流中
            outputStream = response.getOutputStream();
            byte[] buffer = new byte[4096]; // 缓冲区
            int bytesRead;
            while ((bytesRead = stream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            // 关闭流
            outputStream.flush();
            outputStream.close();
            stream.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
