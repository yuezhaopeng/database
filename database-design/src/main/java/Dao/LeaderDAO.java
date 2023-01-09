package Dao;

import Entity.Leader;

import java.util.List;

public interface LeaderDAO {

    void addLeader(String Lno, String Lname, String subject);

    List<Leader> listLeader();
}
