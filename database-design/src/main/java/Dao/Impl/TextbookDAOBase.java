package Dao.Impl;

import Dao.TextbookDAO;
import Entity.Textbook;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TextbookDAOBase extends DaoBase implements TextbookDAO {
    @Override
    public void addTextbook(Textbook textbook) {
        String sql = "INSERT INTO textbook VALUES(?,?,?,?,?,?,?,?) ";
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, textbook.getId());
            preparedStatement.setString(2, textbook.getName());
            preparedStatement.setString(3, textbook.getTime());
            preparedStatement.setInt(4, textbook.getContribution());
            preparedStatement.setString(5, textbook.getMaterial());
            preparedStatement.setString(6, textbook.getStatus());
            preparedStatement.setString(7, textbook.getSid());
            preparedStatement.setString(8, textbook.getPress());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteTextbook(Textbook textbook) {
        String STUDENT_INSERT_SQL = "delete from textbook where tno= ?";
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(STUDENT_INSERT_SQL);
            preparedStatement.setString(1, textbook.getId());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateTextbook(Textbook textbook) {
        String sql = "UPDATE textbook SET tname=?,ttime=?,tcontribution=?,tmaterial=?,status=?,press=? where tno=?";
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, textbook.getName());
            preparedStatement.setString(2, textbook.getTime());
            preparedStatement.setInt(3, textbook.getContribution());
            preparedStatement.setString(4, textbook.getMaterial());
            preparedStatement.setString(5, textbook.getStatus());
            preparedStatement.setString(7, textbook.getId());
            preparedStatement.setString(6, textbook.getPress());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Textbook> findTextbook(String id, int i) {
        ArrayList<Textbook> arrayList = new ArrayList<>();
        String STUDENT_INSERT_SQL ="";
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = null;
            switch (i){
                case 1: STUDENT_INSERT_SQL = "select * from textbook where tno=?";
                        preparedStatement = connection.prepareStatement(STUDENT_INSERT_SQL);
                        preparedStatement.setString(1,id);
                        break;
                case 2: STUDENT_INSERT_SQL = "select textbook.* from textbook,student where sid=Sno and Mno=?";
                        preparedStatement = connection.prepareStatement(STUDENT_INSERT_SQL);
                        preparedStatement.setString(1,id);
                        break;
                case 3: STUDENT_INSERT_SQL = "select textbook.* from textbook,student,mentor where sid=Sno and student.Mno=mentor.Mno and Lno=?";
                        preparedStatement = connection.prepareStatement(STUDENT_INSERT_SQL);
                        preparedStatement.setString(1,id);
                        break;
                case 4: STUDENT_INSERT_SQL = "select * from textbook";
                        preparedStatement = connection.prepareStatement(STUDENT_INSERT_SQL);
            }
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Textbook textbook = new Textbook();
                textbook.setId(resultSet.getString("tno"));
                textbook.setName(resultSet.getString("tname"));
                textbook.setTime(resultSet.getString("ttime"));
                textbook.setContribution(resultSet.getInt("tcontribution"));
                textbook.setMaterial(resultSet.getString ("tmaterial"));
                textbook.setStatus(resultSet.getString("status"));
                textbook.setSid(resultSet.getString("sid"));
                textbook.setPress(resultSet.getString("press"));
                arrayList.add(textbook);
            }
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return arrayList;
    }
}
