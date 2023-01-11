package Dao.Impl;

import Dao.StudentDao;
import Entity.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDaoImpl extends DaoBase implements StudentDao {
    private static final String SQL_INSERT_STUDENT = "INSERT INTO student (Sno,Sname,Smajor,Mno,Stype) values(?,?,?,?,?)";
    private static final String SQL_SELECT_STUDENT = "SELECT * FROM student";
    private static final String STUDENT_SELECTBYSNO_SQL = "select * from student where sno=?";
    private static final String STUDENT_SELECTALL_SQL = "select * from student";
    private static final String COUNT_SQL = "select * from available_assistant where sno=?";

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

    @Override
    public Student getBySno(String sno) {
        Student student = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = getConnection();
            ps = con.prepareStatement(STUDENT_SELECTBYSNO_SQL);
            ps.setString(1,sno);
            rs = ps.executeQuery();
            if(rs.next()){
                student = new Student();
                student.setSno(rs.getString("sno"));
                student.setSname(rs.getString("sname"));
                student.setMno(rs.getString("mno"));
                student.setSmajor(rs.getString("smajor"));
            }
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            try {
                rs.close();
                ps.close();
                con.close();
            }catch (Exception e){

            }
        }
        return student;
    }

    @Override
    public ArrayList<Student> getAll() {
        Connection con = null;
        PreparedStatement ps = null;
        PreparedStatement pps = null;
        ResultSet rs = null;
        ResultSet rrs = null;
        ArrayList<Student> students = new ArrayList<>();
        try {
            con = getConnection();
            ps = con.prepareStatement(STUDENT_SELECTALL_SQL);
            rs = ps.executeQuery();
            while (rs.next()){
                Student stu = new Student();
                stu.setSno(rs.getString("sno"));
                stu.setSname(rs.getString("sname"));
                stu.setSmajor(rs.getString("smajor"));

                pps = con.prepareStatement(COUNT_SQL);
                pps.setString(1,stu.getSno());
                rrs = pps.executeQuery();
                int cnt_0 = 0;
                int cnt_1 = 0;
                int cnt_2 = 0;
                while(rrs.next()){
                    int status = rrs.getInt("status");
                    if(status==0){
                        cnt_0++;
                    }else if(status==1){
                        cnt_1++;
                    }else {
                        cnt_2++;
                    }
                }
                if(cnt_1>0){
                    stu.setMno("是");
                }else if(cnt_0>0){
                    stu.setMno("正在申请");
                }else {
                    stu.setMno("否");
                }
                students.add(stu);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                rs.close();
                rrs.close();
                ps.close();
                pps.close();
                con.close();
            }catch (Exception e){

            }
        }
        return students;
    }
}
