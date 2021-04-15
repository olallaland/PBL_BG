package com.example.sp.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Discussion {
    int discussion_id;
    int project_id;
    String student_id;
    String student_name;
    String title;
    String content;
    String image;
    long time;
    boolean liked;
    int likesCount = 0;
    List<Student> likeList = new ArrayList<>();

    public Discussion(int discussion_id, int project_id, String student_id, String title, String content, String image, long time) {
        this.discussion_id = discussion_id;
        this.project_id = project_id;
        this.student_id = student_id;
        this.title = title;
        this.content = content;
        this.image = image;
        this.time = time;
    }

    public Discussion(int project_id, String student_id, String title, String content, long time) {
        this.project_id = project_id;
        this.student_id = student_id;
        this.title = title;
        this.content = content;
        this.time = time;
    }

    public Discussion() {
    }

    public Discussion(int project_id, String student_id, String title, String content, String image, long time) {
        this.project_id = project_id;
        this.student_id = student_id;
        this.title = title;
        this.content = content;
        this.image = image;
        this.time = time;
    }

    public Discussion(int project_id, String student_id, String student_name, String title, String content) {
        this.project_id = project_id;
        this.student_id = student_id;
        this.student_name = student_name;
        this.title = title;
        this.content = content;
    }

    public int getProject_id() {
        return project_id;
    }

    public void setProject_id(int project_id) {
        this.project_id = project_id;
    }

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getStudent_name() {
        return student_name;
    }

    public void setStudent_name(String student_name) {
        this.student_name = student_name;
    }

    public int getDiscussion_id() {
        return discussion_id;
    }

    public void setDiscussion_id(int discussion_id) {
        this.discussion_id = discussion_id;
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
}
