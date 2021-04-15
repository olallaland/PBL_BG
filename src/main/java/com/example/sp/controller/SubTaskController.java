package com.example.sp.controller;

import com.example.sp.SqlSessionLoader;
import com.example.sp.model.HelpRequest;
import com.example.sp.model.Student;
import com.example.sp.model.SubTask;
import com.example.sp.request.MissionRequest;
import com.example.sp.response.ResponseBean;
import com.example.sp.response.genFailedResponse;
import com.example.sp.response.genSuccessfulResponse;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/subtask")
public class SubTaskController {
    public ResponseBean addTask(SubTask subtask) throws IOException {
        SqlSession sqlSession = SqlSessionLoader.getSqlSession();
        try {
            sqlSession.insert("example.TaskMapper.addTask", subtask);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            sqlSession.rollback();
            return new genFailedResponse(310, "创建失败", new Date());
        } finally {
            sqlSession.commit();
            sqlSession.close();
        }
        return new genSuccessfulResponse(200, "创建成功", new Date());
    }

    public HelpRequest getHelpRequest(int task_id) throws IOException {
        SqlSession sqlSession = SqlSessionLoader.getSqlSession();
        HelpRequest helpRequest = null;
        try {
            helpRequest = sqlSession.selectOne("example.TaskMapper.getHelpRequest", task_id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            sqlSession.commit();
            sqlSession.close();
        }
        if (helpRequest != null) {
            helpRequest.setTo_student_name(new UserController().findStuByID(helpRequest.getTo_student_id()).getName());
        }
        return helpRequest;
    }

    @RequestMapping(value = "/getTaskList", method = RequestMethod.GET)
    @ResponseBody
    public ResponseBean getTaskList(@RequestParam(value = "pjID") int pjID) throws IOException {
        SqlSession sqlSession = SqlSessionLoader.getSqlSession();
        List<SubTask> subTasks = sqlSession.selectList("example.TaskMapper.getTaskList", pjID);
        UserController userController = new UserController();
        for (SubTask subTask : subTasks) {
            if (subTask.getStudent_id() != null) {
                subTask.setStudent_name(userController.findStuByID(subTask.getStudent_id()).getName());
            }
        }
        sqlSession.close();
        return new genSuccessfulResponse(200, "成功获取任务列表", subTasks);
    }

    @RequestMapping(value = "/takeTask", method = RequestMethod.POST)
    @ResponseBody
    public ResponseBean takeTask(@RequestBody SubTask subTask) throws IOException {
        SqlSession sqlSession = SqlSessionLoader.getSqlSession();
        try {
            sqlSession.update("example.TaskMapper.stuTakeTask", subTask);
        } catch (Exception e) {
            sqlSession.rollback();
            System.out.println(e.getMessage());
            return new genFailedResponse(310, "操作失败", new Date());
        } finally {
            sqlSession.commit();
            sqlSession.close();
        }

        return new genSuccessfulResponse(200, "操作成功", new Date());
    }

    @RequestMapping(value = "/getStuTasks", method = RequestMethod.POST)
    @ResponseBody
    public ResponseBean getStuTasks(@RequestBody SubTask subTask) throws IOException {
        SqlSession sqlSession = SqlSessionLoader.getSqlSession();
        List<SubTask> subTasks = new ArrayList<>();
        try {
            subTasks = sqlSession.selectList("example.TaskMapper.getStuTasks", subTask);
        } catch (Exception e) {
            sqlSession.rollback();
            System.out.println(e.getMessage());
            return new genFailedResponse(310, "获取失败", new Date());
        } finally {
            sqlSession.commit();
        }

        for (SubTask subTask1 : subTasks) {
            subTask1.setHelpRequest(getHelpRequest(subTask1.getTask_id()));
        }
        sqlSession.close();
        return new genSuccessfulResponse(200, "获取成功", subTasks);
    }

    @RequestMapping(value = "/submitTask", method = RequestMethod.GET)
    @ResponseBody
    public ResponseBean submitTask(@RequestParam(value = "taskID") int taskID) throws IOException {
        SqlSession sqlSession = SqlSessionLoader.getSqlSession();
        try {
            sqlSession.update("example.TaskMapper.submitTask", taskID);
        } catch (Exception e) {
            sqlSession.rollback();
            System.out.println(e.getMessage());
            return new genFailedResponse(310, "操作失败", new Date());
        } finally {
            sqlSession.commit();
            sqlSession.close();
        }

        return new genSuccessfulResponse(200, "操作成功", new Date());
    }

    @RequestMapping(value = "/sendHelpReq", method = RequestMethod.POST)
    @ResponseBody
    public ResponseBean sendHelpReq(@RequestBody HelpRequest helpRequest) throws IOException {
        SqlSession sqlSession = SqlSessionLoader.getSqlSession();
        try {
            sqlSession.update("example.TaskMapper.addHelpRequest", helpRequest);
        } catch (Exception e) {
            sqlSession.rollback();
            System.out.println(e.getMessage());
            return new genFailedResponse(310, "操作失败", new Date());
        } finally {
            sqlSession.commit();
            sqlSession.close();
        }

        return new genSuccessfulResponse(200, "操作成功", new Date());
    }

    @RequestMapping(value = "/getReceivedHelpReq", method = RequestMethod.GET)
    @ResponseBody
    public ResponseBean getReceivedHelpReq(@RequestParam(value = "stuID") String stuID,
                                           @RequestParam(value = "pjID") int pjID) throws IOException {
        HashMap<String, Object> map = new HashMap<>();
        map.put("stuID", stuID);
        map.put("pjID", pjID);
        List<SubTask> subTasks = new ArrayList<>();
        SqlSession sqlSession = SqlSessionLoader.getSqlSession();
        subTasks = sqlSession.selectList("example.TaskMapper.getReceivedRequest", map);

        UserController userController = new UserController();
        for (SubTask subTask1 : subTasks) {
            subTask1.setHelpRequest(getHelpRequest(subTask1.getTask_id()));
            subTask1.setStudent_name(userController.findStuByID(subTask1.getStudent_id()).getName());
        }
        return new genSuccessfulResponse(200, "获取成功", subTasks);
    }

    @RequestMapping(value = "/acceptRequest", method = RequestMethod.POST)
    @ResponseBody
    public ResponseBean acceptRequest(@RequestBody HelpRequest helpRequest) throws IOException {
        helpRequest.setRequest_status("已接受");
        SqlSession sqlSession = SqlSessionLoader.getSqlSession();
        try {
            sqlSession.update("example.TaskMapper.updateRequestStatus", helpRequest);
            sqlSession.update("example.TaskMapper.updateTaskShared", helpRequest);
        } catch (Exception e) {
            sqlSession.rollback();
            System.out.println(e.getMessage());
            return new genFailedResponse(310, "操作失败", new Date());
        } finally {
            sqlSession.commit();
            sqlSession.close();
        }
        return new genSuccessfulResponse(200, "操作成功", new Date());
    }

    @RequestMapping(value = "/rejectRequest", method = RequestMethod.POST)
    @ResponseBody
    public ResponseBean rejectRequest(@RequestBody HelpRequest helpRequest) throws IOException {
        helpRequest.setRequest_status("已拒绝");
        SqlSession sqlSession = SqlSessionLoader.getSqlSession();
        try {
            sqlSession.update("example.TaskMapper.updateRequestStatus", helpRequest);
        } catch (Exception e) {
            sqlSession.rollback();
            System.out.println(e.getMessage());
            return new genFailedResponse(310, "操作失败", new Date());
        } finally {
            sqlSession.commit();
            sqlSession.close();
        }
        return new genSuccessfulResponse(200, "操作成功", new Date());
    }
}
