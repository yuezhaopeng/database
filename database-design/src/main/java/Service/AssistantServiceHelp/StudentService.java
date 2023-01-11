package Service.AssistantServiceHelp;


import Dao.AvailableAssistantDao;
import Dao.CourseDao;
import Dao.StudentDao;
import Entity.AvailableAssistant;
import Entity.Course;
import Entity.Student;
import Entity.Teacher;
import Entity.vo.AssistantRemark;
import Entity.vo.TeacherCourse;
import Utils.DAOFactory;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

/*供研究生选择要助教的课程*/
public class StudentService {

    /*当前登入的研究生信息*/
    private Student student;

    private StudentDao studentDAO;
    private CourseDao courseDAO;
    private AvailableAssistantDao availableAssistantDAO;

    public StudentService(String sno){
        courseDAO = DAOFactory.getInstance().getCourseDAO();
        availableAssistantDAO = DAOFactory.getInstance().getAvailableAssistantDAO();
        studentDAO = DAOFactory.getInstance().getStudentDAO();
        student = studentDAO.getBySno(sno);
        service();
    }

    /*服务入口*/
    public void service(){
        boolean flag = true;
        Scanner scanner = new Scanner(System.in);
        while(flag){
            System.out.println("\n————————助教工作管理————————");
            System.out.println("1：申请助教");
            System.out.println("2：查看已申请志愿");
            System.out.println("3：填写助教工作评定表");
            System.out.println("4：退出系统");
            System.out.print("输入选项：");
            int num = scanner.nextInt();
            if(num==1){
                showCourses();
            }else if(num==2){
                showApplication();
            }else if(num==3){
                showTable();
            }else if(num==4){
                flag=false;
            }else{
                System.out.println("请重新输入！");
            }
        }
    }

    /*1：助教课程选择，展示所有可被选择的课程，以课时数、选课人数降序排列*/
    public void showCourses(){
        System.out.println("\n————————申请助教————————");
        ArrayList<TeacherCourse> teacherCourses = courseDAO.getAllCoursesNotSelected();
        System.out.println("可供申请的课程：");
        if(teacherCourses.size()==0){
            System.out.println("暂无可选择的课程。");
        }else {
            System.out.println("共有 "+teacherCourses.size()+" 门可选择的课程，优先显示课时数多、选课人数多的课程。");
        }
        for(int i=0;i<teacherCourses.size();i++){
            TeacherCourse tc = teacherCourses.get(i);
            Course c = tc.getCourse();
            Teacher t = tc.getTeacher();
            System.out.println("课程编号："+c.getCno()+"  课程名称："+c.getCname()+"  授课教师："+t.getTname()+"  课程性质："+c.getCtype()+"  授课对象："+c.getTarget()+"  授课时间："+c.getChour()+"  选课人数："+c.getEnrolled_number());
        }
        Scanner scanner = new Scanner(System.in);
        HashSet<String> hs = new HashSet<>();
        ArrayList<AssistantRemark> myApply = availableAssistantDAO.getAllApplyBySno(student.getSno());

        int number = 0;
        String s = "";
        System.out.println("\n已递交的申请：");
        for(int i=0;i< myApply.size();i++){
            AvailableAssistant a = myApply.get(i).getAvailableAssistant();
            if(a.getStatus()==0 || (a.getStatus()==1&&a.getResult()!=2)){
                number++;//已申请尚未审核 或 已成功入选助教且未被评定不合格的数量
                s+=(a.getCno()+":"+myApply.get(i).getCourse().getCname())+"、";
            }
            System.out.print("课程编号："+a.getCno()+"  课程名："+myApply.get(i).getCourse().getCname()+"  申请状态："+(a.getStatus()==0?"未审核":(a.getStatus()==1?"选中":"未选中")));
            if(a.getStatus()==1){
                System.out.println("  评价结果："+(a.getResult()==0?"未审核":(a.getResult()==1?"合格":"不合格")));
            }else {
                System.out.println("");
            }
        }
        System.out.println("当前正在申请或正在助教 "+number+" 门课，还可申请 "+(2-number)+" 门。");
        for(int i=number;i<2;i=number+ hs.size()){
            System.out.println("\n选择：");
            System.out.println("1：提交助教申请");
            System.out.println("2：退出");
            System.out.print("输入选项：");
            int num = scanner.nextInt();
            if(num==1){
                if(teacherCourses.size()==0){
                    System.out.println("暂无可申请的课程。");
                    return;
                }
                System.out.print("输入课程号：");
                String cno = scanner.next();
                if(checkExist(teacherCourses,cno)){
                    if(checkNotSelected(myApply,cno)){
                        hs.add(cno);
                    }else{
                        System.out.println("已递交该申请！");
                    }
                }else{
                    System.out.println("课程不存在或已有助教！");
                }
            }else if(num==2){
                break;
            }
        }
        for(String ss:hs){
            AvailableAssistant aa = new AvailableAssistant();
            aa.setCno(ss);
            aa.setSno(student.getSno());
            aa.setStatus(0);
            aa.setResult(0);

            availableAssistantDAO.addAvailableAssistant(aa);
        }
    }

