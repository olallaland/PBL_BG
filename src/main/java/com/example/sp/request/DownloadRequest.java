package com.example.sp.request;

public class DownloadRequest {
    int project_id;
    int materials_id;
    String user_id;

    public DownloadRequest() {
    }

    public DownloadRequest(int project_id, int materials_id, String user_id) {
        this.project_id = project_id;
        this.materials_id = materials_id;
        this.user_id = user_id;
    }

    public int getProject_id() {
        return project_id;
    }

    public void setProject_id(int project_id) {
        this.project_id = project_id;
    }

    public int getMaterials_id() {
        return materials_id;
    }

    public void setMaterials_id(int materials_id) {
        this.materials_id = materials_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}
