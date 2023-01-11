package Dao;

import Entity.Student;

import java.util.ArrayList;
import java.util.List;

public interface StudentDao {
    /**
     * 添加学生
     * @param Sno 学生号
     * @param Sname 学生姓名
     * @param Mno 导师号
     */
    boolean addStudent(String Sno, String Sname, String Smajor, String Mno,String Stype);

    List<Student> listStudent();

    Student getBySno(String sno);

    ArrayList<Student> getAll();
}
