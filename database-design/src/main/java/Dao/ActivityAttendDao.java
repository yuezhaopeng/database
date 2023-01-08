package Dao;

import Entity.ActivityAttend;

import java.util.List;

public interface ActivityAttendDao {
    /**
     * 学生功能：申请学术交流记录
     */
    boolean insert(ActivityAttend activityAttend);

    /**
     * 学生功能：删除学术交流记录
     *
     * @param Sno   学号
     * @param actNo 活动号
     * @return 是否删除成功
     */
    boolean delete(String Sno, String actNo);

    /**
     * 学生功能：撤回申请记录
     *
     * @param Sno   学号
     * @param actNo 活动号
     */
    boolean withdraw(String Sno, String actNo);

    /**
     * 学生功能：修改记录并重新提交等待审核
     *
     * @param activityAttend 实体类
     */
    boolean reSubmit(ActivityAttend activityAttend);

    /**
     * 导师功能：导师进行审核
     *
     * @param Sno    学号
     * @param act_no 活动号
     * @param res    0表示等待审核，1表示审核完成，-1表示审核不通过
     */
    boolean audit(String Sno, String act_no, int res);

    /**
     * 学生功能：学生查看通过记录
     *
     * @param Sno 学号
     * @return 记录表
     */
    List<ActivityAttend> showForStuYes(String Sno);

    /**
     * 学生功能：学生查看申请失败记录
     *
     * @param Sno 学号
     * @return 记录表
     */
    List<ActivityAttend> showForStuNo(String Sno);

    /**
     * 导师功能：导师查看通过记录表
     *
     * @param Mno 导师号
     * @return 记录表
     */
    List<ActivityAttend> showForMenYes(String Mno);

    /**
     * 导师功能：导师查看需要审批的记录
     *
     * @param Mno 导师号
     * @return 记录表
     */
    List<ActivityAttend> showForMenAudit(String Mno);

    /**
     * 查询对应的一个
     *
     * @param Sno   学号
     * @param actNo 活动号
     * @return 对象
     */
    ActivityAttend showOne(String Sno, String actNo);

    /**
     * 两个活动是否存在
     * @param Sno 学号
     * @param a1 活动1
     * @param a2 活动2
     * @return 是否存在
     */
    boolean exist(String Sno, String a1, String a2);
}
