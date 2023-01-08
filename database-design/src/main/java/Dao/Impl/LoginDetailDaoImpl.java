package Dao.Impl;

import Dao.LoginDetailDAO;
import Entity.LoginDetail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginDetailDaoImpl extends DaoBase implements LoginDetailDAO {

    private static final String SQL_SELECT_LOGIN_DETAIL_BY_USERNAME = "SELECT username, role, no FROM login_detail WHERE username=?";
    /**
     * 根据用户输入的username查询role
     * @param username 用户输入的username
     * @return LoginDetail对象，包含了查询到的no和role
     */
    @Override
    public LoginDetail getLoginDetailByUsername(String username) {
        Connection con = null;
        try{
            con = getConnection();
            PreparedStatement psmt = con.prepareStatement(SQL_SELECT_LOGIN_DETAIL_BY_USERNAME);

            psmt.setString(1, username);
            ResultSet rs = psmt.executeQuery();
            LoginDetail loginDetail = new LoginDetail();
            if (rs.next()) {
                loginDetail.setUsername(username);
                loginDetail.setNo(rs.getString("no"));
                loginDetail.setRole(rs.getString("role"));
            } else {
                System.out.println("未查询到该用户名的信息");
            }
            // 如果能查到不止一条数据
            if(rs.next() == true) {
                System.out.println("查询错误");
                System.exit(0);
            }
            psmt.close();
            return loginDetail;
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

    private static final String SQL_INSERT_LOGIN_DETAIL = "INSERT INTO login_detail(username,role,no) values(?,?,?)";

    /**
     * 在LoginCheck中插入一条记录
     * @param username 要插入的username
     * @param role 要插入的role
     * @param no 要插入的no
     */
    @Override
    public void addLoginDetail(String username, String role, String no) {
        Connection con = null;
        try{
            con = getConnection();
            PreparedStatement psmt = con.prepareStatement(SQL_INSERT_LOGIN_DETAIL);
            psmt.setString(1, username);
            psmt.setString(2, role);
            psmt.setString(3, no);

            int result = psmt.executeUpdate();
            if (result > 0) {
                System.out.println("addLoginDetail成功");
            } else {
                System.out.println("addLoginDetail失败");
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
    private static final String SQL_EXIST_LOGIN_DETAIL = "SELECT COUNT(*) FROM login_detail WHERE role = 'student' and no = ?";
    @Override
    public boolean isExistStudent(String Sno) {
        boolean check =false;
        Connection con = null;
        try{
            con = getConnection();
            PreparedStatement psmt = con.prepareStatement(SQL_EXIST_LOGIN_DETAIL);
            psmt.setString(1, Sno);
            ResultSet result = psmt.executeQuery();
            if(result.next()){
                check= result.getInt(1) != 0;
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
        return check;
    }
}
