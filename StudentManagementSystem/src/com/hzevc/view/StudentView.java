package com.hzevc.view;

import com.hzevc.bean.Student;
import com.hzevc.service.StudentList;
import com.hzevc.utlis.CMUtility;
import com.sun.xml.internal.bind.v2.model.core.ID;

import java.util.Scanner;

/**
 * @author ：项目成员 余俊彦、彭成泰、傅宇、黎建聪
 * @date ：Created in 2020/12/1 11:36
 * @description：Student management system
 * @modified By：
 * @version: java version "1.8.0_261"$
 */
public class StudentView {
    private StudentList students = new StudentList(10);
    //创建并初始化一个students数组，添加一个stu学生对象
    public StudentView() {
        Student stu = new Student("1960201","码煲锅",'男', 69,"G19混元形意太极专业班");
        students.addStudent(stu);
    }

    //    显示《学生管理软件》界面的方法
    public void enterMainMenu() {
        boolean loopFlag = true;
        do {
            System.out.println("\n-----------------学生信息管理软件--------------------\n");
            System.out.println("                 1 添 加 学 生 信 息");
            System.out.println("                 2 显 示 所 有 学 生 信 息");
            System.out.println("                 3 删 除 学 生 信 息");
            System.out.println("                 4 修 改 学 生 信 息");
            System.out.println("                 5 查 询 学 生 个 人 信 息");
            System.out.println("                 6 按 学 号 升 序 对 学 生 信 息 排 序");
            System.out.println("                 7 退 出\n");
            System.out.print("                 请选择(1-7):");

            // 从键盘获取用户1-7的选择
            char menu = CMUtility.readMenuSelection();
            System.out.println();
            // 使用switch-case对用户的选择，进行区别管理
            switch (menu) {
                case '1':
                    addNewStudent();
                    break;
                case '2':
                    listAllStudents();
                    break;
                case '3':
                    deleteStudent();
                    break;
                case '4':
                    modifyStudent();
                    break;
                case '5':
                    getStudent();
                    break;
                case '6':
                    orderStudent();
                    break;
                case '7':
                    System.out.print("确认是否退出(Y/N)：");
                    char yn = CMUtility.readConfirmSelection();
                    if (yn == 'Y')
                        loopFlag = false;
                    break;
            }
        } while (loopFlag);
    }



    //     添 加 学 生 信 息
    private void addNewStudent() {
        System.out.println("---------------------添加学生---------------------");
        //调用CMUtility里的readString()方法，从键盘获取输入的学生数据
        System.out.print("学号(6位)：");
        String ID = CMUtility.readString(12);
        System.out.print("姓名：");
        String name = CMUtility.readString(4);
        System.out.print("性别：");
        char gender = CMUtility.readChar();
        System.out.print("年龄：");
        int age = CMUtility.readInt();
        System.out.print("班级：");
        String SC = CMUtility.readString(15);
        //创建并初始化stu数组
        Student stu = new Student(ID, name, gender, age, SC);
        boolean flag = students.addStudent(stu);
        if (flag) {
            System.out
                    .println("---------------------添加完成---------------------");
        } else {
            System.out.println("----------------记录已满,无法添加-----------------");
        }
    }



    //    显 示 所 有 学 生 信 息
    private void listAllStudents() {
        System.out.println("---------------------------学生列表---------------------------");
        Student[] stus = students.getAllStudents();// 调用Student.getAllStudents()里的stus数组
        if (stus.length == 0) {
            System.out.println("没有学生记录！");
        } else {
            System.out.println("编号\t学号\t\t姓名\t\t性别\t\t年龄\t\t班级");
            //遍历循环，输出suts里的学生对象
            for (int i = 0; i < stus.length; i++) {
                System.out.println((i+1) + "\t" + stus[i].getID()+"\t"+stus[i].getName()+
                        "\t"+stus[i].getGender()+"\t"+"\t"+stus[i].getAge()+"\t"+"\t"+stus[i].getSC());
            }
        }

        System.out.println("-------------------------学生列表完成-------------------------");
    }



