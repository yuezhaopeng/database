package Dao.Impl;

import Dao.ProjectDistributeDAO;
import Entity.ProjectDistribute;

import java.math.BigDecimal;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ProjectDistributeDAOImpl extends DaoBase implements ProjectDistributeDAO {
// project_distribute(Sno, Pno, start_time, end_time, responsibility, personal_money, mentor_agree, chief_agree)
    private static final String SQL_SELECT_PROJECT_DISTRIBUTE_ALL = "SELECT Sno, Pno, convert(varchar(20), start_time, 23) start_time, convert(varchar(20), end_time, 23) end_time, responsibility, personal_money, mentor_agree, chief_agree FROM project_distribute;";
    // 注意，这里查询的时候需要将start_time转换一下类型，否则查询出来的Date会比表中Date值少两天，这是由于sqlserver驱动的原因造成的
    // convert中的23代表yyyy-MM-dd这个日期格式
    /***
     * @return select所有ProjectDistribute
     */
    @Override
    public List<ProjectDistribute> getAllProjectDistribute() {
        Connection con = null;
        List<ProjectDistribute> list = new ArrayList<>();
        try{
            // 法一、使用prepareStatement
//            con = getConnection();
//            PreparedStatement psmt = con.prepareStatement(SQL_SELECT_STUDENT_ALL);
//            ResultSet rs = psmt.executeQuery();
            // 法二、使用Statement
            con = getConnection();
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(SQL_SELECT_PROJECT_DISTRIBUTE_ALL);
            while (rs.next()){
                ProjectDistribute projectDistribute = new ProjectDistribute();
                projectDistribute.setSno(rs.getString("Sno"));
                projectDistribute.setPno(rs.getString("Pno").trim());
                projectDistribute.setStartTime(rs.getDate("start_time"));
                projectDistribute.setEndTime(rs.getDate("end_time"));
                projectDistribute.setResponsibility(rs.getString("responsibility"));
                projectDistribute.setPersonalMoney(rs.getBigDecimal("personal_money"));
                projectDistribute.setMentorAgree(rs.getString("mentor_agree"));
                projectDistribute.setChiefAgree(rs.getString("chief_agree"));
                list.add(projectDistribute);

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

    private static final String SQL_SELECT_PROJECT_DISTRIBUTE_BY_SNO = "SELECT Sno, Pno, convert(varchar(20), start_time, 23) start_time, convert(varchar(20), end_time, 23) end_time, responsibility, personal_money, mentor_agree, chief_agree FROM project_distribute where Sno=?;";
    @Override
    public List<ProjectDistribute> getProjectDistributeBySno(String Sno) {
        Connection con = null;
        List<ProjectDistribute> list = new ArrayList<>();
        try{
            con = getConnection();
            PreparedStatement psmt = con.prepareStatement(SQL_SELECT_PROJECT_DISTRIBUTE_BY_SNO);
            psmt.setString(1, Sno);
            ResultSet rs = psmt.executeQuery();
            while (rs.next()){
                ProjectDistribute projectDistribute = new ProjectDistribute();
                projectDistribute.setSno(rs.getString("Sno"));
                projectDistribute.setPno(rs.getString("Pno").trim());
                projectDistribute.setStartTime(rs.getDate("start_time"));
                projectDistribute.setEndTime(rs.getDate("end_time"));
                projectDistribute.setResponsibility(rs.getString("responsibility"));
                projectDistribute.setPersonalMoney(rs.getBigDecimal("personal_money"));
                projectDistribute.setMentorAgree(rs.getString("mentor_agree"));
                projectDistribute.setChiefAgree(rs.getString("chief_agree"));
                list.add(projectDistribute);
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

    private static final String SQL_SELECT_PROJECT_DISTRIBUTE_BY_PNO = "SELECT Sno, Pno, convert(varchar(20), start_time, 23) start_time, convert(varchar(20), end_time, 23) end_time, responsibility, personal_money, mentor_agree, chief_agree FROM project_distribute where Pno=?;";
    @Override
    public List<ProjectDistribute> getProjectDistributeByPno(String Pno) {
        Connection con = null;
        List<ProjectDistribute> list = new ArrayList<>();
        try{
            con = getConnection();
            PreparedStatement psmt = con.prepareStatement(SQL_SELECT_PROJECT_DISTRIBUTE_BY_PNO);
            psmt.setString(1, Pno);
            ResultSet rs = psmt.executeQuery();
            while (rs.next()){
                ProjectDistribute projectDistribute = new ProjectDistribute();
                projectDistribute.setSno(rs.getString("Sno"));
                projectDistribute.setPno(rs.getString("Pno").trim());
                projectDistribute.setStartTime(rs.getDate("start_time"));
                projectDistribute.setEndTime(rs.getDate("end_time"));
                projectDistribute.setResponsibility(rs.getString("responsibility"));
                projectDistribute.setPersonalMoney(rs.getBigDecimal("personal_money"));
                projectDistribute.setMentorAgree(rs.getString("mentor_agree"));
                projectDistribute.setChiefAgree(rs.getString("chief_agree"));
                list.add(projectDistribute);
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

    private static final String SQL_INSERT_PROJECT_MENTOR = "INSERT INTO project_distribute VALUES(?,?,?,?,?,?,?,?);";

    /**
     * @param projectDistribute 添加一行记录
     */
    @Override
    public void addProjectDistribute(ProjectDistribute projectDistribute) {
        Connection con = null;
        try{
            con = getConnection();
            PreparedStatement psmt = con.prepareStatement(SQL_INSERT_PROJECT_MENTOR);
            psmt.setString(1, projectDistribute.getSno());
            psmt.setString(2, projectDistribute.getPno());
            psmt.setString(3, projectDistribute.getStartTime().toString());
            psmt.setString(4, projectDistribute.getEndTime().toString());
            psmt.setString(5, projectDistribute.getResponsibility());
            psmt.setBigDecimal(6, projectDistribute.getPersonalMoney());
            psmt.setString(7, projectDistribute.getMentorAgree());
            psmt.setString(8, projectDistribute.getChiefAgree());
            int result = psmt.executeUpdate();
            if (result > 0) {
                System.out.println("addProjectDistribute成功");
            } else {
                System.out.println("addProjectDistribute失败");
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

    private static final String SQL_MENTOR_DISTRIBUTE = "INSERT INTO project_distribute(Sno, Pno) VALUES(?,?);";
    /**
     *  第一个环节，教师指定科研项目
     *  project_distribute表中添加一行记录，可以只写Sno和Pno
     * @param projectDistribute
     */
    @Override
    public void mentorDistribute(ProjectDistribute projectDistribute) {
        Connection con = null;
        try{
            con = getConnection();
            PreparedStatement psmt = con.prepareStatement(SQL_MENTOR_DISTRIBUTE);
            psmt.setString(1, projectDistribute.getSno());
            psmt.setString(2, projectDistribute.getPno());
            int result = psmt.executeUpdate();
            if (result > 0) {
                System.out.println("addProjectDistribute成功");
            } else {
                System.out.println("addProjectDistribute失败");
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

    private static final String SQL_STUDENT_COMPLETE = "UPDATE project_distribute set start_time=?, end_time=?, responsibility=? where Sno=? and Pno=?;";
    @Override
    public void studentComplete(String Sno, String Pno, String startTime, String endTime, String responsibility) {
        Connection con = null;
        try{
            con = getConnection();
            PreparedStatement psmt = con.prepareStatement(SQL_STUDENT_COMPLETE);

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date utilStartTime = sdf.parse(startTime);
            java.util.Date utilEndTime = sdf.parse(endTime);
            psmt.setDate(1, new Date(utilStartTime.getTime()));
            psmt.setDate(2, new Date(utilEndTime.getTime()));

            psmt.setString(3, responsibility);
            psmt.setString(4, Sno);
            psmt.setString(5, Pno);

            int result = psmt.executeUpdate();
            if (result > 0) {
                System.out.println("studentComplete成功");
            } else {
                System.out.println("studentComplete失败");
            }
            psmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static final String SQL_MENTOR_GIVE_MONEY_SIGN = "UPDATE project_distribute set personal_money=?, mentor_agree=? where Sno=? and Pno=?;";
    @Override
    public void mentorGiveMoneyAndSign(String Sno, String Pno, BigDecimal personalMoney) {
        Connection con = null;
        try{
            con = getConnection();
            PreparedStatement psmt = con.prepareStatement(SQL_MENTOR_GIVE_MONEY_SIGN);

            psmt.setBigDecimal(1, personalMoney); // 在顶层封装当中要判断这个是否大于6.0000，如果不是，不能更新
            psmt.setString(2, "1");
            psmt.setString(3, Sno);
            psmt.setString(4, Pno);
            int result = psmt.executeUpdate();
            if (result > 0) {
                System.out.println("mentorGiveMoneyAndSign成功");
            } else {
                System.out.println("mentorGiveMoneyAndSign失败");
            }
            psmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static final String SQL_CHIEF_SIGN = "UPDATE project_distribute set chief_agree=? where Sno=? and Pno=?;";
    @Override
    public void chiefSign(String Sno, String Pno) {
        Connection con = null;
        try{
            con = getConnection();
            PreparedStatement psmt = con.prepareStatement(SQL_CHIEF_SIGN);

            psmt.setString(1, "1");
            psmt.setString(2, Sno);
            psmt.setString(3, Pno);
            int result = psmt.executeUpdate();
            if (result > 0) {
                System.out.println("mentorGiveMoneyAndSign成功");
            } else {
                System.out.println("mentorGiveMoneyAndSign失败");
            }
            psmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    private static final String SQL_DELETE_PROJECT_DISTRIBUTE = "DELETE from project_distribute where Pno=? and Sno=?";
    @Override
    public void deleteProjectDistributeByPnoAndSno(String Pno, String Sno) {
        Connection con = null;
        con = getConnection();
        PreparedStatement psmt;
        try {
            psmt = con.prepareStatement(SQL_DELETE_PROJECT_DISTRIBUTE);
            psmt.setString(1, Pno);
            psmt.setString(2, Sno);
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
