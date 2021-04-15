package com.example.sp.request;

public class KeyPointAddRequest {
    int project_id;
    String student_id;
    String content;
    long time;

    public KeyPointAddRequest(int project_id, String student_id, String content, long time) {
        this.project_id = project_id;
        this.student_id = student_id;
        this.content = content;
        this.time = time;
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

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
