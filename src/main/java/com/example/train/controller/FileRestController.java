package com.example.train.controller;

import com.example.train.pojo.document.Document;
import com.example.train.service.DocumentService;
import com.example.train.utils.FileUtil;
import com.example.train.utils.JSONUtil;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 文件传输
 */
@RestController
@RequestMapping("/file")
@AllArgsConstructor
public class FileRestController {


    @Autowired
    private final NonStaticResourceHttpRequestHandler nonStaticResourceHttpRequestHandler;

    @Autowired
    private DocumentService documentService;

    /**
     * 播放视频和音频
     * @param request
     * @param response
     * @throws Exception
     */
    @GetMapping("/video")
    public void videoPreview(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        String docId = request.getParameter("id");
        System.out.println(docId+"请求ID");
        Document document = documentService.findByResId(Integer.parseInt(docId));
        System.out.println(document);
        //假如我把视频1.mp4放在了static下的video文件夹里面
        //sourcePath 是获取resources文件夹的绝对地址
        //realPath 即是视频所在的磁盘地址
//        String sourcePath = ClassUtils.getDefaultClassLoader()
//                .getResource("")
//                .getPath()
//                .substring(1);
        String realPath = "/www/wwwroot/files/video/"+document.getPath();
        System.out.println(realPath);

        Path filePath = Paths.get(realPath );
        if (Files.exists(filePath)) {
            String mimeType = Files.probeContentType(filePath);
            if (!StringUtils.isEmpty(mimeType)) {
                response.setContentType(mimeType);
            }
            request.setAttribute(NonStaticResourceHttpRequestHandler.ATTR_FILE, filePath);
            nonStaticResourceHttpRequestHandler.handleRequest(request, response);
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            response.setCharacterEncoding(StandardCharsets.UTF_8.toString());
        }
    }

    @RequestMapping(value = "/upload")
    public String uploadDocument(@RequestParam("file")MultipartFile file,
                                 @RequestParam("idNumber") Integer idNumber){
        String prefix = "D:/OnlineTraining/src/main/resources/files/";
        String message = FileUtil.uploadDocument(prefix,file);
        System.out.println(idNumber);
        return JSONUtil.JSONSuccessToString(message);
    }

}