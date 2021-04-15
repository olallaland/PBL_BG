package com.example.sp.request;

public class UpdateTeamMemberRequest {
    int project_id;
    String project_name;
    String student_id;
    String student_name;
    String role_tag;

    public UpdateTeamMemberRequest() {
    }

    public UpdateTeamMemberRequest(int project_id, String student_id, String role_tag) {
        this.project_id = project_id;
        this.student_id = student_id;
        this.role_tag = role_tag;
    }

    public UpdateTeamMemberRequest(int project_id, String project_name, String student_id, String student_name, String role_tag) {
        this.project_id = project_id;
        this.project_name = project_name;
        this.student_id = student_id;
        this.student_name = student_name;
        this.role_tag = role_tag;
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

    public String getRole_tag() {
        return role_tag;
    }

    public void setRole_tag(String role_tag) {
        this.role_tag = role_tag;
    }

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public String getStudent_name() {
        return student_name;
    }

    public void setStudent_name(String student_name) {
        this.student_name = student_name;
    }
}
