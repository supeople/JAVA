package com.hzevc.service;

import com.hzevc.bean.Student;

/**
 * @author ：项目成员 余俊彦、彭成泰、傅宇、黎建聪
 * @date ：Created in 2020/12/1 11:35
 * @description：Student management system
 * @modified By：
 * @version: java version "1.8.0_261"$
 */
public class StudentList {
    private Student[] students;// 用来保存客户对象的数组
    private int total = 0;// 记录已保存客户对象的数量

    /**
     * 构造器，用来初始化students数组
     * @param totalStudent : 指定students数组的最大空间
     */
    public StudentList(int totalStudent){
        students = new Student[totalStudent];
    }

    /**
     * @将指定的学生添加到数组中
     * @param student
     * @return 添加是否成功
     */
    public boolean addStudent(Student student){
        if (student != null && total < students.length){
            students[total++] = student;
            return true;
        }
        return false;
    }

    /**
     * 修改指定索引位置上的数组元素
     * @param index
     * @param stu
     * @return
     */
    public  boolean replaceStudent(int index, Student stu) {
        if (index >= 0 && index < total){
            students[index] = stu;
            return true;
        }
        return false;
    }

    /**
     * 删除指定索引位置上的元素
     * @param index
     * @return
     */
    public boolean deleteStudent(int index) {
        if (index >= 0 && index < total){
            for (int i = index;i < total - 1;i++){
                students[i] = students[i + 1];
            }
            //最后有数据的元素需要置空
            students[--total] = null;
            return true;
        }
        return false;
    }

    /**
     * 获取所有的students对象构成的数组
     * @return
     */
    public Student[] getAllStudents() {
        Student[] stus = new Student[total];
        for (int i = 0;i < stus.length; i++){
            stus[i] = students[i];
        }
        return stus;
    }

    /**
     * 获取指定索引位置的Student
     * @param index
     * @return
     */
    public Student getStudent(int index) {
        if (index >= 0 && index < total){
            return students[index];
        }
        return null;
    }

    /**
     * 获取存储Student对象的数量
     * @return
     */
    public int getTotal(){
        return total;
    }
}
