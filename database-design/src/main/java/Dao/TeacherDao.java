package Dao;

import Entity.Teacher;

public interface TeacherDao {

    void addTeacher(String Tno, String Tname);

    Teacher getByTno(String tno);

}
