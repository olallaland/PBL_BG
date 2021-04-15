package com.example.sp.controller;

import com.example.sp.SqlSessionLoader;
import com.example.sp.model.*;
import com.example.sp.request.*;
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
@RequestMapping("/team")
public class TeamController {
    @RequestMapping(value = "/chooseTag", method = RequestMethod.POST)
    @ResponseBody
    public ResponseBean chooseTag(@RequestBody UpdateTeamMemberRequest updateTeamMemberRequest) throws IOException {
        SqlSession sqlSession = SqlSessionLoader.getSqlSession();
        System.out.println(updateTeamMemberRequest.getRole_tag());
        System.out.println(updateTeamMemberRequest.getStudent_id());
        try {
            sqlSession.insert("example.TeamMapper.updateRoleTag", updateTeamMemberRequest);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new genFailedResponse(310, "更新失败", new Date());
        } finally {
            sqlSession.commit();
            sqlSession.close();
        }
        return new genSuccessfulResponse(200, "更新成功", new Date());
    }

    @RequestMapping(value = "/getOthersTag", method = RequestMethod.GET)
    @ResponseBody
    public ResponseBean getOthersTag(@RequestParam(value = "pjID") int pjID) throws IOException {

        SqlSession sqlSession = SqlSessionLoader.getSqlSession();
        List<TeamMember> memberList = new ArrayList<>();
        try {
            memberList = sqlSession.selectList("example.TeamMapper.getMemberTag", pjID);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new genFailedResponse(310, "获取失败", new Date());
        } finally {
            sqlSession.commit();
            sqlSession.close();
        }
        return new genSuccessfulResponse(200, "获取成功", memberList);
    }

    @RequestMapping(value = "/raiseKeyPoint", method = RequestMethod.POST)
    @ResponseBody
    public ResponseBean raiseKeyPoint(@RequestBody KeyPointAddRequest keyPointAddRequest) throws IOException {
        SqlSession sqlSession = SqlSessionLoader.getSqlSession();
        try {
            sqlSession.insert("example.TeamMapper.raiseKeyPoint", keyPointAddRequest);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new genFailedResponse(310, "请求失败", new Date());
        } finally {
            sqlSession.commit();
            sqlSession.close();
        }
        return new genSuccessfulResponse(200, "请求成功", new Date());
    }

    @RequestMapping(value = "/getKeyPointList", method = RequestMethod.GET)
    @ResponseBody
    public ResponseBean getKeyPointList(@RequestParam(value = "pjID") int pjID) throws IOException {
        SqlSession sqlSession = SqlSessionLoader.getSqlSession();
        List<KeyPoint> keyPoints = new ArrayList<>();
        try {
            keyPoints = sqlSession.selectList("example.TeamMapper.getKeyPointList", pjID);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new genFailedResponse(310, "获取失败", new Date());
        } finally {
            sqlSession.commit();
            sqlSession.close();
        }

        for (KeyPoint keyPoint : keyPoints) {
            List<Student> students = getLikedList(keyPoint.getProject_id(), "key", keyPoint.getKey_point_id());
            keyPoint.setLike_count(students.size());
            keyPoint.setLikedList(students);
        }
        return new genSuccessfulResponse(200, "获取成功", keyPoints);
    }

    List<Student> getLikedList(int pjID, String type, int type_id) throws IOException {
        SqlSession sqlSession = SqlSessionLoader.getSqlSession();
        List<Student> students = new ArrayList<>();
        GetLikedListRequest getLikedListRequest = new GetLikedListRequest(type, type_id, pjID);
        try {
            students = sqlSession.selectList("example.TeamMapper.getLikedList", getLikedListRequest);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            sqlSession.commit();
            sqlSession.close();
        }
        return students;
    }

    @RequestMapping(value = "/likeAction", method = RequestMethod.POST)
    @ResponseBody
    public ResponseBean likeAction(@RequestBody HandleLikeRequest handleLikeRequest) throws IOException {
        SqlSession sqlSession = SqlSessionLoader.getSqlSession();
        try {
            sqlSession.insert("example.TeamMapper.likeAction", handleLikeRequest);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new genFailedResponse(310, "请求失败", new Date());
        } finally {
            sqlSession.commit();
        }

        int key_point_id = handleLikeRequest.getType_id();
        ProjectInfo projectInfo = (ProjectInfo) new ProjectController().getProjectDetails(handleLikeRequest.getProject_id()).getData();
        KeyPoint keyPoint = getKeyPoint(key_point_id);
        if (projectInfo.getMember_limit() - 1 == keyPoint.getLike_count()) {
            try {
                sqlSession.update("example.TeamMapper.updateKeyPoint", key_point_id);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            } finally {
                sqlSession.commit();
                sqlSession.close();
            }
        }

        List<KeyPoint> keyPoints = (List<KeyPoint>) getKeyPointList(handleLikeRequest.getProject_id()).getData();
        int qualifiedCnt = 0;
        for (KeyPoint keyPoint1 : keyPoints) {
            if (keyPoint1.getIs_key() == 1) {
                qualifiedCnt++;
            }
        }

        if (qualifiedCnt == projectInfo.getKey_point_count()) {
            SubTaskController subTaskController = new SubTaskController();
            for (KeyPoint keyPoint2 : keyPoints) {
                if (keyPoint2.getIs_key() == 1) {
                    SubTask subTask = new SubTask(keyPoint2.getProject_id(), keyPoint2.getContent(),
                            projectInfo.getStart_time() + projectInfo.getDuration() * 86400000);
                    subTaskController.addTask(subTask);
                }
            }
        }
        return new genSuccessfulResponse(200, "请求成功", new Date());
    }

    @RequestMapping(value = "/getRateOfProgress", method = RequestMethod.GET)
    @ResponseBody
    public ResponseBean getRateOfProgress(@RequestParam(value = "pjID") int pjID) throws IOException {
        ProjectInfo projectInfo = (ProjectInfo) new ProjectController().getProjectDetails(pjID).getData();
        List<KeyPoint> keyPoints = (List<KeyPoint>) getKeyPointList(pjID).getData();
        int qualifiedCnt = 0;
        for (KeyPoint keyPoint : keyPoints) {
            if (keyPoint.getIs_key() == 1) {
                qualifiedCnt++;
            }
        }
        double rateOfProgress = qualifiedCnt / (double) projectInfo.getKey_point_count();
        return new genSuccessfulResponse(200, "获取成功", rateOfProgress);
    }

    @RequestMapping(value = "/getMembers", method = RequestMethod.GET)
    @ResponseBody
    public ResponseBean getMemberList(@RequestParam(value = "pjID") int pjID) throws IOException {
        List<Student> members = new ArrayList<>();
        SqlSession sqlSession = SqlSessionLoader.getSqlSession();
        members = sqlSession.selectList("example.TeamMapper.getMembers", pjID);
        sqlSession.commit();
        sqlSession.close();
        return new genSuccessfulResponse(200, "请求成功", members);
    }

    public KeyPoint getKeyPoint(int key_point_id) throws IOException {
        SqlSession sqlSession = SqlSessionLoader.getSqlSession();
        KeyPoint keyPoint;
        try {
            keyPoint = sqlSession.selectOne("example.TeamMapper.getKeyPoint", key_point_id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        } finally {
            sqlSession.commit();
            sqlSession.close();
        }
        return keyPoint;
    }
}
