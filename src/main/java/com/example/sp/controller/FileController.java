package com.example.sp.controller;

import com.example.sp.SqlSessionLoader;
import com.example.sp.constant.ConfigConstant;
import com.example.sp.model.Project;
import com.example.sp.model.ProjectFile;
import com.example.sp.request.DownloadRequest;
import com.example.sp.response.ResponseBean;
import com.example.sp.response.genFailedResponse;
import com.example.sp.response.genSuccessfulResponse;
import com.example.sp.util.UploadUtil;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.apache.ibatis.session.SqlSession;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *    
 *  @author luo tianyue 
 *  @Date 2020/6/17  
 *  @Time 18:48  
 */

@Controller
@RequestMapping("/file")
public class FileController {

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public Object upload(@RequestParam("upload_file") MultipartFile file, HttpServletRequest request) throws IOException {
        int project_id = request.getIntHeader("project_id");
        String user_id = request.getHeader("user_id");
        String user_type = request.getHeader("user_type");
        System.out.println(user_type);
        String fileName = file.getOriginalFilename();
        String fileExtensionName = fileName.substring(fileName.lastIndexOf(".") + 1);
        String name = fileName.substring(0, fileName.lastIndexOf("."));
        long size = file.getSize();
        long time = new Date().getTime();
        String id = String.valueOf(time);
        String saveFileName = id + '-' + name + "." + fileExtensionName;
        File fileDir = new File(ConfigConstant.UPLOAD_PATH);
        if (!fileDir.exists()){
            fileDir.setWritable(true);
            fileDir.mkdirs();
        }
        /**
         * 上传
         */
        File targetFile = new File(ConfigConstant.UPLOAD_PATH, saveFileName);
        try {
            file.transferTo(targetFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        ProjectFile projectFile = new ProjectFile(project_id, user_id, user_type, time, name, size, "/upload/" + saveFileName);
        SqlSession sqlSession = SqlSessionLoader.getSqlSession();
        try {
            sqlSession.delete("example.FileMapper.addFile", projectFile);
        } catch (Exception e) {
            System.out.println("上传文件失败" + e.getMessage());
            sqlSession.rollback();
            return new genFailedResponse(310, "上传文件失败", new Date());
        } finally {
            sqlSession.commit();
            sqlSession.close();
        }
        Map<String, String> result = new HashMap<>();
        result.put("url", String.format("http://localhost:8088/upload/%s", saveFileName));
        result.put("uid", id);
        result.put("name", fileName);                   
        return new genSuccessfulResponse(200, "上传成功", result);
    }

    @RequestMapping(value = "/getFiles", method = RequestMethod.GET)
    @ResponseBody
    public ResponseBean getFileList(@RequestParam(value = "pjID") int pjID) throws IOException {
        SqlSession sqlSession = SqlSessionLoader.getSqlSession();
        List<ProjectFile> fileList = sqlSession.selectList("example.FileMapper.getFiles", pjID);
        sqlSession.commit();

        for (ProjectFile projectFile : fileList) {
            Object count = sqlSession.selectOne("example.FileMapper.getDownloadCount", projectFile.getMaterials_id());
            if(count == null) {
                count = 0;
            }
            projectFile.setDownload_count((Integer) count);
        }
        sqlSession.close();
        return new genSuccessfulResponse(200, "获取成功", fileList);
    }

    @RequestMapping(value = "/download", method = RequestMethod.POST)
    @ResponseBody
    public ResponseBean download(@RequestBody DownloadRequest downloadRequest) throws IOException {
        SqlSession sqlSession = SqlSessionLoader.getSqlSession();
        System.out.println(downloadRequest.getUser_id());
        System.out.println(downloadRequest.getMaterials_id());
        try {
            sqlSession.delete("example.FileMapper.recordDownload", downloadRequest);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            sqlSession.rollback();
            return new genFailedResponse(310, "请求失败", new Date());
        } finally {
            sqlSession.commit();
            sqlSession.close();
        }
        return new genSuccessfulResponse(200, "请求成功", new Date());
    }

}
