package com.example.sp.model;

import java.util.List;

public class KeyPoint {
    int key_point_id;
    int project_id;
    String student_id;
    String content;
    long time;
    int is_key;
    int like_count;
    List<Student> likedList;

    public KeyPoint() {
    }

    public KeyPoint(int key_point_id, int project_id, String student_id, String content, long time) {
        this.key_point_id = key_point_id;
        this.project_id = project_id;
        this.student_id = student_id;
        this.content = content;
        this.time = time;
    }

    public KeyPoint(int key_point_id, int project_id, String student_id, String content, long time, int like_count) {
        this.key_point_id = key_point_id;
        this.project_id = project_id;
        this.student_id = student_id;
        this.content = content;
        this.time = time;
        this.like_count = like_count;
    }

    public KeyPoint(int key_point_id, int project_id, String student_id, String content, int like_count, List<Student> likedList) {
        this.key_point_id = key_point_id;
        this.project_id = project_id;
        this.student_id = student_id;
        this.content = content;
        this.like_count = like_count;
        this.likedList = likedList;
    }

    public KeyPoint(int key_point_id, int project_id, String student_id, String content, long time, int is_key, int like_count, List<Student> likedList) {
        this.key_point_id = key_point_id;
        this.project_id = project_id;
        this.student_id = student_id;
        this.content = content;
        this.time = time;
        this.is_key = is_key;
        this.like_count = like_count;
        this.likedList = likedList;
    }

    public int getKey_point_id() {
        return key_point_id;
    }

    public void setKey_point_id(int key_point_id) {
        this.key_point_id = key_point_id;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getLike_count() {
        return like_count;
    }

    public void setLike_count(int like_count) {
        this.like_count = like_count;
    }

    public List<Student> getLikedList() {
        return likedList;
    }

    public void setLikedList(List<Student> likedList) {
        this.likedList = likedList;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public int getIs_key() {
        return is_key;
    }

    public void setIs_key(int is_key) {
        this.is_key = is_key;
    }
}
