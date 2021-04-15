package com.example.sp.controller;

import com.example.sp.SqlSessionLoader;
import com.example.sp.constant.ConfigConstant;
import com.example.sp.model.Student;
import com.example.sp.model.Teacher;
import com.example.sp.model.User;
import com.example.sp.request.RegisterRequest;
import com.example.sp.request.UserRegisterRequest;
import com.example.sp.response.ResponseBean;
import com.example.sp.response.genFailedResponse;
import com.example.sp.response.genSuccessfulResponse;
import com.example.sp.util.UploadUtil;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    public Student findStuByID(String student_id) throws IOException {
        SqlSession sqlSession = SqlSessionLoader.getSqlSession();
        return sqlSession.selectOne("example.UserMapper.findStuByID", student_id);
    }

     public Student findTeacherByID(String teacher_id) throws IOException {
        SqlSession sqlSession = SqlSessionLoader.getSqlSession();
        return sqlSession.selectOne("example.UserMapper.findTeacherByID", teacher_id);
    }

    @RequestMapping(value = "/findUserByID/{type}/{userID}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseBean register(@PathVariable String type,
                                 @PathVariable String userID) throws IOException {

        SqlSession sqlSession = SqlSessionLoader.getSqlSession();
        if (type.equals("student")) {
            Student student = sqlSession.selectOne("example.UserMapper.findStudentByUsername", userID);
            if (student != null) {
                return new genSuccessfulResponse(200, "success：成功", student);
            } else {
                return new genFailedResponse(310, "error: 找不到该用户", new Date());
            }
        }

        if (type.equals("teacher")) {
            Teacher teacher = sqlSession.selectOne("example.UserMapper.findTeacherByUsername", userID);
            if (teacher == null) {
                return new genFailedResponse(310, "error: 找不到该用户", new Date());
            } else {
                return new genSuccessfulResponse(200, "success：成功", teacher);
            }
        }
        sqlSession.close();
        return new genFailedResponse(310, "error:未知错误", new Date());
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public ResponseBean login(@RequestBody User user) throws IOException {
        System.out.println(user.getUsername());
        String type = user.getIdentity();
        String password = user.getPassword();
        String username = user.getUsername();
        SqlSession sqlSession = SqlSessionLoader.getSqlSession();
        if (type.equals("student")) {
            Student stu = sqlSession.selectOne("example.UserMapper.findStudentByUsername", username);
            if(stu == null) {
                return new genFailedResponse(310, "该学生用户不存在", new Date());
            }
            if (stu.getPassword().equals(password)) {
                sqlSession.close();
                return new genSuccessfulResponse(200, "登录成功", stu);
            } else {
                sqlSession.close();
                return new genFailedResponse(310, "密码错误", new Date());
            }
        }
        if (type.equals("teacher")) {
            Teacher tea = sqlSession.selectOne("example.UserMapper.findTeacherByUsername", username);
            if(tea == null) {
                return new genFailedResponse(310, "该教师用户不存在", new Date());
            }
            if (tea.getPassword().equals(password)) {
                sqlSession.close();
                return new genSuccessfulResponse(200, "登录成功", tea);
            } else {
                sqlSession.close();
                return new genFailedResponse(310, "密码错误", new Date());
            }
        }
        sqlSession.close();
        return new genSuccessfulResponse(200, "登录成功", new String("root"));

    }

}
