package com.example.sp.model;

public class HelpRequest {
    int help_request_id;
    int task_id;
    String to_student_id;
    String to_student_name;
    String request_status;

    public HelpRequest() {
    }

    public int getHelp_request_id() {
        return help_request_id;
    }

    public void setHelp_request_id(int help_request_id) {
        this.help_request_id = help_request_id;
    }

    public int getTask_id() {
        return task_id;
    }

    public void setTask_id(int task_id) {
        this.task_id = task_id;
    }

    public String getTo_student_id() {
        return to_student_id;
    }

    public void setTo_student_id(String to_student_id) {
        this.to_student_id = to_student_id;
    }

    public String getRequest_status() {
        return request_status;
    }

    public void setRequest_status(String request_status) {
        this.request_status = request_status;
    }

    public String getTo_student_name() {
        return to_student_name;
    }

    public void setTo_student_name(String to_student_name) {
        this.to_student_name = to_student_name;
    }

    public HelpRequest(int help_request_id, String to_student_id, String request_status) {
        this.help_request_id = help_request_id;
        this.to_student_id = to_student_id;
        this.request_status = request_status;
    }

    public HelpRequest(int help_request_id, int task_id, String to_student_id, String request_status) {
        this.help_request_id = help_request_id;
        this.task_id = task_id;
        this.to_student_id = to_student_id;
        this.request_status = request_status;
    }

    public HelpRequest(int help_request_id, int task_id, String to_student_id, String to_student_name, String request_status) {
        this.help_request_id = help_request_id;
        this.task_id = task_id;
        this.to_student_id = to_student_id;
        this.to_student_name = to_student_name;
        this.request_status = request_status;
    }
}
