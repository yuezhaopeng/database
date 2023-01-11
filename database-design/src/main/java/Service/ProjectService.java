package Service;

import Dao.Impl.ProjectDistributeDAOImpl;
import Dao.Impl.ProjectMentorDAOImpl;
import Entity.ProjectDistribute;
import Entity.ProjectMentor;
import Utils.DAOFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

public class ProjectService {

    /**
     * 子系统菜单
     * @param userId 当前登陆用户的编号，例如Sno，Lno等
     * @param userNum 角色的编号
     * @throws ParseException
     * @throws IOException
     */
    public static void showMenu(String userId, int userNum) throws ParseException, IOException {
        String role;
        if (userNum == 1) {
            System.out.println("欢迎您，您的编号是 " + userId + ", 您的角色是 admin");
            adminMenu();
        } else if (userNum == 2) {
            System.out.println("欢迎您，您的编号是 " + userId + ", 您的角色是 leader");
            leaderMenu(userId);
        } else if (userNum == 3) {
            System.out.println("欢迎您，您的编号是 " + userId + ", 您的角色是 teacher");
            System.out.println("授课教师无需操作该部分");
        } else if (userNum == 4) {
            System.out.println("欢迎您，您的编号是 " + userId + ", 您的角色是 mentor");
            mentorMenu(userId);
        } else if (userNum == 5) {
            System.out.println("欢迎您，您的编号是 " + userId + ", 您的角色是 student");
            studentMenu(userId);
        } else {
            System.out.println("角色错误，返回");
            return;
        }

    }

    /**
     * 研究生培养管理员菜单
     * 可以管理系统中项目信息，并且导出存档
     */
    public static void adminMenu() throws ParseException, IOException {
        Scanner sc = new Scanner(System.in);

        while(true) {
            System.out.println("这里是研究生培养管理员对导师项目的管理模块");
            System.out.println("1. 查看当前所有导师的所有项目");
            System.out.println("2. 添加导师的项目");
            System.out.println("3. 删除导师的项目");
            System.out.println("4. 导出所有导师的项目数据");
            System.out.println("0. 退出");
            System.out.println("请输入要进行操作的序号：");
            int option = sc.nextInt();
            if (option == 0) {
                break;
            } else if (option == 1) {
                showAllProjectMentors();
            } else if (option == 2) {
                addProjectMentor();
            } else if (option == 3) {
                deleteProjectMentor();
            } else if (option == 4) {
                exportProjectMentor();
            }
        }
    }

    /**
     * 导师角色的菜单
     */
    public static void mentorMenu(String userId) {
        Scanner sc = new Scanner(System.in);

        while(true) {
            System.out.println("这里是导师对项目的管理模块");
            System.out.println("1. 查看我的所有项目");
            System.out.println("2. 查看所有项目分配指派情况");
            System.out.println("3. 将我的项目指派分配给学生");
            System.out.println("4. 填报学生参与我的项目折合经费并且签字");
            System.out.println("0. 退出");
            System.out.println("请输入要进行操作的序号：");
            int option = sc.nextInt();
            if (option == 0) {
                break;
            } else if (option == 1) {
                showProjectMentorsByMno(userId);
            } else if (option == 2) {
                showAllProjectDistribute();
            } else if (option == 3) {
                mentorDistribute(userId);
            } else if (option == 4) {
                mentorGiveMoneyAndSign(userId);
            }
        }
    }

    /**
     * 学生角色的菜单
     * @param userId 这里对应学生的编号Sno
     */
    public static void studentMenu(String userId) {
        Scanner sc = new Scanner(System.in);

        while(true) {
            System.out.println("这里是学生的项目管理模块");
            System.out.println("1. 查看我分配到的所有项目");
            System.out.println("2. 完善我的项目认定表");
            System.out.println("0. 退出");
            System.out.println("请输入要进行操作的序号：");
            int option = sc.nextInt();
            if (option == 0) {
                break;
            } else if (option == 1) {
                showProjectDistributeBySno(userId);
            } else if (option == 2) {
                studentComplete(userId);
            }
        }
    }

