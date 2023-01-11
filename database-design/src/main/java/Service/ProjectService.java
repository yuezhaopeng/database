package Service;



import Dao.Impl.ProjectDistributeDAOImpl;
import Dao.Impl.ProjectMentorDAOImpl;
import Entity.ProjectDistribute;
import Entity.ProjectMentor;
import Utils.DAOFactory;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

/**
 * 第三个子系统"研究生参与项目"的业务层
 */
public class ProjectService {

    /**
     * 1、导师指定科研项目
     * 在project_distribute表中新增一个记录
     */
    public static void mentorDistribute() {
        Scanner sc = new Scanner(System.in);
        ProjectDistributeDAOImpl projectDistributeDAOImpl = (ProjectDistributeDAOImpl) DAOFactory.getInstance().getProjectDistributeDAO();
        System.out.println("请输入要指派的项目编号Pno：");
        String Pno = sc.nextLine();
        System.out.println("请输入该项目指派给的学生编号Sno：");
        String Sno = sc.nextLine();
        System.out.println("正在project_distribute表中添加指派记录");

        ProjectDistribute projectDistribute = new ProjectDistribute();
        projectDistribute.setSno(Sno);
        projectDistribute.setPno(Pno);
        projectDistributeDAOImpl.mentorDistribute(projectDistribute);

        System.out.println("指派成功");
//        sc.close();
    }

    /**
     * 2、学生填写认定表
     * 填写start_time, end_time, responsibility这几个字段
     */
    public static void studentComplete() {
        Scanner sc = new Scanner(System.in);
        ProjectDistributeDAOImpl projectDistributeDAOImpl = (ProjectDistributeDAOImpl) DAOFactory.getInstance().getProjectDistributeDAO();
        System.out.println("请输入您的学号Sno：");
        String Sno = sc.nextLine();
        System.out.println("请输入您要完善的项目编号Pno：");
        String Pno = sc.nextLine();

        System.out.println("请输入项目开始时间，格式例如2001-01-01：");
        String startTime = sc.nextLine();
        System.out.println("请输入项目结束时间，格式例如2001-12-31：");
        String endTime = sc.nextLine();
        System.out.println("请输入在项目中承担的工作");
        String responsibility = sc.nextLine();

        System.out.println("正在完善project_distribute表");
        projectDistributeDAOImpl.studentComplete(Sno, Pno, startTime, endTime, responsibility);
        System.out.println("填写成功");
//        sc.close();
    }

    /**
     * 3、导师填报学生参与项目折合的经费并且签字
     * 导师update project_distribute set personal_money=?
     * 然后判断是否大于等于6.0000，是就签字，否则重新指定经费数量
     */
    public static void mentorGiveMoneyAndSign() {
        Scanner sc = new Scanner(System.in);
        ProjectDistributeDAOImpl projectDistributeDAOImpl = (ProjectDistributeDAOImpl) DAOFactory.getInstance().getProjectDistributeDAO();
        System.out.println("请输入学生编号Sno：");
        String Sno = sc.nextLine();
        System.out.println("请输入要操作的该学生的项目编号Pno：");
        String Pno = sc.nextLine();
        System.out.println("请输入要分配给该学生项目的经费（单位万元），格式如6.0000：");
        BigDecimal personalMoney = sc.nextBigDecimal();
        while (personalMoney.compareTo(BigDecimal.valueOf(6.00)) < 0) {
            System.out.println("分配的经费不可少于6.0000万元，请重新输入：");
            personalMoney = sc.nextBigDecimal();
        };
        System.out.println("正在操作");

        projectDistributeDAOImpl.mentorGiveMoneyAndSign(Sno, Pno, personalMoney);
        System.out.println("操作成功");
//        sc.close();
    }

    /**
     * 4、项目负责人签字，要先判断个人经费是否大于等于6.0000
     */
    public static void chiefSign() {
        Scanner sc = new Scanner(System.in);
        ProjectDistributeDAOImpl projectDistributeDAOImpl = (ProjectDistributeDAOImpl) DAOFactory.getInstance().getProjectDistributeDAO();
        System.out.println("请输入学生编号Sno：");
        String Sno = sc.nextLine();
        System.out.println("请输入要操作的该学生的项目编号Pno：");
        String Pno = sc.nextLine();
        // 导师已经判断这里的经费是大于等于6的，不用再重复判断
        System.out.println("正在操作");
        projectDistributeDAOImpl.chiefSign(Sno, Pno);
        System.out.println("操作成功");
//        sc.close();
    }

    /**
     * 查询并显示project_mentor表中所有数据
     */
    public static void showAllProjectMentors() {
        ProjectMentorDAOImpl projectMentorDAOImpl = (ProjectMentorDAOImpl) DAOFactory.getInstance().getProjectMentorDAO();
        List<ProjectMentor> projectMentorList = projectMentorDAOImpl.getAllProjectMentors();
        for (int i = 0; i < projectMentorList.size(); i++) {
            System.out.println(projectMentorList.get(i));
        }
    }

    /**
     * 查询并显示project_distribute表中所有数据
     */
    public static void showAllProjectDistribute() {
        ProjectDistributeDAOImpl projectDistributeDAOImpl = (ProjectDistributeDAOImpl) DAOFactory.getInstance().getProjectDistributeDAO();
        List<ProjectDistribute> projectDistributeList = projectDistributeDAOImpl.getAllProjectDistribute();
        for (int i = 0; i < projectDistributeList.size(); i++) {
            System.out.println(projectDistributeList.get(i));
        }
    }


    /**
     * 子系统菜单
     */
    public static void showMenu() {
        System.out.println("--------------------------------");
        System.out.println("研究生参与项目情况");
        int option = 0;
        Scanner sc = new Scanner(System.in);
        System.out.println("1. 导师指定科研项目");
        System.out.println("2. 研究生完善参与项目认定表");
        System.out.println("3. 导师填报折合经费并且审核签字");
        System.out.println("4. 项目负责人审核签字");
        System.out.println("5. 查询所有导师的所有项目信息");
        System.out.println("6. 查询所有项目的分配指派信息");
        System.out.println("7. 退出当前子系统");
        while(true) {
            System.out.println("请输入操作序号:");
            option = sc.nextInt();
            if (option == 7) {
                break;
            } else if (option == 1) {
                mentorDistribute();
            } else if (option == 2) {
                studentComplete();
            } else if (option == 3) {
                mentorGiveMoneyAndSign();
            } else if (option == 4) {
                chiefSign();
            } else if (option == 5) {
                showAllProjectMentors();
            } else if (option == 6) {
                showAllProjectDistribute();
            }
        }
    }


    public static void main(String[] args) {
        showMenu();
    }
}
