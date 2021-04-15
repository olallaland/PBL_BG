package com.example.sp.request;

public class GetLikedListRequest {
    String type;
    int type_id;
    int project_id;

    public GetLikedListRequest() {
    }

    public GetLikedListRequest(String type, int type_id) {
        this.type = type;
        this.type_id = type_id;
    }

    public GetLikedListRequest(String type, int type_id, int project_id) {
        this.type = type;
        this.type_id = type_id;
        this.project_id = project_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getType_id() {
        return type_id;
    }

    public void setType_id(int type_id) {
        this.type_id = type_id;
    }

    public int getProject_id() {
        return project_id;
    }

    public void setProject_id(int project_id) {
        this.project_id = project_id;
    }
}
