package Dao;

import Entity.Thesis;

import java.util.List;

public interface ThesisDao {
	void addThesis(Thesis thesis);//添加论文
    void updateThesis(Thesis thesis);//更新论文信息
    void deleteThesis(Thesis thesis);//删除论文信息
    List<Thesis> findThesis(String userid,int i);//按学生查找论文,1是学生，2是学科负责人查本学科下的，3是导师查研究生
}
