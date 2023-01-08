package Dao.Impl;

import Dao.LeaderDAO;
import Entity.Leader;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class LeaderDaoImpl extends DaoBase implements LeaderDAO {

    private static final String SQL_INSERT_LEADER = "INSERT INTO leader(Lno,Lname, subject) values(?,?,?)";
    private static final String SQL_SELECT_LEADER = "SELECT * FROM leader";
    @Override
    public void addLeader(String Lno, String Lname, String subject) {
        Connection con = null;
        try{
            con = getConnection();
            PreparedStatement psmt = con.prepareStatement(SQL_INSERT_LEADER);
            psmt.setString(1, Lno);
            psmt.setString(2, Lname);
            psmt.setString(3, subject);

            int result = psmt.executeUpdate();
            if (result > 0) {
                System.out.println("addLeader成功");
            } else {
                System.out.println("addLeader失败");
            }
            psmt.close();
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            try{
                if(con!=null){
                    con.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<Leader> listLeader() {
        Connection con = null;
        ArrayList<Leader> list= new ArrayList();
        try{
            con = getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(SQL_SELECT_LEADER);
            while(rs.next()){
                Leader leader=new Leader(rs.getString(1),rs.getString(2));
                list.add(leader);
            }
            st.close();
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            try{
                if(con!=null){
                    con.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return list;
    }
}
