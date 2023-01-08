package Dao;

import Entity.LoginCheck;

public interface LoginCheckDAO {

    LoginCheck getLoginCheckByUsername(String username);
    void addLoginCheck(String username, String password); // 在用户名密码的表中插入记录

}
