package Dao;

import Entity.Prove;

import java.util.List;

public interface ProveDAO {
    void addProve(Prove prove);                      //学生提交专利认证
    void deleteProve(Prove prove);                   //学生撤销申请
    void updateProve(Prove prove);                   //修改申请
    List<Prove> findProve(String id, int i);           //1是学生查看，2是导师查看所有，3学科负责人查看所有
}
