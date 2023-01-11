package Dao.Impl;


import Dao.TeacherCourseDao;
import Entity.Course;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class TeacherCourseDaoImpl extends DaoBase implements TeacherCourseDao {
    private static final String TC_SELECTALL_SQL = "select course.* from course,teacher_course where teacher_course.tno=? and teacher_course.cno=course.cno";
    private static final String TC_SELECTALLSELECTED_SQL = "select course.* from course,teacher_course where selected=? and teacher_course.tno=? and teacher_course.cno=course.cno";
    private static final String TC_ADD_SQL = "insert into teacher_course (tno,cno) values(?,?)";

    @Override
    public ArrayList<Course> getCoursesByTno(String tno, boolean selected) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Course> courses = new ArrayList<>();
        try {
            con = getConnection();
            ps = con.prepareStatement(TC_SELECTALLSELECTED_SQL);
            ps.setBoolean(1,selected);
            ps.setString(2,tno);
            rs = ps.executeQuery();
            while(rs.next()){
                Course c = new Course();
                c.setCno(rs.getString("cno"));
                c.setCname(rs.getString("cname"));
                c.setCtype(rs.getString("ctype"));
                c.setTarget(rs.getString("target"));
                c.setChour(rs.getInt("chour"));
                c.setEnrolled_number(rs.getInt("enrolled_number"));
                c.setSelected(rs.getBoolean("selected"));

                courses.add(c);
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
        return courses;
    }

    @Override
    public ArrayList<Course> getAllCoursesByTno(String tno) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Course> courses = new ArrayList<>();
        try {
            con = getConnection();
            ps = con.prepareStatement(TC_SELECTALL_SQL);
            ps.setString(1,tno);
            rs = ps.executeQuery();
            while(rs.next()){
                Course c = new Course();
                c.setCno(rs.getString("cno"));
                c.setCname(rs.getString("cname"));
                c.setCtype(rs.getString("ctype"));
                c.setTarget(rs.getString("target"));
                c.setChour(rs.getInt("chour"));
                c.setEnrolled_number(rs.getInt("enrolled_number"));
                c.setSelected(rs.getBoolean("selected"));

                courses.add(c);
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
        return courses;
    }

    @Override
    public void addTeacherCourse(String tno, String cno) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = getConnection();
            ps = con.prepareStatement(TC_ADD_SQL);
            ps.setString(1,tno);
            ps.setString(2,cno);
            System.out.println("添加记录数："+ps.executeUpdate());
        }catch (Exception e){

        }finally {
            try {
                ps.close();
                con.close();
            }catch (Exception e){

            }
        }
    }
}
