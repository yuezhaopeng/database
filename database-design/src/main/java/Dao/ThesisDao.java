package Dao;

import Entity.Thesis;

import java.util.List;

public interface ThesisDao {
	void addThesis(Thesis thesis);//�������
    void updateThesis(Thesis thesis);//����������Ϣ
    void deleteThesis(Thesis thesis);//ɾ��������Ϣ
    List<Thesis> findThesis(String userid,int i);//��ѧ����������,1��ѧ����2��ѧ�Ƹ����˲鱾ѧ���µģ�3�ǵ�ʦ���о���
}
