package Dao.Impl;

import Dao.ProveDAO;
import Entity.Prove;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProveDAOBase extends DaoBase implements ProveDAO {

    @Override
    public void addProve(Prove prove) {
        String sql = "INSERT INTO prove VALUES(?,?,?,?,?,?,?,?) ";
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, prove.getId());
            preparedStatement.setString(2, prove.getName());
            preparedStatement.setString(3, prove.getTime());
            preparedStatement.setInt(4, prove.getContribution());
            preparedStatement.setString(5, prove.getMaterial());
            preparedStatement.setString(6, prove.getStatus());
            preparedStatement.setString(7, prove.getSid());
            preparedStatement.setString(8, prove.getAddress());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteProve(Prove prove) {
        String STUDENT_INSERT_SQL = "delete from prove where pno= ?";
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(STUDENT_INSERT_SQL);
            preparedStatement.setString(1, prove.getId());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateProve(Prove prove) {
        String sql = "UPDATE prove SET pname=?,ptime=?,pcontribution=?,pmaterial=?,status=?,address=? where pno=?";
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, prove.getName());
            preparedStatement.setString(2, prove.getTime());
            preparedStatement.setInt(3, prove.getContribution());
            preparedStatement.setString(4, prove.getMaterial());
            preparedStatement.setString(5, prove.getStatus());
            preparedStatement.setString(6, prove.getAddress());
            preparedStatement.setString(7, prove.getId());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Prove> findProve(String id, int i) {
        ArrayList<Prove> arrayList = new ArrayList<>();
        String STUDENT_INSERT_SQL ="";
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = null;
            preparedStatement = connection.prepareStatement(STUDENT_INSERT_SQL);
            preparedStatement.setString(1,id);
            switch (i){
                case 1: STUDENT_INSERT_SQL = "select * from prove where sid=?";
                        preparedStatement = connection.prepareStatement(STUDENT_INSERT_SQL);
                        preparedStatement.setString(1,id);
                        break;
                case 2: STUDENT_INSERT_SQL = "select prove.* from prove,student where sid=Sno and Mno=?";
                        preparedStatement = connection.prepareStatement(STUDENT_INSERT_SQL);
                        preparedStatement.setString(1,id);
                        break;
                case 3: STUDENT_INSERT_SQL = "select prove.* from prove,student,mentor where sid=Sno and student.Mno=mentor.Mno and Lno=?";
                        preparedStatement = connection.prepareStatement(STUDENT_INSERT_SQL);
                        preparedStatement.setString(1,id);
                        break;
                case 4: STUDENT_INSERT_SQL = "select * from prove";
                        preparedStatement = connection.prepareStatement(STUDENT_INSERT_SQL);
                        break;
            }
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Prove prove = new Prove();
                prove.setId(resultSet.getString("pno"));
                prove.setName(resultSet.getString("pname"));
                prove.setTime(resultSet.getString("ptime"));
                prove.setStatus(resultSet.getString("status"));
                prove.setContribution(resultSet.getInt("pcontribution"));
                prove.setMaterial(resultSet.getString("pmaterial"));
                prove.setSid(resultSet.getString("sid"));
                prove.setAddress(resultSet.getString("address"));
                arrayList.add(prove);
            }
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return arrayList;
    }
}
