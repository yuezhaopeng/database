package Dao.Impl;

import Dao.ActivityTableDao;
import Entity.ActivityTable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ActivityTableDaoImpl extends DaoBase implements ActivityTableDao {
    @Override
    public boolean insert(ActivityTable activityTable) {
        boolean check = false;
        Connection con = null;
        try {
            con = getConnection();
            String sql = "INSERT INTO activity_table VALUES (?,?,?,?,?)";
            PreparedStatement psmt = con.prepareStatement(sql);
            psmt.setString(1, activityTable.getSno());
            psmt.setString(2, activityTable.getActNoOne());
            psmt.setString(3, activityTable.getActNoTwo());
            psmt.setString(4, activityTable.getMaudit());
            psmt.setString(5, activityTable.getLaudit());
            int i = psmt.executeUpdate();
            if (i > 0) {
                check = true;
            }
            psmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return check;
    }

    @Override
    public boolean delete(String Sno) {
        boolean check = false;
        Connection con = null;
        try {
            con = getConnection();
            String sql = "delete from activity_table where Sno = ?";
            PreparedStatement psmt = con.prepareStatement(sql);
            psmt.setString(1, Sno);
            int i = psmt.executeUpdate();
            if (i > 0) {
                check = true;
            }
            psmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return check;
    }

    @Override
    public boolean Maudit(String Sno, int res) {
        boolean check = false;
        Connection con = null;
        try {
            con = getConnection();
            String sql = "update activity_table set Maudit = ? where Sno = ?";
            PreparedStatement psmt = con.prepareStatement(sql);
            psmt.setString(1, String.valueOf(res));
            psmt.setString(2, Sno);
            int i = psmt.executeUpdate();
            if (i > 0) {
                check = true;
            }
            psmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return check;
    }

    @Override
    public boolean Laudit(String Sno, int res) {
        boolean check = false;
        Connection con = null;
        try {
            con = getConnection();
            String sql = "update activity_table set Laudit = ? where Sno = ?";
            PreparedStatement psmt = con.prepareStatement(sql);
            psmt.setString(1, String.valueOf(res));
            psmt.setString(2, Sno);
            int i = psmt.executeUpdate();
            if (i > 0) {
                check = true;
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
        return check;
    }

    @Override
    public List<HashMap<String,Object>> showForM(String Mno) {
        Connection con = null;
        ArrayList<HashMap<String,Object>> list = new ArrayList<>();
        try {
            con = getConnection();
            String sql = "select Sno,Sname,Smajor,act_no_one,act_no_two,Maudit,Laudit " +
                    "from table_view where Mno = ?";
            PreparedStatement psmt = con.prepareStatement(sql);
            psmt.setString(1, Mno);
            ResultSet rs = psmt.executeQuery();
            while (rs.next()) {
                ActivityTable a = new ActivityTable(rs.getString(1), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7));
                HashMap map=new HashMap<>();
                map.put("a",a);
                map.put("name",rs.getString(2));
                map.put("major",rs.getString(3));
                list.add(map);
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
        return list;
    }

    @Override
    public List<HashMap<String,Object>> showForL(String Lno) {
        Connection con = null;
        ArrayList<HashMap<String,Object>> list = new ArrayList<>();
        try {
            con = getConnection();
            String sql = "select Sno,Sname,Smajor,act_no_one,act_no_two,Maudit,Laudit " +
                    "from table_view where Lno = ?";
            PreparedStatement psmt = con.prepareStatement(sql);
            psmt.setString(1, Lno);
            ResultSet rs = psmt.executeQuery();
            while (rs.next()) {
                ActivityTable a = new ActivityTable(rs.getString(1), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7));
                HashMap map=new HashMap<>();
                map.put("a",a);
                map.put("name",rs.getString(2));
                map.put("major",rs.getString(3));
                list.add(map);
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
        return list;
    }

    @Override
    public List<HashMap<String,Object>> showForAdmin() {
        Connection con = null;
        ArrayList<HashMap<String,Object>> list = new ArrayList<>();
        try {
            con = getConnection();
            String sql = "select Sno,Sname,Smajor,act_no_one,act_no_two,Maudit,Laudit " +
                    "from table_view where Maudit = 1 and Laudit = 1";
            PreparedStatement psmt = con.prepareStatement(sql);
            ResultSet rs = psmt.executeQuery();
            while (rs.next()) {
                ActivityTable a = new ActivityTable(rs.getString(1), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7));
                HashMap map=new HashMap<>();
                map.put("a",a);
                map.put("name",rs.getString(2));
                map.put("major",rs.getString(3));
                list.add(map);
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
        return list;
    }
}
