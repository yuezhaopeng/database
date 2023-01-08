package Service;

import Dao.Impl.LoginCheckDaoImpl;
import Dao.Impl.LoginDetailDaoImpl;

import java.util.Scanner;

public class Login {

    /**
     * 因为username是主码，所以1个username只对应一个角色，但是一个no可以有多个username
     * @return 登陆成功返回true，失败返回false
     */
    public static void login() {
        Scanner sc = new Scanner(System.in);
        LoginCheckDaoImpl loginCheckDAOImpl = new LoginCheckDaoImpl();
        LoginDetailDaoImpl loginDetailDAOImpl = new LoginDetailDaoImpl();
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
        } else {
            System.out.println("输入错误");
            System.exit(0);
        }
        // 从数据表中查询到的password和role
        String realPassword = loginCheckDAOImpl.getLoginCheckByUsername(username).getPassword();
        String realRole = loginDetailDAOImpl.getLoginDetailByUsername(username).getRole();
        if (realPassword.trim().equals(password.trim()) && realRole.trim().equals(role)) {

        } else {
            System.out.println("登陆失败");
        }
    }
}
