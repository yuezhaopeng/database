package Dao.Impl;

import Dao.ReportDAO;
import Entity.Report;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReportDAOBase extends DaoBase implements ReportDAO {

    @Override
    public void addReport(Report report) {
        String sql = "INSERT INTO report VALUES(?,?,?,?,?,?,?,?,?,?) ";
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, report.getId());
            preparedStatement.setString(2, report.getName());
            preparedStatement.setString(3, report.getType());
            preparedStatement.setString(4, report.getAddress());
            preparedStatement.setString(5, report.getTime());
            preparedStatement.setInt(6, report.getContribution());
            preparedStatement.setString(7, report.getMaterial());
            preparedStatement.setString(8, report.getStatus());
            preparedStatement.setString(9, report.getSid());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteReport(Report report) {
        String STUDENT_INSERT_SQL = "delete from report where rno= ?";
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(STUDENT_INSERT_SQL);
            preparedStatement.setString(1, report.getId());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateReport(Report report) {
        String sql = "UPDATE report SET rname=?,rtype=?,raddress=?,rtime=?,rcontribution=?,rmaterial=?,status=? where rno=?";
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, report.getName());
            preparedStatement.setString(2, report.getType());
            preparedStatement.setInt(5, report.getContribution());
            preparedStatement.setString(3, report.getAddress());
            preparedStatement.setString(4, report.getTime());
            preparedStatement.setString(6, report.getMaterial());
            preparedStatement.setString(7, report.getStatus());
            preparedStatement.setString(8, report.getId());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Report> findReport(String id, int i) {
        ArrayList<Report> arrayList = new ArrayList<>();
        String STUDENT_INSERT_SQL ="";
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = null;
            switch (i){
                case 1: STUDENT_INSERT_SQL = "select * from report where sid=?";
                        preparedStatement = connection.prepareStatement(STUDENT_INSERT_SQL);
                        preparedStatement.setString(1,id);
                        break;
                case 2: STUDENT_INSERT_SQL = "select report.* from report,student where sid=Sno and Mno=?";
                        preparedStatement = connection.prepareStatement(STUDENT_INSERT_SQL);
                        preparedStatement.setString(1,id);
                        break;
                case 3: STUDENT_INSERT_SQL = "select report.* from report,student,mentor where sid=Sno and student.Mno=mentor.Mno and Lno=?";
                        preparedStatement = connection.prepareStatement(STUDENT_INSERT_SQL);
                        preparedStatement.setString(1,id);
                        break;
                case 4: STUDENT_INSERT_SQL = "select * from report";
                        preparedStatement = connection.prepareStatement(STUDENT_INSERT_SQL);
                        break;
            }
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Report report = new Report();
                report.setId(resultSet.getString("rno"));
                report.setName(resultSet.getString("rname"));
                report.setType(resultSet.getString("rtype"));
                report.setAddress(resultSet.getString("raddress"));
                report.setTime(resultSet.getString("rtime"));
                report.setContribution(resultSet.getInt("rcontribution"));
                report.setMaterial(resultSet.getString("rmaterial"));
                report.setStatus(resultSet.getString("status"));
                report.setSid(resultSet.getString("sid"));
                arrayList.add(report);
            }
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return arrayList;
    }
}
