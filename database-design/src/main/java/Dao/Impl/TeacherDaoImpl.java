package Dao.Impl;

import Dao.TeacherDao;
import Entity.Teacher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class TeacherDaoImpl extends DaoBase implements TeacherDao {
    private static final String SQL_INSERT_TEACHER = "INSERT INTO teacher(Tno,Tname) values(?,?)";
    private final static String T_SELECT_SQL = "select * from teacher where tno=?";

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

    @Override
    public Teacher getByTno(String tno) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Teacher teacher = null;
        try {
            con = getConnection();
            ps = con.prepareStatement(T_SELECT_SQL);
            ps.setString(1,tno);
            rs = ps.executeQuery();
            if(rs.next()){
                teacher = new Teacher();
                teacher.setTno(tno);
                teacher.setTname(rs.getString("tname"));
            }
        }catch (Exception e){

        }finally {
            try {
                rs.close();
                ps.close();
                con.close();
            }catch (Exception e){

            }
        }
        return teacher;
    }
}
