package Dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Dao.RewardDao;
import Entity.Reward;

public class RewardDaoImpl extends DaoBase implements RewardDao {
	private static final String INSERT_SQL = "INSERT INTO reward(id,name,relevel,getlevel,rank,gettime,material,sid,state) VALUES(?,?,?,?,?,?,?,?,?) ";
    private static final String UPDATE_SQL="update reward set name=?,relevel=?,getlevel=?,rank=?,gettime=?,material=?,state=? where id=?";
    private static final String DELETE_SQL="delete from reward where id=?";
    private static final String QUERY_SQL1="select * from reward where sid=?";
    private static final String QUERY_SQL2="select reward.* from reward,student,leader where student.Smajor=leader.subject and student.Sno=reward.sid and leader.Lno=?";//ѧ�Ƹ�����
    private static final String QUERY_SQL3="select reward.* from reward,student,mentor where student.Mno=mentor.Mno and reward.sid=student.Sno and mentor.Mno=?";//��ʦ
    private static final String QUERY_SQL4="select * from reward";
    @Override
	public void addReward(Reward reward) {//��ӽ���
		Connection con = null;
        try{
            con = getConnection();
            PreparedStatement psmt = con.prepareStatement(INSERT_SQL);
            psmt.setString(1, reward.getId());
            psmt.setString(2, reward.getName());
            psmt.setString(3, reward.getRelevel());
            psmt.setString(4, reward.getGetlevel());
            psmt.setString(5, reward.getRank());
            psmt.setString(6, reward.getGettime());
            psmt.setString(7, reward.getMaterial());
            psmt.setString(8, reward.getSid());
            psmt.setString(9, reward.getState());
            psmt.executeUpdate();
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
	@Override
    public void updateReward(Reward reward) {//���½�����Ϣ
		Connection con = null;
        try{
            con = getConnection();
            PreparedStatement psmt = con.prepareStatement(UPDATE_SQL);
            psmt.setString(1, reward.getName());
            psmt.setString(2, reward.getRelevel());
            psmt.setString(3, reward.getGetlevel());
            psmt.setString(4, reward.getRank());
            psmt.setString(5, reward.getGettime());
            psmt.setString(6, reward.getMaterial());
            psmt.setString(7, reward.getState());
            psmt.setString(8, reward.getId());
            psmt.executeUpdate();
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
	@Override
    public void deleteReward(Reward reward) {//ɾ��������Ϣ
		Connection con = null;
        try{
            con = getConnection();
            PreparedStatement psmt = con.prepareStatement(DELETE_SQL);
            psmt.setString(1, reward.getId());
            psmt.executeUpdate();
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
	@Override
    public List<Reward> findReward(String userid,int i){//��ѧ�����ҽ���
		Connection con = null;
    	List<Reward> ls=new ArrayList<Reward>();
        try{
            con = getConnection();
            PreparedStatement psmt =null;
            switch(i) {
            case 1:
            	psmt=con.prepareStatement(QUERY_SQL1);
            	psmt.setString(1, userid);
            	break;
            case 2:
            	psmt=con.prepareStatement(QUERY_SQL2);
            	psmt.setString(1, userid);
            	break;
            case 3:
            	psmt=con.prepareStatement(QUERY_SQL3);
            	psmt.setString(1, userid);
            	break;
            case 4:
            	psmt=con.prepareStatement(QUERY_SQL4);
            	break;
            }
            ResultSet rs = psmt.executeQuery();
            while (rs.next()){
            	Reward s=new Reward(rs.getString("id"),rs.getString("name"),rs.getString("relevel"),rs.getString("getlevel"),rs.getString("rank"),rs.getString("gettime"),rs.getString("material"),rs.getString("sid"),rs.getString("state"));
            	ls.add(s);
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
        return ls;
    }
}
