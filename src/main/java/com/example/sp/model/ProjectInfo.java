package com.example.sp.model;

import java.util.List;

public class ProjectInfo {
    String teacher_id;
    String teacher_name;
    int project_id;
    String course_name;
    String project_name;
    String description;
    int key_point_count;
    int member_limit;
    int duration;
    String status;
    long start_time;
    List<Student> curMember;

    public ProjectInfo(String teacher_id, String teacher_name, int project_id, String course_name, String project_name,
                       String description, int key_point_count, int member_limit, int duration, String status,
                       long start_time, List<Student> curMember) {
        this.teacher_id = teacher_id;
        this.teacher_name = teacher_name;
        this.project_id = project_id;
        this.course_name = course_name;
        this.project_name = project_name;
        this.description = description;
        this.key_point_count = key_point_count;
        this.member_limit = member_limit;
        this.duration = duration;
        this.status = status;
        this.start_time = start_time;
        this.curMember = curMember;
    }

    public ProjectInfo(String teacher_id, String teacher_name, int project_id, String course_name, String project_name,
                       String description, int key_point_count, int member_limit, int duration, String status, long start_time) {
        this.teacher_id = teacher_id;
        this.teacher_name = teacher_name;
        this.project_id = project_id;
        this.course_name = course_name;
        this.project_name = project_name;
        this.description = description;
        this.key_point_count = key_point_count;
        this.member_limit = member_limit;
        this.duration = duration;
        this.status = status;
        this.start_time = start_time;
    }

    public int getProject_id() {
        return project_id;
    }

    public void setProject_id(int project_id) {
        this.project_id = project_id;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public String getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(String teacher_id) {
        this.teacher_id = teacher_id;
    }

    public String getTeacher_name() {
        return teacher_name;
    }

    public void setTeacher_name(String teacher_name) {
        this.teacher_name = teacher_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getKey_point_count() {
        return key_point_count;
    }

    public void setKey_point_count(int key_point_count) {
        this.key_point_count = key_point_count;
    }

    public int getMember_limit() {
        return member_limit;
    }

    public void setMember_limit(int member_limit) {
        this.member_limit = member_limit;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getStart_time() {
        return start_time;
    }

    public void setStart_time(long start_time) {
        this.start_time = start_time;
    }


    public List<Student> getCurMember() {
        return curMember;
    }

    public void setCurMember(List<Student> curMember) {
        this.curMember = curMember;
    }
}
