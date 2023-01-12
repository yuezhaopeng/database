package Dao;

import Entity.Mentor;

import java.util.List;

public interface MentorDao {

    boolean addMentor(String Mno, String Mname,String Lno);

    List<Mentor> listMentor();

    List<Mentor> getAllMentors();
}
