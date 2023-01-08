package Dao;

import Entity.ActivityDetail;

import java.util.List;

public interface ActivityDetailDao {
    /**
     * 导师功能：插入对应的学术交流详细信息
     * @param activityDetail 活动信息
     */
    boolean insert(ActivityDetail activityDetail);
    /**
     * 导师功能：查看自己许可的学术交流详细信息
     * @param Mno 导师号
     * @return 活动列表
     */
    List<ActivityDetail> showForMen(String Mno);
    /**
     * 学生功能：查看所有自己可以报名的学术交流信息
     * @param Sno 学生号
     * @return 活动列表
     */
    List<ActivityDetail> showForStu(String Sno);

    /**
     * 管理员功能：查看所有的学术交流信息
     * @return 活动列表
     */
    List<ActivityDetail> showForAdmin();

    ActivityDetail showOne(String actNo);
}
