package Dao.Impl;

import Dao.ActivityAttendDao;
import Entity.ActivityAttend;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ActivityAttendDaoImpl extends DaoBase implements ActivityAttendDao {
    @Override
    public boolean insert(ActivityAttend activityAttend) {
        boolean check = false;
        Connection con = null;
        try {
            con = getConnection();
            String sql = "insert into activity_attend values(?,?,?,?,?,?,?)";
            PreparedStatement psmt = con.prepareStatement(sql);
            psmt.setString(1, activityAttend.getSno());
            psmt.setString(2, activityAttend.getActNo());
            psmt.setString(3, activityAttend.getActReportName());
            psmt.setString(4, activityAttend.getActPic());
            psmt.setString(5, activityAttend.getMaudit());
            psmt.setString(6, activityAttend.getSps());
            psmt.setString(7, activityAttend.getMps());
            int i = psmt.executeUpdate();
            if (i > 0) {
                check = true;
            }
            psmt.close();
        } catch (Exception e) {
            System.out.println("对于同一个活动只能申请一次/申请序号有误");
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
    public boolean delete(String Sno, String actNo) {
        boolean check = false;
        Connection con = null;
        try {
            con = getConnection();
            String sql = "delete from activity_attend where Sno = ? and act_no = ?";
            PreparedStatement psmt = con.prepareStatement(sql);
            psmt.setString(1, Sno);
            psmt.setString(2, actNo);
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
    public boolean withdraw(String Sno, String actNo) {
        boolean check = false;
        Connection con = null;
        try {
            con = getConnection();
            String sql = "update activity_attend set Maudit = -2 where Sno = ? and act_no = ?";
            PreparedStatement psmt = con.prepareStatement(sql);
            psmt.setString(1, Sno);
            psmt.setString(2, actNo);
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
    public boolean reSubmit(ActivityAttend activityAttend) {
        boolean check = false;
        Connection con = null;
        try {
            con = getConnection();
            String sql = "update activity_attend " +
                    "set act_report_name=?,act_pic=?,Sps=?,Maudit=0 " +
                    "where Sno = ? and act_no = ?";
            PreparedStatement psmt = con.prepareStatement(sql);

            psmt.setString(4, activityAttend.getSno());
            psmt.setString(5, activityAttend.getActNo());
            psmt.setString(1, activityAttend.getActReportName());
            psmt.setString(2, activityAttend.getActPic());
            psmt.setString(3, activityAttend.getSps());

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
    public boolean audit(String Sno, String act_no, int res) {
        boolean check = false;
        Connection con = null;
        try {
            con = getConnection();
            String sql = "update activity_attend " +
                    "set Maudit=? " +
                    "where Sno = ? and act_no = ?";
            PreparedStatement psmt = con.prepareStatement(sql);
            psmt.setString(1, String.valueOf(res));
            psmt.setString(2, Sno);
            psmt.setString(3, act_no);
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
    public List<ActivityAttend> showForStuNo(String Sno) {
        Connection con = null;
        ArrayList<ActivityAttend> list = new ArrayList<>();
        try {
            con = getConnection();
            String sql = "select * from activity_attend where Sno = ? and Maudit != ? ";
            PreparedStatement psmt = con.prepareStatement(sql);
            psmt.setString(1, Sno);
            psmt.setString(2, "1");
            ResultSet rs = psmt.executeQuery();
            while (rs.next()) {
                ActivityAttend a = new ActivityAttend(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7));
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
    public List<ActivityAttend> showForStuYes(String Sno) {
        Connection con = null;
        ArrayList<ActivityAttend> list = new ArrayList<>();
        try {
            con = getConnection();
            String sql = "select * from activity_attend where Sno = ? and Maudit = ?";
            PreparedStatement psmt = con.prepareStatement(sql);
            psmt.setString(1, Sno);
            psmt.setString(2, "1");
            ResultSet rs = psmt.executeQuery();
            while (rs.next()) {
                ActivityAttend a = new ActivityAttend(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7));
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
    public List<ActivityAttend> showForMenAudit(String Mno) {
        Connection con = null;
        ArrayList<ActivityAttend> list = new ArrayList<>();
        try {
            con = getConnection();
            String sql = "select * from activity_attend_men where Mno = ? and Maudit = 0";
            PreparedStatement psmt = con.prepareStatement(sql);
            psmt.setString(1, Mno);
            ResultSet rs = psmt.executeQuery();
            while (rs.next()) {
                ActivityAttend a = new ActivityAttend(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7));
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
    public ActivityAttend showOne(String Sno, String actNo) {
        Connection con = null;
        ActivityAttend list =null;
        try {
            con = getConnection();
            String sql = "select * from activity_attend where Sno = ? and act_no = ?";
            PreparedStatement psmt = con.prepareStatement(sql);
            psmt.setString(1, Sno);
            psmt.setString(2,actNo);
            ResultSet rs = psmt.executeQuery();
            while (rs.next()) {
                list = new ActivityAttend(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7));
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
    public boolean exist(String Sno, String a1, String a2) {
        Connection con = null;
        boolean check =false;
        try {
            con = getConnection();
            String sql = "select count(*) from activity_attend where Sno = ? and (act_no = ? or act_no = ?) and Maudit = 1";
            PreparedStatement psmt = con.prepareStatement(sql);
            psmt.setString(1, Sno);
            psmt.setString(2,a1);
            psmt.setString(3,a2);
            ResultSet rs = psmt.executeQuery();
            while (rs.next()) {
                check=rs.getInt(1)==2?true:false;
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
    public List<ActivityAttend> showForMenYes(String Mno) {
        Connection con = null;
        ArrayList<ActivityAttend> list = new ArrayList<>();
        try {
            con = getConnection();
            String sql = "select * from activity_attend_men where Mno = ? and Maudit = 1";
            PreparedStatement psmt = con.prepareStatement(sql);
            psmt.setString(1, Mno);
            ResultSet rs = psmt.executeQuery();
            while (rs.next()) {
                ActivityAttend a = new ActivityAttend(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7));
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
}