    /**
     * 负责人的菜单
     * @param userId 当前登陆负责人的编号，即Lno
     */
    public static void leaderMenu(String userId) {
        Scanner sc = new Scanner(System.in);

        while(true) {
            System.out.println("这里是负责人对项目的管理模块");
            System.out.println("1. 查看我负责的所有项目");
            System.out.println("2. 查看我负责项目的分配指派情况");
            System.out.println("3. 填报学生参与我的项目折合经费并且签字");
            System.out.println("0. 退出");
            System.out.println("请输入要进行操作的序号：");
            int option = sc.nextInt();
            if (option == 0) {
                break;
            } else if (option == 1) {
                showProjectMentorsByLno(userId);
            } else if (option == 2) {
                showProjectDistributeForLeader(userId);
            } else if (option == 3) {
                leaderSign(userId);
            }
        }
    }


    /**
     * 显示当前登陆导师所有的项目
     * @param Mno 导师编号
     */
    public static void showProjectMentorsByMno(String Mno) {
        ProjectMentorDAOImpl projectMentorDAOImpl = (ProjectMentorDAOImpl) DAOFactory.getInstance().getProjectMentorDAO();
        List<ProjectMentor> list = projectMentorDAOImpl.getProjectMentorsByMno(Mno);
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }

    /**
     * 导师指定科研项目
     * 在project_distribute表中新增一个记录
     */
    public static void mentorDistribute(String Mno) {
        ProjectMentorDAOImpl projectMentorDAOImpl = (ProjectMentorDAOImpl) DAOFactory.getInstance().getProjectMentorDAO();
        List<ProjectMentor> list = projectMentorDAOImpl.getProjectMentorsByMno(Mno);

        Scanner sc = new Scanner(System.in);
        ProjectDistributeDAOImpl projectDistributeDAOImpl = (ProjectDistributeDAOImpl) DAOFactory.getInstance().getProjectDistributeDAO();
        System.out.println("请输入要指派的项目编号Pno：");
        String Pno = sc.nextLine();

        // 判断这是否是我的项目
        boolean flag = false;
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).getPno().trim().equals(Pno.trim())) {
                flag = true;
                break;
            }
        }
        if (flag == false) {
            System.out.println("这不是您的项目，请确定后重新分配");
            return;
        }
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
     * 显示当前登陆学生分配到的所有项目
     * @param Sno
     */
    public static void showProjectDistributeBySno(String Sno) {
        ProjectDistributeDAOImpl projectDistributeDAOImpl = (ProjectDistributeDAOImpl) DAOFactory.getInstance().getProjectDistributeDAO();
        List<ProjectDistribute> list = projectDistributeDAOImpl.getProjectDistributeBySno(Sno);
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
        System.out.println("查询成功");
    }


    /**
     * 学生填写认定表
     * 填写start_time, end_time, responsibility这几个字段
     */
    public static void studentComplete(String Sno) {
        // 首先获取该学生分配到的所有项目列表，以便后续判断是否填写错误
        ProjectDistributeDAOImpl projectDistributeDAOImpl = (ProjectDistributeDAOImpl) DAOFactory.getInstance().getProjectDistributeDAO();
        List<ProjectDistribute> list = projectDistributeDAOImpl.getProjectDistributeBySno(Sno);

        Scanner sc = new Scanner(System.in);
        System.out.println("请输入您要完善的项目编号Pno：");
        String Pno = sc.nextLine();

        // 判断学生是否有这个项目
        boolean flag = false;
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).getPno().trim().equals(Pno.trim())) {
                flag = true;
                break;
            }
        }
        if (flag == false) {
            System.out.println("这不是您的项目，请确定后重新操作");
            return;
        }

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
     * 导师填报学生参与项目折合的经费并且签字
     * 导师update project_distribute set personal_money=?
     * 然后判断是否大于等于6.0000，是就签字，否则重新指定经费数量
     */
    public static void mentorGiveMoneyAndSign(String Mno) {
        ProjectMentorDAOImpl projectMentorDAOImpl = (ProjectMentorDAOImpl) DAOFactory.getInstance().getProjectMentorDAO();
        List<ProjectMentor> list = projectMentorDAOImpl.getProjectMentorsByMno(Mno);

        Scanner sc = new Scanner(System.in);
        ProjectDistributeDAOImpl projectDistributeDAOImpl = (ProjectDistributeDAOImpl) DAOFactory.getInstance().getProjectDistributeDAO();
        System.out.println("请输入学生编号Sno：");
        String Sno = sc.nextLine();
        System.out.println("请输入要操作的该学生的项目编号Pno：");
        String Pno = sc.nextLine();

        // 判断这是否是我的项目
        boolean flag = false;
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).getPno().trim().equals(Pno.trim())) {
                flag = true;
                break;
            }
        }
        if (flag == false) {
            System.out.println("这不是您的项目，请确定后重新操作");
            return;
        }

        System.out.println("请输入要分配给该学生项目的经费（单位万元），格式如6.0000：");
        BigDecimal personalMoney = new BigDecimal(sc.nextLine().trim());
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
     * 显示当前登陆的负责人负责的所有项目信息
     * @param Lno
     */
    public static void showProjectMentorsByLno(String Lno) {
        ProjectMentorDAOImpl projectMentorDAOImpl = (ProjectMentorDAOImpl) DAOFactory.getInstance().getProjectMentorDAO();
        List<ProjectMentor> list = projectMentorDAOImpl.getProjectMentorsByLno(Lno);
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
        System.out.println("查询成功");
    }

    /**
     * 显示当前登陆的负责人负责项目的分配指派情况
     * @param Lno 负责人的编号
     */
    public static void showProjectDistributeForLeader(String Lno) {
        // 先获取自己负责的项目编号Pno
        ProjectMentorDAOImpl projectMentorDAOImpl = (ProjectMentorDAOImpl) DAOFactory.getInstance().getProjectMentorDAO();
        List<ProjectMentor> list = projectMentorDAOImpl.getProjectMentorsByLno(Lno);
        ProjectDistributeDAOImpl projectDistributeDAOImpl = (ProjectDistributeDAOImpl) DAOFactory.getInstance().getProjectDistributeDAO();

        // 然后根据Pno再进行查询分配指派情况
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).getPno());
            List<ProjectDistribute> list2 = projectDistributeDAOImpl.getProjectDistributeByPno(list.get(i).getPno().trim());
            for (int j = 0; j < list2.size(); j++) {
                System.out.println(list2.get(j).toString());
            }
        }
        System.out.println("查询成功");
    }

    /**
     * 项目负责人签字
     */
    public static void leaderSign(String Lno) {
        Scanner sc = new Scanner(System.in);
        ProjectDistributeDAOImpl projectDistributeDAOImpl = (ProjectDistributeDAOImpl) DAOFactory.getInstance().getProjectDistributeDAO();
        System.out.println("请输入学生编号Sno：");
        String Sno = sc.nextLine();
        System.out.println("请输入要操作的该学生的项目编号Pno：");
        String Pno = sc.nextLine();
        // 获取自己负责的项目
        ProjectMentorDAOImpl projectMentorDAOImpl = (ProjectMentorDAOImpl) DAOFactory.getInstance().getProjectMentorDAO();
        List<ProjectMentor> list1 = projectMentorDAOImpl.getProjectMentorsByLno(Lno);
        // 判断输入的项目是否是自己负责
        boolean isMyProject = false;
        for (int i = 0; i < list1.size(); i++) {
            if (list1.get(i).getPno().trim().equals(Pno.trim())) {
                isMyProject = true;
                break;
            }
        }
        if (isMyProject == false) {
            System.out.println("该项目不是您负责的，无法进行签字操作");
            return;
        }

        boolean flag = false;
        ProjectDistribute projectDistribute = null;
        List<ProjectDistribute> list = projectDistributeDAOImpl.getProjectDistributeBySno(Sno);
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getPno().trim().equals(Pno.trim())) {
                flag = true;
                projectDistribute = list.get(i);
                break;
            }
        }
        if(flag == false) {
            System.out.println("未查询到该条记录，请检查是否输入有误");
            return;
        } else {
            System.out.println("查询到的记录如下：");
            System.out.println(projectDistribute.toString());
        }
        if (projectDistribute.getPersonalMoney().compareTo(BigDecimal.valueOf(6.0)) < 0) {
            System.out.println("分配到的经费小于六万元，不符合要求，无法签字");
            return;
        } else {
            System.out.println("分配到的经费是 " + projectDistribute.getPersonalMoney() + ",大于等于6万元，符合要求，可以签字");
        }
        System.out.println("请确认是否签字，确认输入1，否则输入0：");
        String ack = sc.nextLine();
        if (ack.equals("1")) {
            // 导师已经判断这里的经费是大于等于6的，不用再重复判断
            System.out.println("正在操作");
            projectDistributeDAOImpl.chiefSign(Sno, Pno);
            System.out.println("操作成功");
        } else {
            System.out.println("不签字，直接返回");
            return;
        }
