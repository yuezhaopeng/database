package Dao;


import Entity.Manager;

public interface ManagerDao {
    Manager selectByMno(String mno);
}
