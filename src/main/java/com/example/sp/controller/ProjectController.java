package com.example.sp.controller;

import com.example.sp.SqlSessionLoader;
import com.example.sp.model.Project;
import com.example.sp.model.ProjectInfo;
import com.example.sp.model.Student;
import com.example.sp.request.JoinProjectRequest;
import com.example.sp.request.ProjectAddRequest;
import com.example.sp.request.UpdateProjectStatusRequest;
import com.example.sp.response.ResponseBean;
import com.example.sp.response.genFailedResponse;
import com.example.sp.response.genSuccessfulResponse;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/pj")
public class ProjectController {
    @RequestMapping(value = "/getPJList", method = RequestMethod.GET)
    @ResponseBody
    public ResponseBean getProjectList(@RequestParam(value = "userID") String userID,
                                       @RequestParam(value = "identity") String identity) throws IOException {
        System.out.println("userID: " + userID + "-----------------------------");
        System.out.println("identity: " + identity + "-----------------------------");
        SqlSession sqlSession = SqlSessionLoader.getSqlSession();
        List<ProjectInfo> pjs = new ArrayList<>();
        if (identity.equals("teacher")) {
            pjs = sqlSession.selectList("example.ProjectMapper.getMyPJ", userID);
            System.out.println(pjs.size());
        } else if (identity.equals("student")) {
            pjs = sqlSession.selectList("example.ProjectMapper.getStuPJ", userID);
        }

        for (ProjectInfo pi : pjs) {
            System.out.println("gg");
            pi.setCurMember((List<Student>) getMemberList(pi.getProject_id()).getData());
        }

        sqlSession.commit();
        sqlSession.close();
        return new genSuccessfulResponse(200, "获取项目列表成功", pjs);
    }

    @RequestMapping(value = "/createPJ", method = RequestMethod.POST)
    @ResponseBody
    public ResponseBean addProject(@RequestBody ProjectAddRequest projectAddRequest) throws IOException {
        SqlSession sqlSession = SqlSessionLoader.getSqlSession();

        try {
            sqlSession.insert("example.ProjectMapper.insertProject", projectAddRequest);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new genFailedResponse(310, "创建失败", new Date());
        } finally {
            sqlSession.commit();
            sqlSession.close();
        }
        return new genSuccessfulResponse(200, "创建成功", projectAddRequest);
    }

    @RequestMapping(value = "/joinPJ", method = RequestMethod.POST)
    @ResponseBody
    public ResponseBean joinPJ(@RequestParam(value = "stuID") String stuID,
                               @RequestParam(value = "pjID") int pjID) throws IOException {
        SqlSession sqlSession = SqlSessionLoader.getSqlSession();
        JoinProjectRequest jpr = new JoinProjectRequest(pjID, stuID);
        ProjectInfo info;
        List<Student> members;
        try {
            sqlSession.insert("example.ProjectMapper.joinProject", jpr);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new genFailedResponse(310, "加入失败", new Date());
        } finally {
            sqlSession.commit();
            sqlSession.close();
        }
        info = (ProjectInfo) getProjectDetails(pjID).getData();
        members = (List<Student>) getMemberList(pjID).getData();
        if (info.getStatus().equals("未开始")) {
            updateProjectStatus(pjID, "组队中");
        }
        if (members.size() == info.getMember_limit()) {
            updateProjectStatus(pjID, "进行中", new Date().getTime());
        }
        return new genSuccessfulResponse(200, "加入成功", new Date());
    }

    void updateProjectStatus(int pjID, String status) throws IOException {
        SqlSession sqlSession = SqlSessionLoader.getSqlSession();
        UpdateProjectStatusRequest upsr = new UpdateProjectStatusRequest(pjID, status);
        try {
            sqlSession.insert("example.ProjectMapper.updateProjectStatus", upsr);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            sqlSession.commit();
            sqlSession.close();
        }
    }

     void updateProjectStatus(int pjID, String status, long startTime) throws IOException {
        SqlSession sqlSession = SqlSessionLoader.getSqlSession();
        UpdateProjectStatusRequest upsr = new UpdateProjectStatusRequest(pjID, status, startTime);
        try {
            sqlSession.insert("example.ProjectMapper.updateProjectStatusAndTime", upsr);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            sqlSession.commit();
            sqlSession.close();
        }
    }

    @RequestMapping(value = "/searchPJ", method = RequestMethod.GET)
    @ResponseBody
    public ResponseBean searchPJ(@RequestParam(value = "keyword") String keyword) throws IOException {
        System.out.println(keyword);
        SqlSession sqlSession = SqlSessionLoader.getSqlSession();
        List<ProjectInfo> pjs = new ArrayList<>();
        pjs = sqlSession.selectList("example.ProjectMapper.searchProject", "%" + keyword + "%");
        for (ProjectInfo pi : pjs) {
            pi.setCurMember((List<Student>) getMemberList(pi.getProject_id()).getData());
        }
        sqlSession.commit();
        sqlSession.close();
        return new genSuccessfulResponse(200, "搜索成功", pjs);
    }

    @RequestMapping(value = "/getMemberList", method = RequestMethod.GET)
    @ResponseBody
    public ResponseBean getMemberList(@RequestParam(value = "pjID") int pjID) throws IOException {
        SqlSession sqlSession = SqlSessionLoader.getSqlSession();
        List<Student> members = new ArrayList<>();
        members = sqlSession.selectList("example.ProjectMapper.getMemberList", pjID);
        sqlSession.commit();
        sqlSession.close();
        return new genSuccessfulResponse(200, "获取成功", members);
    }

    @RequestMapping(value = "/getPJDetails", method = RequestMethod.GET)
    @ResponseBody
    public ResponseBean getProjectDetails(@RequestParam(value = "pjID") int pjID) throws IOException {
        SqlSession sqlSession = SqlSessionLoader.getSqlSession();
        ProjectInfo info = sqlSession.selectOne("example.ProjectMapper.getPJDetails", pjID);
        info.setCurMember((List<Student>) getMemberList(info.getProject_id()).getData());
        sqlSession.commit();
        sqlSession.close();
        return new genSuccessfulResponse(200, "请求成功", info);
    }

}