//        sc.close();
    }


    /**
     * 添加一条导师的项目
     * 在project_mentor表中
     * 对应管理员的内容
     * Mno设置了外键，这里添加的时候一定要添加mentor表中存在的Mno，否则会报错
     */
    public static void addProjectMentor() throws ParseException {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入要添加的项目编号Pno：");
        String Pno = sc.nextLine();
        System.out.println("请输入要添加的项目名称Pname：");
        String Pname = sc.nextLine();
        System.out.println("请输入要添加的项目类型Ptype：");
        String Ptype = sc.nextLine();
        System.out.println("请输入要添加的项目总经费totalMoney（单位，万元）：");
        BigDecimal totalMoney = new BigDecimal(sc.nextLine().trim());
        System.out.println("请输入负责该项目的导师编号Mno：");
        String Mno = sc.nextLine();
        System.out.println("请输入项目的开始时间start_time，例如2001-01-01：");
        String startTime = sc.nextLine();
        System.out.println("请输入项目的结束时间end_time，例如2001-12-12：");
        String endTime = sc.nextLine();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date utilStartTime = sdf.parse(startTime.trim());
        java.util.Date utilEndTime = sdf.parse(endTime.trim());

        ProjectMentor projectMentor = new ProjectMentor();
        projectMentor.setPno(Pno);
        projectMentor.setPname(Pname);
        projectMentor.setPtype(Ptype);
        projectMentor.setTotalMoney(totalMoney);
        projectMentor.setMno(Mno);
        projectMentor.setStartTime(new java.sql.Date(utilStartTime.getTime()));
        projectMentor.setEndTime(new java.sql.Date(utilEndTime.getTime()));

        ProjectMentorDAOImpl projectMentorDAOImpl = (ProjectMentorDAOImpl) DAOFactory.getInstance().getProjectMentorDAO();
        projectMentorDAOImpl.addProjectMentor(projectMentor);

        System.out.println("添加成功");
    }

    /**
     * 删除一条导师的项目
     * 在project_mentor表中
     * 对应管理员的内容
     */
    public static void deleteProjectMentor() {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入要删除的项目的编号Pno：");
        String Pno = sc.nextLine();
        ProjectMentorDAOImpl projectMentorDAOImpl = (ProjectMentorDAOImpl) DAOFactory.getInstance().getProjectMentorDAO();
        projectMentorDAOImpl.deleteProjectMentorByPno(Pno);
        System.out.println("删除成功");
    }


    /**
     * 导出所有导师的项目数据
     * 在project_mentor表中
     * 对应管理员的内容
     */
    public static void exportProjectMentor() throws IOException {
        File f=new File("./导师项目信息.txt");
        FileOutputStream fos1=new FileOutputStream(f);
        OutputStreamWriter dos1=new OutputStreamWriter(fos1);
        ProjectMentorDAOImpl projectMentorDAOImpl = (ProjectMentorDAOImpl) DAOFactory.getInstance().getProjectMentorDAO();
        List<ProjectMentor> projectMentorList = projectMentorDAOImpl.getAllProjectMentors();
        for (int i = 0; i < projectMentorList.size(); i++) {
            dos1.write(i);
            dos1.write(projectMentorList.get(i).toString());
            dos1.write("\n");
        }
        System.out.println("导出成功，在当前目录下的txt文件");
        dos1.close();
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
        System.out.println("查询成功");
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
        System.out.println("查询成功");
    }


    public static void main(String[] args) throws ParseException, IOException {
        showMenu("0000",1);
    }
}
