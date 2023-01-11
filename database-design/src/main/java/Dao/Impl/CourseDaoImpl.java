package Dao.Impl;



import Dao.CourseDao;
import Entity.Course;
import Entity.Teacher;
import Entity.vo.TeacherCourse;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class CourseDaoImpl extends DaoBase implements CourseDao {
    private static final String COURSE_INSERT_SQL = "insert into course (cno,cname,ctype,target,chour,enrolled_number,selected) values(?,?,?,?,?,?,?)";
    private static final String COURSE_UPDATE_SQL = "update course set selected=? where cno=?";
    private static final String COURSE_DELETE_SQL = "delete from course where cno=?";
    private static final String COURSE_SELECTNOTSELECTED_SQL = "select course.*,teacher.* from course,teacher_course,teacher where selected=0 and course.cno=teacher_course.cno and teacher_course.tno=teacher.tno order by chour desc,enrolled_number desc";

    @Override
    public void addCourse(Course course) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = getConnection();
            ps = con.prepareStatement(COURSE_INSERT_SQL);
            ps.setString(1,course.getCno());
            ps.setString(2,course.getCname());
            ps.setString(3,course.getCtype());
            ps.setString(4,course.getTarget());
            ps.setInt(5,course.getChour());
            ps.setInt(6,course.getEnrolled_number());
            ps.setBoolean(7,course.isSelected());
            System.out.println("添加记录数："+ps.executeUpdate());
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            try {
                ps.close();
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void updateCourse(String cno,boolean selected) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = getConnection();
            ps = con.prepareStatement(COURSE_UPDATE_SQL);
            ps.setBoolean(1,selected);
            ps.setString(2,cno);
            System.out.println("更新记录数："+ps.executeUpdate());
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            try {
                ps.close();
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void deleteCourse(String cno) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = getConnection();
            ps = con.prepareStatement(COURSE_DELETE_SQL);
            ps.setString(1,cno);
            System.out.println("删除记录数："+ps.executeUpdate());
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            try {
                ps.close();
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public ArrayList<TeacherCourse> getAllCoursesNotSelected() {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<TeacherCourse> teacherCourses = new ArrayList<>();
        try {
            con = getConnection();
            ps = con.prepareStatement(COURSE_SELECTNOTSELECTED_SQL);
            rs = ps.executeQuery();
            while(rs.next()){
                Course course = new Course();
                course.setCno(rs.getString("cno"));
                course.setCname(rs.getString("cname"));
                course.setCtype(rs.getString("ctype"));
                course.setTarget(rs.getString("target"));
                course.setChour(rs.getInt("chour"));
                course.setEnrolled_number(rs.getInt("enrolled_number"));
                course.setSelected(rs.getBoolean("selected"));

                Teacher teacher = new Teacher();
                teacher.setTno(rs.getString("tno"));
                teacher.setTname(rs.getString("tname"));

                TeacherCourse tc = new TeacherCourse();
                tc.setCourse(course);
                tc.setTeacher(teacher);

                teacherCourses.add(tc);
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            try {
                rs.close();
                ps.close();
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return teacherCourses;
    }
}
