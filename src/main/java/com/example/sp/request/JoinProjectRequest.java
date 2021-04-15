package com.example.sp.request;

/**
 *    
 *  @author luo tianyue 
 *  @Date 2020/6/19  
 *  @Time 19:07  
 */
public class JoinProjectRequest {
    int project_id;
    String student_id;

    public JoinProjectRequest() {
    }

    public JoinProjectRequest(int project_id, String student_id) {
        this.project_id = project_id;
        this.student_id = student_id;
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
}
