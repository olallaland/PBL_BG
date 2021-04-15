package com.example.sp.model;

import java.util.Date;

/**
 *    
 *  @author luo tianyue 
 *  @Date 2020/6/18  
 *  @Time 16:06  
 */
public class ProjectFile {
    int materials_id;
    int project_id;
    String user_id;
    String user_type;
    long upload_time;
    String description;
    long size;
    String address;
    int download_count;

    public ProjectFile(int project_id, String user_id, String user_type, long upload_time, String description, long size, String address) {
        this.project_id = project_id;
        this.user_id = user_id;
        this.user_type = user_type;
        this.upload_time = upload_time;
        this.description = description;
        this.size = size;
        this.address = address;
    }

    public ProjectFile(int materials_id, int project_id, String user_id, String user_type, long upload_time, String description, long size, String address) {
        this.materials_id = materials_id;
        this.project_id = project_id;
        this.user_id = user_id;
        this.user_type = user_type;
        this.upload_time = upload_time;
        this.description = description;
        this.size = size;
        this.address = address;
    }

    public ProjectFile(int materials_id, int project_id, String user_id, String user_type, long upload_time, String description, long size, String address, int download_count) {
        this.materials_id = materials_id;
        this.project_id = project_id;
        this.user_id = user_id;
        this.user_type = user_type;
        this.upload_time = upload_time;
        this.description = description;
        this.size = size;
        this.address = address;
        this.download_count = download_count;
    }

    public int getMaterials_id() {
        return materials_id;
    }

    public void setMaterials_id(int materials_id) {
        this.materials_id = materials_id;
    }

    public int getProject_id() {
        return project_id;
    }

    public void setProject_id(int project_id) {
        this.project_id = project_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_type() {
        return user_type;
    }

    public void setUser_type(String user_type) {
        this.user_type = user_type;
    }

    public long getUpload_time() {
        return upload_time;
    }

    public void setUpload_time(long upload_time) {
        this.upload_time = upload_time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getDownload_count() {
        return download_count;
    }

    public void setDownload_count(int download_count) {
        this.download_count = download_count;
    }
}
