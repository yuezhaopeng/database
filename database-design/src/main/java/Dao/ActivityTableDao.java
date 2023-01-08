package Dao;

import Entity.ActivityTable;

import java.util.HashMap;
import java.util.List;

public interface ActivityTableDao {
    /**
     * 学生功能：插入
     * @param activityTable 实体
     */
    boolean insert(ActivityTable activityTable);

    /**
     * 学生功能：删除本人的申请表
     * @param Sno 序号
     * @return 是否成功
     */
    boolean delete(String Sno);
    /**
     * 导师功能：导师审核
     * @param Sno 学生学号
     */
    boolean Maudit(String Sno,int res);

    /**
     * 学科负责人功能：学科负责人审核
     * @param Sno 学生学号
     */
    boolean Laudit(String Sno,int res);

    /**
     * 导师功能：查看导师下学生统计表
     * @param Mno 导师号
     */
    List<HashMap<String,Object>> showForM(String Mno);

    /**
     * 学科负责人功能：查看学科下统计表
     * @param Lno 学科负责人号
     */
    List<HashMap<String,Object>> showForL(String Lno);

    /**
     * 管理员功能：查看所有已通过审核的申请表
     * @return 列表
     */
    List<HashMap<String,Object>> showForAdmin();
}
