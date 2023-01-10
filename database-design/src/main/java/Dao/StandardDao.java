package Dao;

import Entity.Standard;

import java.util.List;

public interface StandardDao {
	void addStandard(Standard standard);//添加标准
    void updateStandard(Standard standard);//更新标准
    void deleteStandard(Standard standard);//删除标准
    List<Standard> findStandard(String userid,int i);//查询标准
}
