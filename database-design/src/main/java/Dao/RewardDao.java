package Dao;

import Entity.Reward;

import java.util.List;

public interface RewardDao {
	void addReward(Reward reward);//添加奖励
    void updateReward(Reward reward);//更新奖励信息
    void deleteReward(Reward reward);//删除奖励信息
    List<Reward> findReward(String userid,int i);//按学生查找奖励
}
