package com.example.train.utils;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 用于处理文件的传输和下载
 */
public class FileUtil {


    /**
     * 文件的上传
     * @param prefix
     * @param file
     * @return
     */
    public static String uploadDocument(String prefix,MultipartFile file){
        if (file.isEmpty()){
            return "请选择文件";
        }
        String originalFilename = file.getOriginalFilename();
        String fileType = originalFilename
                .substring(originalFilename.lastIndexOf(".") + 1, originalFilename.length());
//        System.out.println(originalFilename+"文件类型"+fileType);

//        String path = "D:/OnlineTraining/src/main/resources/files/";
        Path filePath = Paths.get(prefix + originalFilename);
        try {
            byte[] fileBytes = file.getBytes();
            Files.write(filePath, fileBytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "上传成功";
    }

    /**
     * 根据路径删除文件
     * @param path
     * @return
     */
    public static boolean deleteFile(String path){
        File file = new File(path);
        if (file.exists()){
            file.delete();
            return true;
        }
        else
            return false;
    }

    /**
     * 传输文件
     * @param path
     * @param response
     */
    public static void download(String path, HttpServletResponse response) {
        try {
    //     if(StringUtils.isNotBlank(path)){
          File file = new File(path);
          // 取得文件名。
          String fileName = file.getName();
          // 以流的形式下载文件。
          InputStream fis = new BufferedInputStream(new FileInputStream(path));
          byte[] buffer = new byte[fis.available()];
          fis.read(buffer);
          fis.close();

          //清空response
          response.reset();
          String uncod= URLDecoder.decode(fileName,"UTF-8");
          fileName = new String(uncod.getBytes("UTF-8"), "iso-8859-1");

          response.setHeader("Content-Disposition", "attachment;filename=".concat(String.valueOf(fileName)));
          // 设置response的Header
          response.addHeader("Content-Length", "" + file.length());

          OutputStream toClient = new BufferedOutputStream(
                                    response.getOutputStream());
          toClient.write(buffer);
          toClient.flush();
          toClient.close();
    //     }
          // path是指欲下载的文件的路径。
        } catch (IOException ex) {
          ex.printStackTrace();
        }
    }
}
