package Dao.Impl;

import Dao.ThesisDao;
import Entity.Thesis;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ThesisDaoImpl extends DaoBase implements ThesisDao {
	private static final String INSERT_SQL = "INSERT INTO thesis(id,name,pubname,pubtime,pubstate,indextype,base,material,sid,state) VALUES(?,?,?,?,?,?,?,?,?,?) ";
    private static final String UPDATE_SQL="update thesis set name=?,pubname=?,pubtime=?,pubstate=?,indextype=?,base=?,material=?,state=? where id=?";
    private static final String DELETE_SQL="delete from thesis where id=?";
    private static final String QUERY_SQL1="select * from thesis where sid=?";
    private static final String QUERY_SQL2="select thesis.* from thesis,student,leader where student.Smajor=leader.subject and student.Sno=thesis.sid and leader.Lno=?";//ѧ�Ƹ�����
    private static final String QUERY_SQL3="select thesis.* from thesis,student,mentor where student.Mno=mentor.Mno and thesis.sid=student.Sno and mentor.Mno=?";//��ʦ
    private static final String QUERY_SQL4="select * from thesis";
    @Override
    public void addThesis(Thesis thesis) {
    	 Connection con = null;
	        try{
	            con = getConnection();
	            PreparedStatement psmt = con.prepareStatement(INSERT_SQL);
	            psmt.setString(1, thesis.getId());
	            psmt.setString(2, thesis.getName());
	            psmt.setString(3, thesis.getPubname());
	            psmt.setString(4, thesis.getPubtime());
	            psmt.setString(5, thesis.getPubstate());
	            psmt.setString(6, thesis.getIndextype());
	            psmt.setString(7, thesis.getBase());
	            psmt.setString(8, thesis.getMaterial());
	            psmt.setString(9, thesis.getSid());
	            psmt.setString(10, thesis.getState());
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
    public void updateThesis(Thesis thesis) {
    	Connection con = null;
        try{
            con = getConnection();
            PreparedStatement psmt = con.prepareStatement(UPDATE_SQL);
            psmt.setString(1, thesis.getName());
            psmt.setString(2, thesis.getPubname());
            psmt.setString(3, thesis.getPubtime());
            psmt.setString(4, thesis.getPubstate());
            psmt.setString(5, thesis.getIndextype());
            psmt.setString(6, thesis.getBase());
            psmt.setString(7, thesis.getMaterial());
            psmt.setString(8, thesis.getState());
            psmt.setString(9, thesis.getId());
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
    public void deleteThesis(Thesis thesis) {
    	Connection con = null;
        try{
            con = getConnection();
            PreparedStatement psmt = con.prepareStatement(DELETE_SQL);
            psmt.setString(1, thesis.getId());
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
    public List<Thesis> findThesis(String userid,int i){
    	Connection con = null;
    	List<Thesis> ls=new ArrayList<Thesis>();
        try{
            con = getConnection();
            PreparedStatement psmt=null;
            switch(i) {
            case 1:
            	psmt = con.prepareStatement(QUERY_SQL1);
            	psmt.setString(1, userid);
            	break;
            case 2:
            	psmt = con.prepareStatement(QUERY_SQL2);
            	psmt.setString(1, userid);
            	break;
            case 3:
            	psmt = con.prepareStatement(QUERY_SQL3);
            	psmt.setString(1, userid);
            	break;
            case 4:
            	psmt = con.prepareStatement(QUERY_SQL4);
            	break;
            }
            ResultSet rs = psmt.executeQuery();
            while (rs.next()){
            	Thesis s=new Thesis(rs.getString("id"),rs.getString("name"),rs.getString("pubname"),rs.getString("pubtime"),rs.getString("pubstate"),rs.getString("indextype"),rs.getString("base"),rs.getString("material"),rs.getString("sid"),rs.getString("state"));
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
