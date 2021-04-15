package com.example.sp.model;

public class Teacher {
    String teacher_id;
    //教师工号
    String password;
    //密码
    String name;
    //学生名字
    public Teacher(){
    }

    public Teacher(String teacher_id, String name) {
        this.teacher_id = teacher_id;
        this.name = name;
    }

    public Teacher(String teacher_id, String password, String name){
        this.teacher_id = teacher_id;
        this.name = name;
        this.password = password;
    }

    public void setTeacher_id(String teacher_id){
        this.teacher_id = teacher_id;
    }
    public String getTeacher_id(){
        return teacher_id;
    }

    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }

    public void setPassword(String password){
        this.password = password;
    }
    public String getPassword(){
        return password;
    }
}