    /*2：查看已申请的助教课程和审核通过情况*/
    public void showApplication(){
        System.out.println("\n————————查看已申请志愿————————");
        ArrayList<AssistantRemark> myApply = availableAssistantDAO.getAllApplyBySno(student.getSno());
        System.out.println("已递交的申请：");
        if(myApply.size()==0){
            System.out.println("暂无申请。");
            return;
        }
        for (AssistantRemark ar:myApply){
            Course course = ar.getCourse();
            AvailableAssistant a = ar.getAvailableAssistant();
            System.out.print("课程编号："+a.getCno()+"  课程名："+ar.getCourse().getCname()+"  申请状态："+(a.getStatus()==0?"未审核":(a.getStatus()==1?"选中":"未选中")));
            if(a.getStatus()==1){
                System.out.println("  评价结果："+(a.getResult()==0?"未审核":(a.getResult()==1?"合格":"不合格")));
            }else {
                System.out.println("");
            }
        }
    }

    /*3：查看已选中课程的工作评定表*/
    public void showTable(){
        System.out.println("\n————————填写助教工作评定表————————");
        ArrayList<AssistantRemark> myApply = availableAssistantDAO.getAllSelectedApplyBySno(student.getSno());
        System.out.println("正在助教的课程：");
        if(myApply.size()==0){
            System.out.println("暂无正在助教的课程。");
            return;
        }
        for (int i=0;i< myApply.size();i++){
            Course course = myApply.get(i).getCourse();
            AvailableAssistant availableAssistant = myApply.get(i).getAvailableAssistant();
            System.out.println("课程编号："+availableAssistant.getCno()+"  课程名称："+course.getCname());
        }
        boolean flag = true;
        Scanner scanner = new Scanner(System.in);
        while (flag){
            System.out.println("\n选择：");
            System.out.println("1：查看工作评价表");
            System.out.println("2：填写工作评价表");
            System.out.println("3：返回上级");
            System.out.print("输入选项：");
            int num = scanner.nextInt();
            if(num==1 || num==2){
                System.out.print("输入课程编号：");
                String cnum = scanner.next();
                int idx=findIndex(myApply,cnum);
                if(idx!=-1){
                    AssistantRemark ar = myApply.get(idx);
                    Course c = ar.getCourse();
                    AvailableAssistant aa = ar.getAvailableAssistant();
                    Teacher t = ar.getTeacher();

                    System.out.println("\n————————《信息学院研究生助教工作评定表》————————");
                    System.out.println("研究生姓名："+student.getSname());
                    System.out.println("研究生学号："+student.getSno());
                    System.out.println("课程名称："+c.getCname());
                    System.out.println("授课人数："+c.getEnrolled_number());
                    System.out.println("研究生所在学科："+student.getSmajor());
                    System.out.println("课程性质："+c.getCtype());
                    System.out.println("授课对象："+c.getTarget());
                    System.out.println("授课教师："+t.getTname());
                    System.out.println("授课时间："+c.getChour());
                    System.out.println("助教工作自述："+ aa.getStudent_comment());
                    System.out.println("助教签字时间："+aa.getStudent_comment_time());
                    System.out.println("授课教师评价："+aa.getTeacher_comment());
                    System.out.println("授课教师签字时间："+aa.getTeacher_comment_time());
                    System.out.println("评价结果："+(aa.getResult()==0?"未审核":(aa.getResult()==1?"合格":"不合格")));

                    if(num==2){
                        System.out.print("\n请输入助教工作自述：");
                        String cmt = scanner.next();
                        availableAssistantDAO.updateAvailableAssistant_student(aa.getCno(), student.getSno(), cmt);
                    }
                    flag = false;
                }else {
                    System.out.println("请重新输入！");
                }
            }else if(num==3){
                flag = false;
            }else {
                System.out.println("请重新输入！");
            }
        }
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

    public boolean checkNotSelected(ArrayList<AssistantRemark> selected,String cno){
        for(AssistantRemark a:selected){
            if(a.getAvailableAssistant().getCno().equals(cno)){
                return false;
            }
        }
        return true;
    }

    public int findIndex(ArrayList<AssistantRemark> apply,String cno){
        for(int i=0;i<apply.size();i++){
            if(apply.get(i).getAvailableAssistant().getCno().equals(cno)){
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        StudentService s = new StudentService("1");
    }
}