    //    删 除 学 生 信 息
    private void deleteStudent() {
        System.out.println("---------------------删除学生---------------------");

        int index = 0;
        Student stu = null;
        for (;;) {
            System.out.print("请选择待删除学生编号(-1退出)：");
            index = CMUtility.readInt();//调用CMUtility里的readInt()方法，从键盘读取一个长度不超过2位的整数作为编号
            if (index == -1) {
                return;
            }
            stu = students.getStudent(index - 1);
            if (stu == null) {
                System.out.println("无法找到指定学生！");
            } else
                break;
        }

        System.out.print("确认是否删除(Y/N)：");
        char yn = CMUtility.readConfirmSelection();//调用CMUtility里的readConfirmSelection()方法，从键盘从键盘读取‘Y’或’N’
        if (yn == 'N')
            return;

        // 删除指定学生
        boolean flag = students.deleteStudent(index - 1);
        if (flag) {
            System.out
                    .println("---------------------删除完成---------------------");
        } else {
            System.out.println("----------无法找到指定学生,删除失败--------------");
        }

    }



    //    修 改 学 生 信 息
    private void modifyStudent() {
        System.out.println("---------------------修改学生---------------------");

        int index = 0;
        Student stu = null;
        for (;;) {
            System.out.print("请选择待修改学生编号(-1退出)：");
            index = CMUtility.readInt();//调用CMUtility里的readInt()方法，从键盘读取一个长度不超过2位的整数作为编号
            //查找该编号学生
            if (index == -1) {
                return;
            }
            stu = students.getStudent(index - 1);
            if (stu == null) {
                System.out.println("无法找到指定学生！");
            } else
                break;
        }
        //从键盘获取新的学生信息，替换该编号的学生的信息
        System.out.print("学号(" + stu.getID() + ")：");
        String ID = CMUtility.readString(12, stu.getID());

        System.out.print("姓名(" + stu.getName() + ")：");
        String name = CMUtility.readString(4, stu.getName());

        System.out.print("性别(" + stu.getGender() + ")：");
        char gender = CMUtility.readChar(stu.getGender());

        System.out.print("年龄(" + stu.getAge() + ")：");
        int age = CMUtility.readInt(stu.getAge());

        System.out.print("班级(" + stu.getSC() + ")：");
        String SC = CMUtility.readString(15, stu.getSC());


        stu = new Student(ID, name, gender, age, SC);

        boolean flag = students.replaceStudent(index - 1, stu);
        if (flag) {
            System.out
                    .println("---------------------修改完成---------------------");
        } else {
            System.out.println("----------无法找到指定学生,修改失败--------------");
        }
    }



    //      查 询 学 生 个 人 信 息
    private void getStudent(){
        Student[] stus = students.getAllStudents();
        System.out.print("请输入要查询的学生学号 : ");
        Scanner scanner=new Scanner(System.in);// 从键盘获取输入的数据（学号），存储在Stunumber中
        String Stunumber=scanner.next();
        int i;
        for(i=0;i< stus.length;i++)//循环遍历stus数值，获取正确学生的信息
        {
            //使用 equals() 方法将Stunumber中的学号与stus数组中的学生学号进行比较。
            if(Stunumber.equals(stus[i].getID())){
                System.out.println("-------------------------------------------");
                System.out.println("学号\t\t姓名\t\t性别\t\t年龄\t\t班级");
                System.out.println(stus[i].getID()+"\t"+stus[i].getName()+"\t" +
                        ""+stus[i].getGender()+"\t"+"\t"+stus[i].getAge()+"\t"+
                        "\t"+stus[i].getSC());
                System.out.println("-------------------------------------------");
                break;
            }
        }if(stus.length==i)System.out.println("对不起，无法找到指定学生");
    }



    //      按 学 号 升 序 对 学 生 信 息 排 序
    private void orderStudent(){
        Student[] stus = students.getAllStudents();
        // 循环遍历数组进行排序
        for (int i=0; i<stus.length-1; i++)
            for (int j=i+1,a = Integer.parseInt(stus[i].getID()),b = Integer.parseInt(stus[j].getID()); j<stus.length; j++)
                if (a>b){
                    Student tmp = stus[i];
                    stus[i] = stus[j];
                    stus[j]=tmp;
                }
        System.out.println("按学号升序排序");
        //遍历输出按学号升序排序后的所有学生信息
        for (int i = 0; i < stus.length; i++) {
            System.out.println((i+1) + "\t" + stus[i].getID()+"\t"+stus[i].getName()+"\t"
                    +stus[i].getGender()+"\t"+"\t"+stus[i].getAge()+"\t"+"\t"+stus[i].getSC());
        }
    }

    //程序的入口
    public static void main(String[] args) {
        StudentView view = new StudentView();
        view.enterMainMenu();
    }
}
