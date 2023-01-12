package Dao.Impl;

import Dao.StandardDao;
import Entity.Standard;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class StandardDaoImpl extends DaoBase implements StandardDao {
	private static final String INSERT_SQL = "INSERT INTO standard(id,name,getlevel,pubtime,material,sid,state) VALUES(?,?,?,?,?,?,?) ";
    private static final String UPDATE_SQL="update standard set name=?,getlevel=?,pubtime=?,material=?,state=? where id=?";
    private static final String DELETE_SQL="delete from standard where id=?";
    private static final String QUERY_SQL1="select * from standard where sid=?";
    private static final String QUERY_SQL2="select standard.* from standard,student,leader where student.Smajor=leader.subject and student.Sno=standard.sid and leader.Lno=?";//ѧ�Ƹ�����
    private static final String QUERY_SQL3="select standard.* from standard,student,mentor where student.Mno=mentor.Mno and standard.sid=student.Sno and mentor.Mno=?";//��ʦ
    private static final String QUERY_SQL4="select * from standard";
    @Override
	public void addStandard(Standard standard) {
		Connection con = null;
        try{
            con = getConnection();
            PreparedStatement psmt = con.prepareStatement(INSERT_SQL);
            psmt.setString(1, standard.getId());
            psmt.setString(2, standard.getName());
            psmt.setString(3, standard.getGetlevel());
            psmt.setString(4, standard.getPubtime());
            psmt.setString(5, standard.getMaterial());
            psmt.setString(6, standard.getSid());
            psmt.setString(7, standard.getState());
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
    public void updateStandard(Standard standard) {//���±�׼��Ϣ
		Connection con = null;
        try{
            con = getConnection();
            PreparedStatement psmt = con.prepareStatement(UPDATE_SQL);
            psmt.setString(1, standard.getName());
            psmt.setString(2, standard.getGetlevel());
            psmt.setString(3, standard.getPubtime());
            psmt.setString(4, standard.getMaterial());
            psmt.setString(5, standard.getState());
            psmt.setString(6, standard.getId());
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
    public void deleteStandard(Standard standard) {//ɾ����׼��Ϣ
		Connection con = null;
        try{
            con = getConnection();
            PreparedStatement psmt = con.prepareStatement(DELETE_SQL);
            psmt.setString(1, standard.getId());
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
    public List<Standard> findStandard(String userid,int i){//��ѧ�����ұ�׼
		Connection con = null;
    	List<Standard> ls=new ArrayList<Standard>();
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
            	Standard s=new Standard(rs.getString("id"),rs.getString("name"),rs.getString("getlevel"),rs.getString("pubtime"),rs.getString("material"),rs.getString("sid"),rs.getString("state"));
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
