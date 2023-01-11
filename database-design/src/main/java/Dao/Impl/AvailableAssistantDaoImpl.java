package Dao.Impl;

import Dao.AvailableAssistantDao;
import Entity.AvailableAssistant;
import Entity.Course;
import Entity.Student;
import Entity.Teacher;
import Entity.vo.AssistantRemark;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class AvailableAssistantDaoImpl extends DaoBase implements AvailableAssistantDao {

    private static final String ASSISTANT_INSERT_SQL = "insert into available_assistant (sno,cno,status,result) values(?,?,?,?)";
    private static final String ASSISTANT_SELECTBYSNO_SQL = "select available_assistant.*,course.cname from available_assistant,course where sno=? and available_assistant.cno=course.cno";
    private static final String ASSISTANT_SELECTSELECTEDBYSNO_SQL = "select available_assistant.*,course.*,teacher.* from available_assistant,course,teacher,teacher_course where sno=? and status=1 and available_assistant.cno=course.cno and course.cno=teacher_course.cno and teacher_course.tno=teacher.tno";
    private static final String ASSISTANT_STUDENTUPDATE_SQL = "update available_assistant set student_comment=?,student_comment_time=? where cno=? and sno=?";
    private static final String ASSISTANT_SELECTBYCNO_SQL = "select student.* from student,available_assistant where cno=? and status=? and available_assistant.sno=student.sno";
    private static final String ASSISTANT_TEACHERUPDATESTATUS_SQL = "update available_assistant set status=? where cno=? and sno=?";
    private static final String ASSISTANT_TEACHERUPDATECOMMENT_SQL = "update available_assistant set result=?,teacher_comment=?,teacher_comment_time=? where cno=? and sno=?";
    private static final String ASSISTANT_SELECTSELECTEDBYCNO_SQL = "select available_assistant.*,course.cname,course.ctype,course.target,course.chour,course.enrolled_number,student.sname,student.smajor from available_assistant,course,student where available_assistant.cno=? and status=1 and available_assistant.cno=course.cno and available_assistant.sno=student.sno";
    @Override
    public void addAvailableAssistant(AvailableAssistant availableAssistant) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = getConnection();
            ps = con.prepareStatement(ASSISTANT_INSERT_SQL);
            ps.setString(1,availableAssistant.getSno());
            ps.setString(2,availableAssistant.getCno());
            ps.setInt(3,availableAssistant.getStatus());
            ps.setInt(4,availableAssistant.getResult());
            System.out.println("添加记录数："+ps.executeUpdate());
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                rs.close();
                ps.close();
                con.close();
            }catch (Exception e){

            }
        }
    }

    @Override
    public void updateAvailableAssistant_student(String cno,String sno,String comment) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = getConnection();
            ps = con.prepareStatement(ASSISTANT_STUDENTUPDATE_SQL);
            ps.setString(1,comment);
            ps.setDate(2, new java.sql.Date(new java.util.Date().getTime()));
            ps.setString(3,cno);
            ps.setString(4,sno);
            System.out.println("更新记录数："+ps.executeUpdate());
        } catch (Exception e){

        } finally {
            try {
                ps.close();
                con.close();
            }catch (Exception e){

            }
        }
    }

    @Override
    public void updateAvailableAssistant_teacher(String cno, String sno, int status) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = getConnection();
            ps = con.prepareStatement(ASSISTANT_TEACHERUPDATESTATUS_SQL);
            ps.setInt(1,status);
            ps.setString(2,cno);
            ps.setString(3,sno);
            System.out.println("更新记录数："+ps.executeUpdate());
        } catch (Exception e){

        } finally {
            try {
                ps.close();
                con.close();
            }catch (Exception e){

            }
        }
    }

    @Override
    public void updateTeacherComment(String cno, String sno, int result, String comment) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = getConnection();
            ps = con.prepareStatement(ASSISTANT_TEACHERUPDATECOMMENT_SQL);
            ps.setInt(1,result);
            ps.setString(2,comment);
            ps.setDate(3,new java.sql.Date(new java.util.Date().getTime()));
            ps.setString(4,cno);
            ps.setString(5,sno);
            System.out.println("更新记录数："+ps.executeUpdate());
        } catch (Exception e){

        } finally {
            try {
                ps.close();
                con.close();
            }catch (Exception e){

            }
        }
    }

    @Override
    public ArrayList<Student> getAllAssistantByCno(String cno, int status) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Student> students = new ArrayList<>();
        try {
            con = getConnection();
            ps = con.prepareStatement(ASSISTANT_SELECTBYCNO_SQL);
            ps.setString(1,cno);
            ps.setInt(2,status);
            rs = ps.executeQuery();
            while(rs.next()){
                Student stu = new Student();
                stu.setSno(rs.getString("sno"));
                stu.setSname(rs.getString("sname"));
                stu.setSmajor(rs.getString("smajor"));

                students.add(stu);
            }
        } catch (Exception e){

        } finally {
            try {
                rs.close();
                ps.close();
                con.close();
            }catch (Exception e){

            }
        }
        return students;
    }

    @Override
    public ArrayList<AssistantRemark> getAllApplyBySno(String sno) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<AssistantRemark> assistantRemarks = new ArrayList<>();
        try {
            con = getConnection();
            ps = con.prepareStatement(ASSISTANT_SELECTBYSNO_SQL);
            ps.setString(1,sno);
            rs = ps.executeQuery();
            while(rs.next()){
                Course course = new Course();
                course.setCname(rs.getString("cname"));

                AvailableAssistant availableAssistant = new AvailableAssistant();
                availableAssistant.setCno(rs.getString("cno"));
                availableAssistant.setStatus(rs.getInt("status"));
                availableAssistant.setResult(rs.getInt("result"));

                AssistantRemark ar = new AssistantRemark();
                ar.setAvailableAssistant(availableAssistant);
                ar.setCourse(course);

                assistantRemarks.add(ar);
            }
        } catch (Exception e){

        } finally {
            try {
                rs.close();
                ps.close();
                con.close();
            }catch (Exception e){

            }
        }
        return assistantRemarks;
    }

    @Override
    public ArrayList<AssistantRemark> getAllSelectedApplyBySno(String sno) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<AssistantRemark> assistantRemarks = new ArrayList<>();
        try {
            con = getConnection();
            ps = con.prepareStatement(ASSISTANT_SELECTSELECTEDBYSNO_SQL);
            ps.setString(1,sno);
            rs = ps.executeQuery();
            while(rs.next()){
                AvailableAssistant availableAssistant = new AvailableAssistant();
                availableAssistant.setSno(sno);
                availableAssistant.setCno(rs.getString("cno"));
                availableAssistant.setStudent_comment(rs.getString("student_comment"));
                availableAssistant.setStudent_comment_time(rs.getDate("student_comment_time"));
                availableAssistant.setTeacher_comment(rs.getString("teacher_comment"));
                availableAssistant.setTeacher_comment_time(rs.getDate("teacher_comment_time"));
                availableAssistant.setResult(rs.getInt("result"));

                Course course = new Course();
                course.setCname(rs.getString("cname"));
                course.setCtype(rs.getString("ctype"));
                course.setTarget(rs.getString("target"));
                course.setChour(rs.getInt("chour"));
                course.setEnrolled_number(rs.getInt("enrolled_number"));

                Teacher teacher = new Teacher();
                teacher.setTno(rs.getString("tno"));
                teacher.setTname(rs.getString("tname"));

                AssistantRemark ar = new AssistantRemark();
                ar.setCourse(course);
                ar.setAvailableAssistant(availableAssistant);
                ar.setTeacher(teacher);

                assistantRemarks.add(ar);
            }
        } catch (Exception e){

        } finally {
            try {
                rs.close();
                ps.close();
                con.close();
            }catch (Exception e){

            }
        }
        return assistantRemarks;
    }

    @Override
    public AssistantRemark getApplyByCno(String cno) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        AssistantRemark assistantRemark = null;
        try {
            con = getConnection();
            ps = con.prepareStatement(ASSISTANT_SELECTSELECTEDBYCNO_SQL);
            ps.setString(1,cno);
            rs = ps.executeQuery();
            if(rs.next()){
                AvailableAssistant availableAssistant = new AvailableAssistant();
                availableAssistant.setSno(rs.getString("sno"));
                availableAssistant.setCno(rs.getString("cno"));
                availableAssistant.setStatus(1);
                availableAssistant.setStudent_comment(rs.getString("student_comment"));
                availableAssistant.setStudent_comment_time(rs.getDate("student_comment_time"));
                availableAssistant.setTeacher_comment(rs.getString("teacher_comment"));
                availableAssistant.setTeacher_comment_time(rs.getDate("teacher_comment_time"));
                availableAssistant.setResult(rs.getInt("result"));

                Course course = new Course();
                course.setCno(cno);
                course.setCname(rs.getString("cname"));
                course.setCtype(rs.getString("ctype"));
                course.setTarget(rs.getString("target"));
                course.setChour(rs.getInt("chour"));
                course.setEnrolled_number(rs.getInt("enrolled_number"));

                Student student = new Student();
                student.setSno(rs.getString("sno"));
                student.setSname(rs.getString("sname"));
                student.setSmajor(rs.getString("smajor"));

                assistantRemark = new AssistantRemark();
                assistantRemark.setCourse(course);
                assistantRemark.setAvailableAssistant(availableAssistant);
                assistantRemark.setStudent(student);
            }
        } catch (Exception e){

        } finally {
            try {
                rs.close();
                ps.close();
                con.close();
            }catch (Exception e){

            }
        }
        return assistantRemark;
    }

}
