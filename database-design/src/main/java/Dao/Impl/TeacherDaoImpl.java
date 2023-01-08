package Dao.Impl;

import Dao.TeacherDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class TeacherDaoImpl extends DaoBase implements TeacherDAO {
    private static final String SQL_INSERT_TEACHER = "INSERT INTO teacher(Tno,Tname) values(?,?)";
    @Override
    public void addTeacher(String Tno, String Tname) {
        Connection con = null;
        try{
            con = getConnection();
            PreparedStatement psmt = con.prepareStatement(SQL_INSERT_TEACHER);
            psmt.setString(1, Tno);
            psmt.setString(2, Tname);

            int result = psmt.executeUpdate();
            if (result > 0) {
                System.out.println("addTeacher成功");
            } else {
                System.out.println("addTeacher失败");
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
