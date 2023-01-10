package Dao;

import Entity.Textbook;

import java.util.List;

public interface TextbookDAO {
    void addTextbook(Textbook textbook);                      //学生提交专利认证
    void deleteTextbook(Textbook textbook);                   //学生撤销申请
    void updateTextbook(Textbook textbook);                   //修改申请
    List<Textbook> findTextbook(String id, int i);           //1是学生查看，2是导师查看所有，3学科负责人查看所有
}
