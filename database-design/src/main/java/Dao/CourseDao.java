package Dao;


import Entity.Course;
import Entity.vo.TeacherCourse;

import java.util.ArrayList;

public interface CourseDao {
    /*对应course表，是最原始的课程列表
    * 根据此表的课时数chour和学分数credit加权生成可供选择的课程表*/

    /*添加课程*/
    void addCourse(Course course);
    /*更新课程*/
    void updateCourse(String cno,boolean selected);
    /*删除课程*/
    void deleteCourse(String cno);
    /*查询所有未被选中的课程，按照chour和credit加权排序*/
    ArrayList<TeacherCourse> getAllCoursesNotSelected();
}
