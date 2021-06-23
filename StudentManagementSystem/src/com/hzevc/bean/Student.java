package com.hzevc.bean;

/**
 * @author ：项目成员 余俊彦、彭成泰、傅宇、黎建聪
 * @date ：Created in 2020/12/1 11:33
 * @description：Student management system
 * @modified By：
 * @version: java version "1.8.0_261"$
 */
public class Student {
    private String ID;// 学号
    private String name;// 学生姓名
    private char gender;// 性别
    private int age;// 年龄
    private String SC;// 学生班级

    public Student() {
    }

    public Student(String ID, String name, char gender, int age, String SC) {
        this.ID = ID;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.SC = SC;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSC() {
        return SC;
    }

    public void setSC(String SC) {
        this.SC = SC;
    }
}

