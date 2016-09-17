package com.lotus.core.controller.admin;

import com.lotus.common.web.ResponseUtils;
import com.lotus.core.web.Constants;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import org.apache.commons.io.FilenameUtils;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * 上传图片
 * @author wyy
 */
@Controller
@RequestMapping("/upload")
public class UploadController {

    @RequestMapping("/uploadPic.do")
    public void uploadPic(@RequestParam(required = false)MultipartFile file, HttpServletResponse response){

        //文件扩展名
        String ext = FilenameUtils.getExtension(file.getOriginalFilename());

        //图片名生成策略
        Date date = new Date();
        String filename = String.valueOf(date.getTime());

        //实例化一个Jersey
        Client client = new Client();
        //文件路径
        String path = "upload/" + filename + "." + ext;

        //服务器的请求路径
        String url = Constants.IMAGE_URL + path;
        WebResource resource = client.resource(url);

        //发送请求
        try {
            resource.put(String.class, file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

        //返回两个路径
        JSONObject jsonObject= new JSONObject();
        jsonObject.put("url", url);
        jsonObject.put("path", path);

        ResponseUtils.renderJson(response, jsonObject.toString());
    }
}
