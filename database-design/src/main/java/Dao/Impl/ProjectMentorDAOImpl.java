package Dao.Impl;


import Dao.ProjectMentorDAO;
import Entity.ProjectMentor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProjectMentorDAOImpl extends DaoBase implements ProjectMentorDAO {
    // project_mentor（Pno, Pname, Ptype, total_money, Mno） Mno是外键

    private static final String SQL_SELECT_PROJECT_MENTOR_ALL = "SELECT * FROM project_mentor;";

    /***
     * @return select所有ProjectMentors
     */
    @Override
    public List<ProjectMentor> getAllProjectMentors() {
        Connection con = null;
        List<ProjectMentor> list = new ArrayList<>();
        try{
            // 法一、使用prepareStatement
//            con = getConnection();
//            PreparedStatement psmt = con.prepareStatement(SQL_SELECT_STUDENT_ALL);
//            ResultSet rs = psmt.executeQuery();
            // 法二、使用Statement
            con = getConnection();
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(SQL_SELECT_PROJECT_MENTOR_ALL);
            while (rs.next()){
                ProjectMentor projectMentor = new ProjectMentor();
                projectMentor.setPno(rs.getString("Pno"));
                projectMentor.setPname(rs.getString("Pname"));
                projectMentor.setPtype(rs.getString("Ptype"));
                projectMentor.setTotalMoney(rs.getBigDecimal("total_money"));
                projectMentor.setMno(rs.getString("Mno"));
                projectMentor.setStartTime(rs.getDate("start_time"));
                projectMentor.setEndTime(rs.getDate("end_time"));
                projectMentor.setLno(rs.getString("Lno"));
                list.add(projectMentor);
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

    private static final String SQL_SELECT_PROJECT_MENTOR_BY_MNO = "SELECT * FROM project_mentor where Mno=?;";
    @Override
    public List<ProjectMentor> getProjectMentorsByMno(String Mno) {
        Connection con = null;
        List<ProjectMentor> list = new ArrayList<>();
        try{
            con = getConnection();
            PreparedStatement psmt = con.prepareStatement(SQL_SELECT_PROJECT_MENTOR_BY_MNO);
            psmt.setString(1, Mno);
            ResultSet rs = psmt.executeQuery();
            while (rs.next()){
                ProjectMentor projectMentor = new ProjectMentor();
                projectMentor.setPno(rs.getString("Pno"));
                projectMentor.setPname(rs.getString("Pname"));
                projectMentor.setPtype(rs.getString("Ptype"));
                projectMentor.setTotalMoney(rs.getBigDecimal("total_money"));
                projectMentor.setMno(rs.getString("Mno"));
                projectMentor.setStartTime(rs.getDate("start_time"));
                projectMentor.setEndTime(rs.getDate("end_time"));
                projectMentor.setLno(rs.getString("Lno"));
                list.add(projectMentor);
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
        return list;
    }

    private static final String SQL_SELECT_PROJECT_MENTOR_BY_LNO = "SELECT * FROM project_mentor where Lno=?;";
    @Override
    public List<ProjectMentor> getProjectMentorsByLno(String Lno) {
        Connection con = null;
        List<ProjectMentor> list = new ArrayList<>();
        try{
            con = getConnection();
            PreparedStatement psmt = con.prepareStatement(SQL_SELECT_PROJECT_MENTOR_BY_LNO);
            psmt.setString(1, Lno);
            ResultSet rs = psmt.executeQuery();
            while (rs.next()){
                ProjectMentor projectMentor = new ProjectMentor();
                projectMentor.setPno(rs.getString("Pno"));
                projectMentor.setPname(rs.getString("Pname"));
                projectMentor.setPtype(rs.getString("Ptype"));
                projectMentor.setTotalMoney(rs.getBigDecimal("total_money"));
                projectMentor.setMno(rs.getString("Mno"));
                projectMentor.setStartTime(rs.getDate("start_time"));
                projectMentor.setEndTime(rs.getDate("end_time"));
                projectMentor.setLno(rs.getString("Lno"));
                list.add(projectMentor);
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
        return list;
    }

    private static final String SQL_INSERT_PROJECT_MENTOR = "INSERT INTO project_mentor VALUES(?,?,?,?,?,?,?,?);";
    // project_mentor（Pno, Pname, Ptype, total_money, Mno, start_time, end_time） Mno是外键

    @Override
    public void addProjectMentor(ProjectMentor projectMentor) {
        Connection con = null;
        try{
            con = getConnection();
            PreparedStatement psmt = con.prepareStatement(SQL_INSERT_PROJECT_MENTOR);
            psmt.setString(1, projectMentor.getPno());
            psmt.setString(2, projectMentor.getPname());
            psmt.setString(3, projectMentor.getPtype());
            psmt.setBigDecimal(4, projectMentor.getTotalMoney());
            psmt.setString(5, projectMentor.getMno());

            psmt.setDate(6, projectMentor.getStartTime());
            psmt.setDate(7, projectMentor.getEndTime());

            psmt.setString(8, projectMentor.getLno());

            int result = psmt.executeUpdate();
            if (result > 0) {
                System.out.println("addProjectMentor成功");
            } else {
                System.out.println("addProjectMentor失败");
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


    private static final String SQL_DELETE_PROJECT_MENTOR = "DELETE from project_mentor where Pno=?";
    @Override
    public void deleteProjectMentorByPno(String Pno) {
        Connection con = null;
        con = getConnection();
        PreparedStatement psmt;
        try {
            psmt = con.prepareStatement(SQL_DELETE_PROJECT_MENTOR);
            psmt.setString(1, Pno);
            psmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try{
                con.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }





}
