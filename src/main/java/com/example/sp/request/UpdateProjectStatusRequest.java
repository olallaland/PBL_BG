package com.example.sp.request;

public class UpdateProjectStatusRequest {
    int project_id;
    String status;
    long start_time = 0;

    public UpdateProjectStatusRequest() {
    }

    public UpdateProjectStatusRequest(int project_id, String status) {
        this.project_id = project_id;
        this.status = status;
    }

    public UpdateProjectStatusRequest(int project_id, long start_time) {
        this.project_id = project_id;
        this.start_time = start_time;
    }

    public UpdateProjectStatusRequest(int project_id, String status, long start_time) {
        this.project_id = project_id;
        this.status = status;
        this.start_time = start_time;
    }

    public int getProject_id() {
        return project_id;
    }

    public void setProject_id(int project_id) {
        this.project_id = project_id;
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
}
