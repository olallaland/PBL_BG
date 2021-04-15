package com.example.sp.request;

public class HandleLikeRequest {
    int project_id;
    int type_id;
    String type;
    String from_stu_id;
    int status;

    public HandleLikeRequest() {
    }

    public HandleLikeRequest(int project_id, int type_id, String type, String from_stu_id) {
        this.project_id = project_id;
        this.type_id = type_id;
        this.type = type;
        this.from_stu_id = from_stu_id;
    }

    public HandleLikeRequest(int type_id, String type, String from_stu_id, int status) {
        this.type_id = type_id;
        this.type = type;
        this.from_stu_id = from_stu_id;
        this.status = status;
    }

    public HandleLikeRequest(int project_id, int type_id, String type, String from_stu_id, int status) {
        this.project_id = project_id;
        this.type_id = type_id;
        this.type = type;
        this.from_stu_id = from_stu_id;
        this.status = status;
    }

    public int getProject_id() {
        return project_id;
    }

    public void setProject_id(int project_id) {
        this.project_id = project_id;
    }

    public int getType_id() {
        return type_id;
    }

    public void setType_id(int type_id) {
        this.type_id = type_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFrom_stu_id() {
        return from_stu_id;
    }

    public void setFrom_stu_id(String from_stu_id) {
        this.from_stu_id = from_stu_id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
