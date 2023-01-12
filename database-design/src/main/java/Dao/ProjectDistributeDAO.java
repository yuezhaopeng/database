package Dao;



import Entity.ProjectDistribute;

import java.math.BigDecimal;
import java.util.List;

public interface ProjectDistributeDAO {
    List<ProjectDistribute> getAllProjectDistribute();
    List<ProjectDistribute> getProjectDistributeBySno(String Sno);
    List<ProjectDistribute> getProjectDistributeByPno(String Pno);
    void addProjectDistribute(ProjectDistribute projectDistribute);
    void mentorDistribute(ProjectDistribute projectDistribute); // 环节一、导师指定科研项目
    void studentComplete(String Sno, String Pno, String startTime, String endTime, String responsibility); // 环节二、学生完善认定表
    void mentorGiveMoneyAndSign(String Sno, String Pno, BigDecimal personalMoney); // 环节三、导师填报学生折合的经费数量并且签字
    void chiefSign(String Sno, String Pno); // 环节四、项目负责人签字
    void deleteProjectDistributeByPnoAndSno(String Pno, String Sno); // 导师删除已经分配的记录（为了数据库完整性）


}
