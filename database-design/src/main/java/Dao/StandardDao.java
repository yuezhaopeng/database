package Dao;

import Entity.Standard;

import java.util.List;

public interface StandardDao {
	void addStandard(Standard standard);//��ӱ�׼
    void updateStandard(Standard standard);//���±�׼
    void deleteStandard(Standard standard);//ɾ����׼
    List<Standard> findStandard(String userid,int i);//��ѯ��׼
}
