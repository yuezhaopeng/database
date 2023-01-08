package Dao.Impl;

import Dao.LoginCheckDAO;
import Entity.LoginCheck;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginCheckDaoImpl extends DaoBase implements LoginCheckDAO {

    private static final String SQL_SELECT_LOGIN_CHECK_BY_USERNAME = "SELECT username, password FROM login_check where username=?";

    /**
     * 根据用户名查询密码
     * @param username 用户输入的用户名
     * @return 返回LoginCheck类对象，包含了用户名和查询到的密码
     */
    @Override
    public LoginCheck getLoginCheckByUsername(String username) {
        Connection con = null;
        try{
            con = getConnection();
            PreparedStatement psmt = con.prepareStatement(SQL_SELECT_LOGIN_CHECK_BY_USERNAME);

            psmt.setString(1, username);
            ResultSet rs = psmt.executeQuery();
            LoginCheck loginCheck = new LoginCheck();
            if (rs.next()) {
                loginCheck.setUsername(username);
                loginCheck.setPassword(rs.getString("password"));
            } else {
                System.out.println("未查询到该用户名的数据");
            }

            // 如果查询到不止一条数据
            if(rs.next() == true) {
                System.out.println("查询错误");
                System.exit(0);
            }
            psmt.close();
            return loginCheck;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static final String SQL_INSERT_LOGIN_CHECK = "INSERT INTO login_check(username,password) values(?,?)";
    /**
     * 在LoginCheck中插入一条记录
     * @param username 用户名
     * @param password 密码
     */
    @Override
    public void addLoginCheck(String username, String password) {
        Connection con = null;
        try{
            con = getConnection();
            PreparedStatement psmt = con.prepareStatement(SQL_INSERT_LOGIN_CHECK);
            psmt.setString(1, username);
            psmt.setString(2, password);

            int result = psmt.executeUpdate();
            if (result > 0) {
                System.out.println("addLoginCheck成功");
            } else {
                System.out.println("addLoginCheck失败");
            }
            psmt.close();
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            try{
                con.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }


}
