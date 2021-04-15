package com.example.sp.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Timestamp;

public class ProjectAddRequest {
    String teacher_id;
    String course_name;
    String project_name;
    String description;
    int key_point_count;
    int member_limit;
    int duration;

    public ProjectAddRequest(){
    }

    public ProjectAddRequest(String teacher_id, String course_name, String project_name, String description,
                             int key_point_count, int member_limit, int duration) {
        this.teacher_id = teacher_id;
        this.course_name = course_name;
        this.project_name = project_name;
        this.description = description;
        this.key_point_count = key_point_count;
        this.member_limit = member_limit;
        this.duration = duration;
    }

    public String getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(String teacher_id) {
        this.teacher_id = teacher_id;
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
}
