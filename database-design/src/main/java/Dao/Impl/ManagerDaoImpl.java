package Dao.Impl;


import Dao.ManagerDao;
import Entity.Manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ManagerDaoImpl extends DaoBase implements ManagerDao {
    private static final String MANAGER_SELECTBYMNO_SQL = "select * from manger where mno=?";

    @Override
    public Manager selectByMno(String mno) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Manager manager = null;
        try {
            con = getConnection();
            ps = con.prepareStatement(MANAGER_SELECTBYMNO_SQL);
            ps.setString(1,mno);
            rs = ps.executeQuery();
            if(rs.next()){
                manager = new Manager();
                manager.setMno(rs.getString("mno"));
                manager.setMname(rs.getString("mname"));
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
        return manager;
    }
}
