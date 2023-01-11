package Service;

import Entity.Student;
import Utils.DAOFactory;

import java.util.List;
import java.util.Scanner;

// 研究生培养管理人员的业务层
// Scanner在程序运行过程中关闭后无法重新打开，会报错，所以这里不进行close，以便多次使用
public class AdminService {
    /**
     * 添加任何角色的登陆信息，都用这个
     */
    public static void addLoginInfo() {
        Scanner sc = new Scanner(System.in);

        System.out.println("请输入要添加用户的用户名username：");
        String username = sc.nextLine();
        System.out.println("请输入要添加用户的密码password：");
        String password = sc.nextLine();

        System.out.println("注意，一个username只对应一个角色，一个no的人可以有多个username");
        System.out.println("请输入要添加用户的角色role(填leader/teacher/mentor/student)：");
        String role = sc.nextLine();
        while (!role.equals("leader") && !role.equals("mentor") && !role.equals("teacher") && !role.equals("student")) {
            System.out.println("输入有误，请重新输入添加用户的角色：");
            role = sc.nextLine();
        }
        System.out.println("请输入要添加角色的编号no(可以是Sno, Tno等)：");
        String no = sc.nextLine();
        DAOFactory.getInstance().getLoginCheckDAO().addLoginCheck(username, password);
        DAOFactory.getInstance().getLoginDetailDAO().addLoginDetail(username, role, no);
//        loginCheckDAO.addLoginCheck(username, password);
//        loginDetailDAO.addLoginDetail(username, role, no);
        System.out.println("添加成功");
//        sc.close();
    }


    /**
     * 添加学科负责人的基本信息
     */
    public static void addLeader() {
        Scanner sc = new Scanner(System.in);

        System.out.println("请输入要添加的学科负责人的编号Lno：");
        String Lno = sc.nextLine();
        System.out.println("请输入要添加的学科负责人的姓名Lname：");
        String Lname = sc.nextLine();
        System.out.println("请输入要添加负责人对应的学科：");
        String subject = sc.nextLine();

        DAOFactory.getInstance().getLeaderDAO().addLeader(Lno, Lname, subject);
//        leaderDAO.addLeader(Lno, Lname, subject);
        System.out.println("添加成功");
//        sc.close();
    }

    /**
     * 添加授课教师的基本信息
     */
    public static void addTeacher() {
        Scanner sc = new Scanner(System.in);

        System.out.println("请输入要添加的授课教师的编号Tno：");
        String Tno = sc.nextLine();
        System.out.println("请输入要添加的授课教师的姓名Tname：");
        String Tname = sc.nextLine();

        DAOFactory.getInstance().getTeacherDao().addTeacher(Tno, Tname);
//        teacherDAO.addTeacher(Tno, Tname);
        System.out.println("添加成功");
//        sc.close();
    }

    /**
     * 添加导师基本信息并与学科负责人建立联系
     */
    public static void addMentor() {
        Scanner sc = new Scanner(System.in);

        System.out.println("请输入要添加的导师的编号Mno：");
        String Mno = sc.nextLine();
        System.out.println("请输入要添加的导师的姓名Mname：");
        String Mname = sc.nextLine();
        // 如果需要可以先展示一下已有的学科listLender,方法已经写好
        System.out.println("请输入要添加的导师的学科负责人的编号：");
        String Lno = sc.nextLine();

        boolean check = DAOFactory.getInstance().getMentorDao().addMentor(Mno, Mname, Lno);
        if (check) {
            System.out.println("添加成功");
        } else {
            System.out.println("添加失败");
        }
//        sc.close();
    }

    /**
     * 添加学生基础信息
     */
    public static void addStudent() {
        Scanner sc = new Scanner(System.in);

        System.out.println("请输入要添加的学生的编号Sno：");
        String Sno = sc.nextLine();
        System.out.println("请输入要添加的学生的姓名Sname：");
        String Sname = sc.nextLine();
        System.out.println("请输入要添加的学生的专业Smajor：");
        String Smajor = sc.nextLine();
        // 如果需要可以先展示一下已有的导师listMentor,方法已经写好
        System.out.println("请输入要添加的学生的导师编号：");
        String Mno = sc.nextLine();
        String Stype = null;
        do {
            System.out.println("请输入要添加的学生的类型 1.学硕，2.专硕，3，博士");
            Stype = sc.nextLine().trim();
        } while (!Stype.equals("1") && !Stype.equals("2") && !Stype.equals("3"));


        boolean check = DAOFactory.getInstance().getStudentDao().addStudent(Sno, Sname, Smajor, Mno, Stype);
        if (check) {
            System.out.println("添加成功");
        } else {
            System.out.println("添加失败");
        }
//        sc.close();
    }

    /**
     * 自动生成学生账号,可以直接把参数Mno放入函数参数，不用输入
     */
    public static void autoGenerateStudentLoginAccountByMno() {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入要为某导师所有学生生成登陆账号的导师号");
        String Mno = sc.nextLine();

        List<Student> students = DAOFactory.getInstance().getStudentDao().listStudent();
        for (Student student : students) {
            if (student.getMno() != null && student.getMno().equals(Mno) && !DAOFactory.getInstance().getLoginDetailDAO().isExistStudent(student.getSno())) {
                DAOFactory.getInstance().getLoginCheckDAO().addLoginCheck(student.getSno(), student.getSno());
                DAOFactory.getInstance().getLoginDetailDAO().addLoginDetail(student.getSno(), "student", student.getSno());
            }
        }
        System.out.println("生成完成，默认账号为学号，默认密码为学号");
    }
}
