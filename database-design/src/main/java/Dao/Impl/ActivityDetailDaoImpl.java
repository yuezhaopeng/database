package Dao.Impl;

import Dao.ActivityDetailDao;
import Entity.ActivityDetail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class ActivityDetailDaoImpl extends DaoBase implements ActivityDetailDao {
    @Override
    public boolean insert(ActivityDetail activityDetail) {
        boolean check = false;
        Connection con = null;
        try {
            con = getConnection();
            String sql = "insert into activity_detail values(?,?,?,?,?)";
            PreparedStatement psmt = con.prepareStatement(sql);

            psmt.setString(1, activityDetail.getActNo());
            psmt.setString(2, activityDetail.getActName());
            psmt.setString(3, activityDetail.getActAdd());
            psmt.setString(4, activityDetail.getActDate());
            psmt.setString(5, activityDetail.getMno());

            int i = psmt.executeUpdate();
            if (i > 0) {
                check = true;
            }
            psmt.close();
        } catch (Exception e) {
            System.out.println("插入失败，可能存在相同活动序号");
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
    public List<ActivityDetail> showForMen(String Mno) {
        Connection con = null;
        ArrayList<ActivityDetail> list = new ArrayList<>();
        try {
            con = getConnection();
            String sql = "select * from activity_detail where Mno = ?";
            PreparedStatement psmt = con.prepareStatement(sql);
            psmt.setString(1, Mno);
            ResultSet rs = psmt.executeQuery();
            while (rs.next()) {
                ActivityDetail a = new ActivityDetail(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
                list.add(a);
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
        return list;
    }

    @Override
    public List<ActivityDetail> showForStu(String Sno) {
        Connection con = null;
        ArrayList<ActivityDetail> list = new ArrayList<>();
        try {
            con = getConnection();
            String sql = "select * from activity_detail_stu where Sno = ?";
            PreparedStatement psmt = con.prepareStatement(sql);
            psmt.setString(1, Sno);
            ResultSet rs = psmt.executeQuery();
            while (rs.next()) {
                ActivityDetail a = new ActivityDetail(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
                list.add(a);
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
    public List<ActivityDetail> showForAdmin() {
        Connection con = null;
        ArrayList<ActivityDetail> list = new ArrayList<>();
        try {
            con = getConnection();
            String sql = "select * from activity_detail";
            PreparedStatement psmt = con.prepareStatement(sql);
            ResultSet rs = psmt.executeQuery();
            while (rs.next()) {
                ActivityDetail a = new ActivityDetail(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
                list.add(a);
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
    public ActivityDetail showOne(String actNo) {
        Connection con = null;
        ActivityDetail list = null;
        try {
            con = getConnection();
            String sql = "select * from activity_detail where act_no = ?";
            PreparedStatement psmt = con.prepareStatement(sql);
            psmt.setString(1,actNo);
            ResultSet rs = psmt.executeQuery();
            while (rs.next()) {
                list = new ActivityDetail(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
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
