package com.example.student.tools;

//保存学生信息的实体类
public class Student {
    private String name;//姓名
    private String sex;//性别
    private String id;//学号
    private String number;//手机号
    private String password;//学生登录密码
    private int MathScore;//数学成绩
    private int ChineseScore;//语文成绩
    private int EnglishScore;//英语成绩
    private int order;//名次

    public Student(String name,String sex,String id,String number,String password,int mathScore,int chineseScore,int englishScore,int order) {
        this.name = name;
        this.sex = sex;
        this.id = id;
        this.number = number;
        this.password = password;
        MathScore = mathScore;
        ChineseScore = chineseScore;
        EnglishScore = englishScore;
        this.order=order;
    }

    public String getName() {
        return name;
    }

    public String getSex() {
        return sex;
    }

    public String getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }

    public String getPassword() {
        return password;
    }

    public int getMathScore() {
        return MathScore;
    }

    public int getChineseScore() {
        return ChineseScore;
    }

    public int getEnglishScore() {
        return EnglishScore;
    }
    
    public int getOrder() {
        return order;
    }
}