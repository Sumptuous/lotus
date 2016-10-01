package com.lotus.core.controller.admin;

import com.lotus.common.web.ResponseUtils;
import com.lotus.core.web.Constants;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import net.fckeditor.response.UploadResponse;
import org.apache.commons.io.FilenameUtils;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.Set;

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

    @RequestMapping("/uploadCK.do")
    public void uploadCK(HttpServletRequest request, HttpServletResponse response){

        //强转request  支持多个
        MultipartHttpServletRequest mr= (MultipartHttpServletRequest)request;
        //获取值  支持多个
        Map<String, MultipartFile> fileMap = mr.getFileMap();
        //遍历Map
        Set<Map.Entry<String, MultipartFile>> entrySet = fileMap.entrySet();
        for(Map.Entry<String, MultipartFile> entry : entrySet){
            //上传上来的图片
            MultipartFile pic = entry.getValue();
            //扩展名
            String ext = FilenameUtils.getExtension(pic.getOriginalFilename());

            //图片名生成策略
            Date date = new Date();
            String filename = String.valueOf(date.getTime());

            //实例化一个Jersey
            Client client = new Client();
            //保存数据库
            String path = "upload/" + filename + "." + ext;

            //另一台服务器的请求路径是?
            String url = Constants.IMAGE_URL  + path;
            //设置请求路径
            WebResource resource = client.resource(url);

            //发送开始  POST  GET   PUT
            try {
                resource.put(String.class, pic.getBytes());
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            //返回Url给Fck   fck-core...jar   ckeditor
            UploadResponse ok = UploadResponse.getOK(url);
            try {
                response.getWriter().print(ok);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
    }
}
