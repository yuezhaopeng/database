package Dao;

import Entity.AvailableAssistant;
import Entity.Student;
import Entity.vo.AssistantRemark;

import java.util.ArrayList;

public interface AvailableAssistantDao {
    void addAvailableAssistant(AvailableAssistant availableAssistant);//添加可选助教

    ArrayList<AssistantRemark> getAllApplyBySno(String sno);//根据学号查询申请

    ArrayList<AssistantRemark> getAllSelectedApplyBySno(String sno);//根据学号查询工作评价表

    void updateAvailableAssistant_student(String cno,String sno,String comment);//学生填写自述

    void updateAvailableAssistant_teacher(String cno,String sno,int status);//教师选择助教

    void updateTeacherComment(String cno,String sno,int result,String comment);//教师填写工作表

    ArrayList<Student> getAllAssistantByCno(String cno, int status);//查询课程号下的所有申请

    AssistantRemark getApplyByCno(String cno);//根据cno获取工作评价表（status=1）

}
