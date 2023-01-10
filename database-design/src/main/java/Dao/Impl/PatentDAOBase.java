package Dao.Impl;

import Dao.PatentDAO;
import Entity.Patent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PatentDAOBase extends DaoBase implements PatentDAO {
    @Override
    public void addPatent(Patent patent) {
        String sql = "INSERT INTO patent VALUES(?,?,?,?,?,?,?,?,?) ";
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, patent.getId());
            preparedStatement.setString(2, patent.getName());
            preparedStatement.setString(3, patent.getType());
            preparedStatement.setString(4, patent.getTime());
            preparedStatement.setString(5, patent.getStatus());
            preparedStatement.setInt(6, patent.getContribution());
            preparedStatement.setString(7, patent.getMaterial());
            preparedStatement.setString(8, patent.getTstatus());
            preparedStatement.setString(9, patent.getSid());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deletePatent(Patent patent) {                           //学生撤回申请
        String STUDENT_INSERT_SQL = "delete from patent where pid= ?";
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(STUDENT_INSERT_SQL);
            preparedStatement.setString(1, patent.getId());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updatePatent(Patent patent) {
        String sql = "UPDATE patent SET pname=?,ptype=?,ptime=?,pstatus=?,pcontribution=?,pmaterial=?,status=? where pid=?";
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, patent.getName());
            preparedStatement.setString(2, patent.getType());
            preparedStatement.setString(3, patent.getTime());
            preparedStatement.setString(4, patent.getStatus());
            preparedStatement.setInt(5, patent.getContribution());
            preparedStatement.setString(6, patent.getMaterial());
            preparedStatement.setString(7, patent.getTstatus());
            preparedStatement.setString(8, patent.getId());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Patent> findPatent(String id,int i) {
        ArrayList<Patent> arrayList = new ArrayList<>();
        String STUDENT_INSERT_SQL ="";
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = null;
            switch (i){
                case 1: STUDENT_INSERT_SQL = "select * from patent where sid=?";
                        preparedStatement = connection.prepareStatement(STUDENT_INSERT_SQL);
                        preparedStatement.setString(1,id);
                        break;
                case 2: STUDENT_INSERT_SQL = "select patent.* from patent,student where sid=Sno and Mno=?";
                        preparedStatement = connection.prepareStatement(STUDENT_INSERT_SQL);
                        preparedStatement.setString(1,id);
                        break;
                case 3: STUDENT_INSERT_SQL = "select patent.* from patent,student,mentor where sid=Sno and student.Mno=mentor.Mno and Lno=?";
                        preparedStatement = connection.prepareStatement(STUDENT_INSERT_SQL);
                        preparedStatement.setString(1,id);
                        break;
                case 4: STUDENT_INSERT_SQL = "select * from patent";
                        preparedStatement = connection.prepareStatement(STUDENT_INSERT_SQL);
                        break;
            }
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Patent patent = new Patent();
                patent.setId(resultSet.getString("pid"));
                patent.setName(resultSet.getString("pname"));
                patent.setType(resultSet.getString("ptype"));
                patent.setTime(resultSet.getString("ptime"));
                patent.setStatus(resultSet.getString("pstatus"));
                patent.setContribution(resultSet.getInt("pcontribution"));
                patent.setMaterial(resultSet.getString("pmaterial"));
                patent.setTstatus(resultSet.getString("status"));
                patent.setSid(resultSet.getString("sid"));
                arrayList.add(patent);
            }
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return arrayList;
    }

}
