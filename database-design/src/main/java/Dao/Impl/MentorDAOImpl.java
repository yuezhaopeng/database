package Dao.Impl;

import Dao.MentorDao;
import Entity.Mentor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MentorDaoImpl extends DaoBase implements MentorDao {
    private static final String SQL_INSERT_MENTOR = "INSERT INTO mentor (Mno,Mname,Lno) values(?,?,?)";
    private static final String SQL_SELECT_MENTOR = "SELECT * FROM mentor";
    @Override
    public boolean addMentor(String Mno, String Mname,String Lno) {
        boolean check = false;
        Connection con = null;
        try{
            con = getConnection();
            PreparedStatement psmt = con.prepareStatement(SQL_INSERT_MENTOR);
            psmt.setString(1, Mno);
            psmt.setString(2, Mname);
            psmt.setString(3, Lno);

            int result = psmt.executeUpdate();
            if (result > 0) {
                System.out.println("addMentor成功");
                check = true;
            } else {
                System.out.println("addMentor失败");
            }
            psmt.close();
        }catch(SQLException e){
            System.out.println("addMentor失败");
        }finally {
            try{
                con.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return check;
    }

    @Override
    public List<Mentor> listMentor() {
        Connection con = null;
        ArrayList<Mentor> list= new ArrayList();
        try{
            con = getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(SQL_SELECT_MENTOR);
            while(rs.next()){
                Mentor mentor=new Mentor(rs.getString(1),rs.getString(2),rs.getString(3));
                list.add(mentor);
            }
            st.close();
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            try{
                if(con!=null){
                    con.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return list;
    }


    // 以下是第3个子系统用到的方法
    private static final String SQL_SELECT_MENTOR_ALL = "SELECT * FROM mentor;";
    @Override
    public List<Mentor> getAllMentors() {
        Connection con = null;
        List<Mentor> list = new ArrayList<>();
        try{
            // 法一、使用prepareStatement
//            con = getConnection();
//            PreparedStatement psmt = con.prepareStatement(SQL_SELECT_STUDENT_ALL);
//            ResultSet rs = psmt.executeQuery();
            // 法二、使用Statement
            con = getConnection();
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(SQL_SELECT_MENTOR_ALL);
            while (rs.next()){
                Mentor mentor = new Mentor();
                mentor.setMno(rs.getString("Mno"));
                mentor.setMname(rs.getString("Mname"));
                mentor.setLno(rs.getString("Lno"));
                list.add(mentor);
            }
//            psmt.close();
            statement.close();
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            try{
                con.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return list;
    }


}
