package Dao;

import Entity.Course;

import java.util.ArrayList;

public interface TeacherCourseDao {
    ArrayList<Course> getCoursesByTno(String tno, boolean selected);

    ArrayList<Course> getAllCoursesByTno(String tno);

    void addTeacherCourse(String tno,String cno);
}
