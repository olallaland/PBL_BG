package com.example.sp.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Timestamp;

public class SubTask {
    int task_id;
    int project_id;
    String student_id;
    String student_name;
    String content;
    long deadline;
    String status;
    int shared;
    String second_id;
    HelpRequest helpRequest;

    public SubTask() {
    }

    public SubTask(int project_id, String content, long deadline) {
        this.project_id = project_id;
        this.content = content;
        this.deadline = deadline;
    }

    public SubTask(int task_id, int project_id, String student_id) {
        this.task_id = task_id;
        this.project_id = project_id;
        this.student_id = student_id;
    }



    public SubTask(int task_id, int project_id, String student_id, String content, long deadline, String status, int shared, String second_id) {
        this.task_id = task_id;
        this.project_id = project_id;
        this.student_id = student_id;
        this.content = content;
        this.deadline = deadline;
        this.status = status;
        this.shared = shared;
        this.second_id = second_id;
    }

    public String getStudent_name() {
        return student_name;
    }

    public void setStudent_name(String student_name) {
        this.student_name = student_name;
    }

    public int getTask_id() {
        return task_id;
    }

    public void setTask_id(int task_id) {
        this.task_id = task_id;
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

    public long getDeadline() {
        return deadline;
    }

    public void setDeadline(long deadline) {
        this.deadline = deadline;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getShared() {
        return shared;
    }

    public void setShared(int shared) {
        this.shared = shared;
    }

    public String getSecond_id() {
        return second_id;
    }

    public void setSecond_id(String second_id) {
        this.second_id = second_id;
    }

    public HelpRequest getHelpRequest() {
        return helpRequest;
    }

    public void setHelpRequest(HelpRequest helpRequest) {
        this.helpRequest = helpRequest;
    }
}
