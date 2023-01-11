package Entity.vo;


import Entity.Course;
import Entity.Teacher;

public class TeacherCourse {
    private Course course;
    private Teacher teacher;

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    @Override
    public String toString() {
        return "TeacherCourse{" +
                "course=" + course +
                ", teacher=" + teacher +
                '}';
    }
}
