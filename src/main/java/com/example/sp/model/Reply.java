package com.example.sp.model;

import java.util.ArrayList;
import java.util.List;

public class Reply {
    int reply_id;
    int project_id;
    int discussion_id;
    String student_id;
    String student_name;
    String content;
    long reply_time;
    boolean liked;
    int likesCount = 0;
    List<Student> likeList = new ArrayList<>();

    public int getReply_id() {
        return reply_id;
    }

    public void setReply_id(int reply_id) {
        this.reply_id = reply_id;
    }

    public int getProject_id() {
        return project_id;
    }

    public void setProject_id(int project_id) {
        this.project_id = project_id;
    }

    public int getDiscussion_id() {
        return discussion_id;
    }

    public void setDiscussion_id(int discussion_id) {
        this.discussion_id = discussion_id;
    }

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public String getStudent_name() {
        return student_name;
    }

    public void setStudent_name(String student_name) {
        this.student_name = student_name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getReply_time() {
        return reply_time;
    }

    public void setReply_time(long reply_time) {
        this.reply_time = reply_time;
    }

    public int getLikesCount() {
        return likesCount;
    }

    public void setLikesCount(int likesCount) {
        this.likesCount = likesCount;
    }

    public List<Student> getLikeList() {
        return likeList;
    }

    public void setLikeList(List<Student> likeList) {
        this.likeList = likeList;
    }

    public boolean isLiked() {
        return liked;
    }

    public void setLiked(boolean liked) {
        this.liked = liked;
    }

    public Reply(int project_id, int discussion_id, String student_id, String student_name, String content) {
        this.project_id = project_id;
        this.discussion_id = discussion_id;
        this.student_id = student_id;
        this.student_name = student_name;
        this.content = content;
    }

    public Reply(int project_id, int discussion_id, String student_id, String student_name, String content, long reply_time) {
        this.project_id = project_id;
        this.discussion_id = discussion_id;
        this.student_id = student_id;
        this.student_name = student_name;
        this.content = content;
        this.reply_time = reply_time;
    }

    public Reply(int reply_id, int project_id, int discussion_id, String student_id, String student_name, String content, long reply_time, int likesCount) {
        this.reply_id = reply_id;
        this.project_id = project_id;
        this.discussion_id = discussion_id;
        this.student_id = student_id;
        this.student_name = student_name;
        this.content = content;
        this.reply_time = reply_time;
        this.likesCount = likesCount;
    }

    public Reply() {
    }
}
