package Service.AssistantServiceHelp;


import Dao.AvailableAssistantDao;
import Dao.CourseDao;
import Dao.TeacherCourseDao;
import Dao.TeacherDao;
import Entity.AvailableAssistant;
import Entity.Course;
import Entity.Student;
import Entity.Teacher;
import Entity.vo.AssistantRemark;
import Utils.DAOFactory;

import java.util.ArrayList;
import java.util.Scanner;

/*授课教师选择助教*/
public class TeacherService {

    /*当前登入教师*/
    private Teacher teacher;
    private AvailableAssistantDao availableAssistantDAO;
    private CourseDao courseDAO;
    private TeacherCourseDao teacherCourseDAO;
    private TeacherDao teacherDAO;

    public TeacherService(String tno){
        availableAssistantDAO = DAOFactory.getInstance().getAvailableAssistantDAO();
        courseDAO = DAOFactory.getInstance().getCourseDAO();
        teacherCourseDAO = DAOFactory.getInstance().getTeacherCourseDAO();
        teacherDAO = DAOFactory.getInstance().getTeacherDAO();
        teacher = teacherDAO.getByTno(tno);
        service();
    }

    /*服务入口*/
    public void service(){
        boolean flag = true;
        Scanner scanner = new Scanner(System.in);
        while(flag){
            System.out.println("\n————————助教工作管理————————");
            System.out.println("1：录入课程信息");
            System.out.println("2：展示教授的所有课程");
            System.out.println("3：管理课程助教申请（只显示未选定助教的课）");
            System.out.println("4：填写助教工作评定表（只显示已选定助教的课）");
            System.out.println("5：退出系统");
            System.out.print("输入选项：");
            int num = scanner.nextInt();
            if(num==1){
                addCourse();
            }else if(num==2){
                showLessons();
            }else if(num==3){
                selectAssistant();
            }else if(num==4){
                showTable();
            }else if(num==5){
                flag = false;
            }else {
                System.out.println("请重新输入选择！");
            }
        }
    }

    /*1：教师录入课程信息*/
    public void addCourse(){
        System.out.println("\n————————录入课程信息————————");
        Scanner scanner = new Scanner(System.in);
        Course course = new Course();
        System.out.print("课程编号：");
        course.setCno(scanner.next());
        System.out.print("课程名：");
        course.setCname(scanner.next());
        System.out.print("课程类型（必修/选修）：");
        course.setCtype(scanner.next());
        System.out.print("授课对象（本科生/研究生）：");
        course.setTarget(scanner.next());
        System.out.print("课时数：");
        course.setChour(scanner.nextInt());
        System.out.print("选课人数：");
        course.setEnrolled_number(scanner.nextInt());
        course.setSelected(false);

        courseDAO.addCourse(course);
        teacherCourseDAO.addTeacherCourse(teacher.getTno(),course.getCno());
        System.out.println("添加成功！");
    }

    /*2：展示本人教授的所有课程*/
    public void showLessons(){
        System.out.println("\n————————展示教授的所有课程————————");
        System.out.println("教授的课程：");
        ArrayList<Course> courses = teacherCourseDAO.getAllCoursesByTno(teacher.getTno());
        for(int i=0;i<courses.size();i++){
            Course c = courses.get(i);
            System.out.println("课程编号："+c.getCno()+"  课程名称："+c.getCname()+"  课程类型："+c.getCtype()+"  授课对象："+c.getTarget()+"  授课时间："+c.getChour()+"  选课人数："+c.getEnrolled_number()+"  是否有助教："+(c.isSelected()?"是":"否"));
        }
        Scanner scanner = new Scanner(System.in);
        String num;
        while (courses.size()>0){
            System.out.print("\n输入课程编号查看助教详情：");
            num = scanner.next().trim();
            int idx = getIndex(courses,num);
            if(idx!=-1){
                Course c = courses.get(idx);
                if(c.isSelected()){
                    /*有助教*/
                    Student stu = availableAssistantDAO.getAllAssistantByCno(num,1).get(0);
                    System.out.println("课程 "+c.getCno()+":"+c.getCname()+" 的助教为：");
                    System.out.println("学生编号："+stu.getSno()+"  学生姓名："+stu.getSname()+"  学生专业："+stu.getSmajor());
                }else{
                    /*无助教*/
                    System.out.println("本门课程暂无助教。");
                }
                break;
            }else{
                System.out.println("请重新输入！");
            }
        }
    }

    /*3：根据课程号查询助教申请*/
    public void selectAssistant(){
        System.out.println("\n————————管理课程助教申请————————");
        System.out.println("教授的课程（只显示未选定助教的课）：");
        ArrayList<Course> courses = teacherCourseDAO.getCoursesByTno(teacher.getTno(),false);
        for(int i=0;i<courses.size();i++){
            Course c = courses.get(i);
            System.out.println("课程编号："+c.getCno()+"  课程名称："+c.getCname());
        }

        Scanner scanner = new Scanner(System.in);
        String num;
        while (courses.size()>0){
            System.out.print("\n输入课程编号查看助教详情：");
            num = scanner.next().trim();
            int idx = getIndex(courses,num);
            if(idx!=-1){
                getApplications(num);
                break;
            }else{
                System.out.println("请重新输入！");
            }
        }

    }

