package Dao;

import Entity.Reward;

import java.util.List;

public interface RewardDao {
	void addReward(Reward reward);//��ӽ���
    void updateReward(Reward reward);//���½�����Ϣ
    void deleteReward(Reward reward);//ɾ��������Ϣ
    List<Reward> findReward(String userid,int i);//��ѧ�����ҽ���
}
