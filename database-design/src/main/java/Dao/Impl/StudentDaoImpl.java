package Dao.Impl;

import Dao.StudentDao;
import Entity.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDaoImpl extends DaoBase implements StudentDao {
    private static final String SQL_INSERT_STUDENT = "INSERT INTO student (Sno,Sname,Smajor,Mno,Stype) values(?,?,?,?,?)";
    private static final String SQL_SELECT_STUDENT = "SELECT * FROM student";
    @Override
    public boolean addStudent(String Sno, String Sname,String Smajor, String Mno,String Stype) {
        boolean check = false;
        Connection con = null;
        try{
            con = getConnection();
            PreparedStatement psmt = con.prepareStatement(SQL_INSERT_STUDENT);
            psmt.setString(1, Sno);
            psmt.setString(2, Sname);
            psmt.setString(3, Smajor);
            psmt.setString(4,Mno);
            psmt.setString(5,Stype);

            int result = psmt.executeUpdate();
            if (result > 0) {
                System.out.println("addStudent成功");
                check = true;
            } else {
                System.out.println("addStudent失败");
            }
            psmt.close();
        }catch(SQLException e){
            System.out.println("addStudent失败");
        }finally {
            try{
                if(con!=null){
                    con.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return check;
    }

    @Override
    public List<Student> listStudent() {
        Connection con = null;
        ArrayList<Student> list= new ArrayList();
        try{
            con = getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(SQL_SELECT_STUDENT);
            while(rs.next()){
                Student student=new Student(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5));
                list.add(student);
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
}
