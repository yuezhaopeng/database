package Dao.Impl;

import Dao.ProjectMentorDAO;
import Entity.ProjectMentor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
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

    private static final String SQL_INSERT_PROJECT_MENTOR = "INSERT INTO project_mentor VALUES(?,?,?,?,?);";
    // project_mentor（Pno, Pname, Ptype, total_money, Mno） Mno是外键

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





}
