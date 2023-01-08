package Dao;

import Entity.LoginDetail;

public interface LoginDetailDAO {
    LoginDetail getLoginDetailByUsername(String username); // 根据username查询LoginDetail
    void addLoginDetail(String username, String role, String no); // 插入一条记录

    /**
     * 根据学号查询是否有对应的账号
     * @param Sno 学号
     * @return ture：有， false：无
     */
    boolean isExistStudent(String Sno);
}
