package com.example.sp.model;

public class Student {
    String student_id;
    //学生学号
    String password;
    //密码
    String name;
    //学生名字

    public Student(){
    }

    public Student(String student_id) {
        this.student_id = student_id;
    }

    public Student(String student_id, String name) {
        this.student_id = student_id;
        this.name = name;
    }

    public Student(String student_id, String password, String name){
        this.student_id = student_id;
        this.name = name;
        this.password = password;
    }

    public void setStudent_id(String student_id){
        this.student_id = student_id;
    }
    public String getStudent_id(){
        return student_id;
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
