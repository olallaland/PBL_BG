package com.example.sp.controller;


import com.example.sp.SqlSessionLoader;
import com.example.sp.model.Discussion;
import com.example.sp.model.Discussion_answer;
import com.example.sp.model.Reply;
import com.example.sp.model.Student;
import com.example.sp.request.HandleLikeRequest;
import com.example.sp.response.ResponseBean;
import com.example.sp.response.genFailedResponse;
import com.example.sp.response.genSuccessfulResponse;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/discussion")
public class DiscussionController {
    @RequestMapping(value = "/addDiscussion", method = RequestMethod.POST)
    @ResponseBody
    public ResponseBean addDiscussion(@RequestBody Discussion discussion) throws IOException {
        SqlSession sqlSession = SqlSessionLoader.getSqlSession();
        try {
            sqlSession.update("example.DiscussionMapper.addDiscussion", discussion);
        } catch (Exception e) {
            sqlSession.rollback();
            System.out.println(e.getMessage());
            return new genFailedResponse(310, "发起讨论失败", new Date());
        } finally {
            sqlSession.commit();
            sqlSession.close();
        }
        return new genSuccessfulResponse(200, "发起讨论成功", discussion);
    }

    @RequestMapping(value = "/getDiscussionList", method = RequestMethod.GET)
    @ResponseBody
    public ResponseBean getDiscussionList(@RequestParam(value = "pjID") int pjID,
                                          @RequestParam(value = "stuID") String stuID) throws IOException {
        SqlSession sqlSession = SqlSessionLoader.getSqlSession();
        List<Discussion> discussionList = sqlSession.selectList("example.DiscussionMapper.getDiscussionList", pjID);

        UserController userController = new UserController();
        TeamController teamController = new TeamController();
        for (Discussion discussion : discussionList) {
            discussion.setStudent_name(userController.findStuByID(discussion.getStudent_id()).getName());
            discussion.setLikeList(teamController.getLikedList(pjID, "discussion", discussion.getDiscussion_id()));
            discussion.setLikesCount(discussion.getLikeList().size());
            discussion.setLiked(false);
            for (Student stu : discussion.getLikeList()) {
                if (stu.getStudent_id().equals(stuID)) {
                    discussion.setLiked(true);
                    break;
                }
            }
        }
        return new genSuccessfulResponse(200, "获取成功", discussionList);
    }


    @RequestMapping(value = "/getReplyList", method = RequestMethod.GET)
    @ResponseBody
    public ResponseBean getReplyList(@RequestParam(value = "discussionID") int discussionID,
                                     @RequestParam(value = "stuID") String stuID) throws IOException {
        SqlSession sqlSession = SqlSessionLoader.getSqlSession();
        List<Reply> replyList = sqlSession.selectList("example.DiscussionMapper.getReplyList", discussionID);

//        UserController userController = new UserController();
//        TeamController teamController = new TeamController();
//        for (Reply reply : replyList) {
//            reply.setStudent_name(userController.findStuByID(reply.getStudent_id()).getName());
//            reply.setLikeList(teamController.getLikedList(reply.getProject_id(), "reply", reply.getReply_id()));
//            reply.setLikesCount(reply.getLikeList().size());
//            reply.setLiked(false);
//            for (Student stu : reply.getLikeList()) {
//                if (stu.getStudent_id().equals(stuID)) {
//                    reply.setLiked(true);
//                    break;
//                }
//            }
//        }


        return new genSuccessfulResponse(200, "获取成功", replyList);
    }

    @RequestMapping(value = "addReply", method = RequestMethod.POST)
    @ResponseBody
    public ResponseBean addReply(@RequestBody Reply reply) throws IOException {
        SqlSession sqlSession = SqlSessionLoader.getSqlSession();
        try {
            sqlSession.update("example.DiscussionMapper.addReply", reply);
        } catch (Exception e) {
            sqlSession.rollback();
            System.out.println(e.getMessage());
            return new genFailedResponse(310, "回复失败", new Date());
        } finally {
            sqlSession.commit();
            sqlSession.close();
        }
        return new genSuccessfulResponse(200, "回复成功", new Date());
    }

    @RequestMapping(value = "/likeAction", method = RequestMethod.POST)
    @ResponseBody
    public ResponseBean likeAction(@RequestBody HandleLikeRequest handleLikeRequest) throws IOException {
        SqlSession sqlSession = SqlSessionLoader.getSqlSession();
        try {
            sqlSession.insert("example.TeamMapper.likeAction", handleLikeRequest);
        } catch (Exception e) {
            sqlSession.rollback();
            System.out.println(e.getMessage());
            return new genFailedResponse(310, "点赞失败", new Date());
        } finally {
            sqlSession.commit();
            sqlSession.close();
        }
        return new genSuccessfulResponse(200, "点赞成功", new Date());
    }

    @RequestMapping(value = "/cancelLike", method = RequestMethod.POST)
    @ResponseBody
    public ResponseBean cancelLike(@RequestBody HandleLikeRequest handleLikeRequest) throws IOException {
        SqlSession sqlSession = SqlSessionLoader.getSqlSession();
        try {
            sqlSession.delete("example.TeamMapper.cancelLike", handleLikeRequest);
        } catch (Exception e) {
            sqlSession.rollback();
            System.out.println(e.getMessage());
            return new genFailedResponse(310, "取消点赞失败", new Date());
        } finally {
            sqlSession.commit();
            sqlSession.close();
        }
        return new genSuccessfulResponse(200, "取消点赞成功", new Date());
    }

}
