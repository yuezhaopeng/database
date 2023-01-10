package Dao;

import Entity.Patent;

import java.util.List;

public interface PatentDAO {
    void addPatent(Patent patent);                      //学生提交专利认证
    void deletePatent(Patent patent);                   //学生撤销申请
    void updatePatent(Patent patent);                   //修改申请
    List<Patent> findPatent(String id,int i);           //1是学生查看，2是导师查看所有，3学科负责人查看所有

}