    /*4：填写助教工作评定表*/
    public void showTable(){
        System.out.println("\n————————填写助教工作评定表————————");
        System.out.println("教授的课程（只显示选定助教的课）：");
        ArrayList<Course> courses = teacherCourseDAO.getCoursesByTno(teacher.getTno(),true);
        for(int i=0;i<courses.size();i++){
            Course c = courses.get(i);
            System.out.println("课程编号："+c.getCno()+"  课程名称："+c.getCname());
        }

        Scanner scanner = new Scanner(System.in);
        String num;
        while (courses.size()>0){
            System.out.print("\n输入课程编号查看助教详情：");
            num = scanner.next().trim();
            int idx = getIndex(courses,num);
            if(idx!=-1){
                enterComment(num);
                break;
            }else{
                System.out.println("请重新输入！");
            }
        }
    }

    public int getIndex(ArrayList<Course> courses,String cno){
        for(int i=0;i<courses.size();i++){
            if(courses.get(i).getCno().equals(cno)){
                return i;
            }
        }
        return -1;
    }

    public int getStudentIndex(ArrayList<Student> students,String sno){
        for(int i=0;i<students.size();i++){
            if(students.get(i).getSno().equals(sno)){
                return i;
            }
        }
        return -1;
    }

    public void getApplications(String cno){
        System.out.println("\n申请本门课程的助教：");
        ArrayList<Student> students = availableAssistantDAO.getAllAssistantByCno(cno,0);
        if(students.size()==0){
            System.out.println("本门课暂无助教申请。");
        }else {
            for(int i=0;i<students.size();i++){
                Student s = students.get(i);
                System.out.println("学号："+s.getSno()+"  姓名："+s.getSname()+"  专业："+s.getSmajor());
            }
            boolean flag = true;
            Scanner scanner = new Scanner(System.in);
            while(flag){
                System.out.println("\n选择：");
                System.out.println("1：设置助教");
                System.out.println("2：返回上级");
                System.out.print("输入选项：");
                int num = scanner.nextInt();
                if(num==1){
                    System.out.print("请输入要设置为助教的学生学号：");
                    String select = scanner.next().trim();
                    int idx = getStudentIndex(students,select);
                    if(idx!=-1){
                        for(int i=0;i<students.size();i++){
                            availableAssistantDAO.updateAvailableAssistant_teacher(cno,students.get(i).getSno(),2);
                        }
                        availableAssistantDAO.updateAvailableAssistant_teacher(cno,select,1);
                        courseDAO.updateCourse(cno,true);
                        flag = false;
                    }else {
                        System.out.println("请重新输入！");
                    }
                }else if(num==2){
                    flag=false;
                }else {
                    System.out.println("请重新输入！");
                }
            }
        }
    }

    public void enterComment(String cno){
        AssistantRemark ar = availableAssistantDAO.getApplyByCno(cno);
        Student student = ar.getStudent();
        Course c = ar.getCourse();
        AvailableAssistant aa = ar.getAvailableAssistant();
        System.out.println("————————《信息学院研究生助教工作评定表》————————");
        System.out.println("研究生姓名："+student.getSname());
        System.out.println("研究生学号："+student.getSno());
        System.out.println("课程名称："+c.getCname());
        System.out.println("授课人数："+c.getEnrolled_number());
        System.out.println("研究生所在学科："+student.getSmajor());
        System.out.println("课程性质："+c.getCtype());
        System.out.println("授课对象："+c.getTarget());
        System.out.println("授课教师："+teacher.getTname());
        System.out.println("授课时间："+c.getChour());
        System.out.println("助教工作自述："+ aa.getStudent_comment());
        System.out.println("助教签字时间："+aa.getStudent_comment_time());
        System.out.println("授课教师评价："+aa.getTeacher_comment());
        System.out.println("授课教师签字时间："+aa.getTeacher_comment_time());
        System.out.println("评价结果："+(aa.getResult()==0?"未审核":(aa.getResult()==1?"合格":"不合格")));

        Scanner scanner = new Scanner(System.in);
        int num;
        while (true){
            System.out.println("\n选择：");
            System.out.println("1：填写评价表");
            System.out.println("2：返回上级");
            System.out.print("请输入选项：");
            num = scanner.nextInt();
            if(num==1){
                System.out.print("\n请输入助教工作评价（合格/不合格）：");
                String result = scanner.next().trim();
                System.out.print("请输入助教工作评语：");
                String cmt = scanner.next().trim();
                availableAssistantDAO.updateTeacherComment(aa.getCno(),aa.getSno(),(result.equals("合格")?1:2),cmt);
                break;
            }else if(num==2){
                break;
            }else {
                System.out.println("请重新输入！");
            }
        }
    }


    public static void main(String[] args) {
        TeacherService t = new TeacherService("1");
    }
}
