package Service;


import Utils.DAOFactory;

import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

public class Login {

    //主菜单
    public static void mainMenu(String userid, int roleNum) throws ParseException, IOException {
        Scanner sc = new Scanner(System.in);
        boolean flag = true;
        while (flag){
            System.out.println("*******功能选择*******");
            System.out.println("1-研究生助教工作");
            System.out.println("2-研究生学术交流工作");
            System.out.println("3-研究生参与项目工作");
            System.out.println("4-研究生成果认定工作");
            System.out.println("5-用户管理");
            System.out.println("6-退出");
            System.out.print("请输入选择：");
            int c=sc.nextInt();
            while(c!=1&&c!=2&&c!=3&&c!=4&&c!=5&&c!=6) {
                System.out.println("您的输入错误，请输入1/2/3/4/5/6!");
                c=sc.nextInt();
            }
            switch(c) {
                case 1:
                    //助教工作子系统入口，传参userid,roleNum
                    break;
                case 2:
                    //学术交流子系统入口，传参userid,roleNum
                    ActivityService.acticityMenu(userid,roleNum);
                    break;
                case 3:
                    //参与项目子系统入口，传参userid,roleNum
                    ProjectService.showMenu(userid, roleNum);
                    break;
                case 4:
                    //成果认定子系统入口，传参userid,roleNum
                    AchieveService.achieveMenu(userid,roleNum);
                    break;
                case 5:
                    //用户管理，传参userid,roleNum
                    AdminService.addLoginInfo();
                    break;
                case 6:
                    flag = false;
                    break;
            }
        }
    }

    /**
     * 因为username是主码，所以1个username只对应一个角色，但是一个no可以有多个username
     * @return 登陆成功返回true，失败返回false
     *
     */
    public static void login() throws ParseException, IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("*******研究生毕业管理系统*******");
        System.out.println("请输入您的用户名：");
        String username = sc.nextLine();
        System.out.println("请输入您的密码：");
        String password = sc.nextLine();
        System.out.println("1 研究生培养管理员");
        System.out.println("2 学科负责人");
        System.out.println("3 授课教师");
        System.out.println("4 导师");
        System.out.println("5 研究生");
        System.out.println("请输入该账户的角色序号：");
        String role = null;
        int roleNum = sc.nextInt();
        while(roleNum!=1&&roleNum!=2&&roleNum!=3&&roleNum!=4&&roleNum!=5) {
            System.out.println("输入错误！请输入1/2/3/4/5!");
            roleNum = sc.nextInt();
        }
        if (roleNum == 1) {
            role = "admin";
        } else if (roleNum == 2) {
            role = "leader";
        } else if (roleNum == 3) {
            role = "teacher";
        } else if (roleNum == 4) {
            role = "mentor";
        } else if (roleNum == 5) {
            role = "student";
        }

        // 从数据表中查询到的password和role
        String realPassword = DAOFactory.getInstance().getLoginCheckDAO().getLoginCheckByUsername(username).getPassword();
        String realRole = DAOFactory.getInstance().getLoginDetailDAO().getLoginDetailByUsername(username).getRole();

        if (realPassword!=null && realRole!=null && realPassword.trim().equals(password.trim()) && realRole.trim().equals(role)) {
            System.out.println("登录成功");
            mainMenu(DAOFactory.getInstance().getLoginDetailDAO().getLoginDetailByUsername(username).getNo(),roleNum);
        } else {
            System.out.println("登陆失败");
        }
    }
}
