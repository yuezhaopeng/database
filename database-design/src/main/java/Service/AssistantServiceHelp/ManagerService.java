package Service.AssistantServiceHelp;


import Dao.*;
import Entity.*;
import Entity.vo.AssistantRemark;
import Entity.vo.TeacherCourse;
import Utils.DAOFactory;

import java.util.ArrayList;
import java.util.Scanner;

/*在研究生多轮没选上时，为学生指定助教课程*/
public class ManagerService {
    /*当前登入的培养管理员*/
    private Manager manager;

    private AvailableAssistantDao availableAssistantDAO;
    private CourseDao courseDAO;
    private TeacherCourseDao teacherCourseDAO;
    private TeacherDao teacherDAO;
    private StudentDao studentDAO;
    private ManagerDao managerDAO;

    public ManagerService(String mno) {
        availableAssistantDAO = DAOFactory.getInstance().getAvailableAssistantDAO();
        courseDAO = DAOFactory.getInstance().getCourseDAO();
        teacherCourseDAO = DAOFactory.getInstance().getTeacherCourseDAO();
        teacherDAO = DAOFactory.getInstance().getTeacherDAO();
        studentDAO = DAOFactory.getInstance().getStudentDAO();
        managerDAO = DAOFactory.getInstance().getManagerDAO();

        manager = managerDAO.selectByMno(mno);
        service();
    }

    /*程序入口*/
    public void service(){
        boolean flag = true;
        Scanner scanner = new Scanner(System.in);
        while(flag){
            System.out.println("\n————————助教工作管理————————");
            System.out.println("1：查看所有学生助教情况");
            System.out.println("2：退出系统");
            System.out.print("输入选项：");
            int num = scanner.nextInt();
            if(num==1){
                showAssistant();
            }else if(num==2){
                flag = false;
            }else{
                System.out.println("请重新输入！");
            }
        }
    }

    /*1：展示所有学生的助教情况，并可以选择为没有助教的学生指定课程*/
    public void showAssistant(){
        System.out.println("\n————————查看所有学生助教情况————————");
        ArrayList<Student> students = studentDAO.getAll();
        System.out.println("学生列表：");
        for(int i=0;i<students.size();i++){
            Student s = students.get(i);
            System.out.println("学生学号："+s.getSno()+"  学生姓名："+s.getSname()+"  学生专业："+s.getSmajor()+"  是否已任助教："+s.getMno());
        }
        boolean flag = true;
        while (flag){
            System.out.print("输入学生学号查看详情：");
            Scanner scanner = new Scanner(System.in);
            String num = scanner.next().trim();
            int idx = getIndex(students,num);
            if(idx!=-1){
                Student stu = students.get(idx);
                if(!stu.getMno().equals("否")){
                    /*已有助教，查看详情*/
                    System.out.println("\n学号为 "+num+" 的学生，正在申请或正在担任如下助教工作：");
                    ArrayList<AssistantRemark> myApply = availableAssistantDAO.getAllApplyBySno(num);

                    for(int i=0;i< myApply.size();i++){
                        AvailableAssistant a = myApply.get(i).getAvailableAssistant();
                        System.out.print("课程编号："+a.getCno()+"  课程名："+myApply.get(i).getCourse().getCname()+"  申请状态："+(a.getStatus()==0?"未审核":(a.getStatus()==1?"选中":"未选中")));
                        if(a.getStatus()==1){
                            System.out.println("  评价结果："+(a.getResult()==0?"未审核":(a.getResult()==1?"合格":"不合格")));
                        }else {
                            System.out.println("");
                        }
                    }
                }else {
                    /*没有助教，指定*/
                    System.out.println("\n该学生没有成功申请上任何一门助教工作。");
                    ArrayList<TeacherCourse> teacherCourses = courseDAO.getAllCoursesNotSelected();
                    System.out.println("\n可供申请的课程：");
                    if(teacherCourses.size()==0){
                        System.out.println("暂无可选择的课程。");
                        return;
                    }else {
                        System.out.println("共有 "+teacherCourses.size()+" 门可选择的课程，优先显示课时数多、选课人数多的课程。");
                    }
                    for(int i=0;i<teacherCourses.size();i++){
                        TeacherCourse tc = teacherCourses.get(i);
                        Course c = tc.getCourse();
                        Teacher t = tc.getTeacher();
                        System.out.println("课程编号："+c.getCno()+"  课程名称："+c.getCname()+"  授课教师："+t.getTname()+"  课程性质："+c.getCtype()+"  授课对象："+c.getTarget()+"  授课时间："+c.getChour()+"  选课人数："+c.getEnrolled_number());
                    }
                    System.out.print("请输入要为该学生指定的助教课程编号：");
                    while (true){
                        String cnum = scanner.next().trim();
                        if(checkExist(teacherCourses,cnum)){
                            /*将申请本门课的学生状态都设为没选中该门课*/
                            for(int it=0;it<students.size();it++){
                                availableAssistantDAO.updateAvailableAssistant_teacher(cnum,students.get(it).getSno(),2);
                            }
                            /*向available_assistant插入status为1的记录*/
                            AvailableAssistant aa = new AvailableAssistant();
                            aa.setSno(num);
                            aa.setCno(cnum);
                            aa.setStatus(1);
                            aa.setResult(0);
                            availableAssistantDAO.addAvailableAssistant(aa);
                            /*修改course*/
                            courseDAO.updateCourse(cnum,true);

                            System.out.println("成功指定！");
                            break;
                        }else {
                            System.out.println("请重新输入！");
                        }
                    }
                }
                break;
            }else {
                System.out.println("请重新输入！");
            }
        }
    }

    public int getIndex(ArrayList<Student> students, String sno){
        for(int i=0;i<students.size();i++){
            if(students.get(i).getSno().equals(sno)){
                return i;
            }
        }
        return -1;
    }

    /*查询课程是否存在*/
    public boolean checkExist(ArrayList<TeacherCourse> teacherCourses,String cno){
        for(TeacherCourse tc: teacherCourses){
            if(tc.getCourse().getCno().equals(cno)){
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        ManagerService m = new ManagerService("1");
    }
}
