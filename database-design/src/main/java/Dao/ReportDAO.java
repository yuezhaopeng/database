package Dao;

import Entity.Report;

import java.util.List;

public interface ReportDAO {
    void addReport(Report report);                      //学生提交专利认证
    void deleteReport(Report report);                   //学生撤销申请
    void updateReport(Report report);                   //修改申请
    List<Report> findReport(String id, int i);           //1是学生查看，2是导师查看所有，3学科负责人查看所有
}
