package Entity.vo;


import Entity.AvailableAssistant;
import Entity.Course;
import Entity.Student;
import Entity.Teacher;

/*学生工作评定表封装类型*/
public class AssistantRemark {
    private Student student;
    private Course course;
    private Teacher teacher;
    private AvailableAssistant availableAssistant;

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public AvailableAssistant getAvailableAssistant() {
        return availableAssistant;
    }

    public void setAvailableAssistant(AvailableAssistant availableAssistant) {
        this.availableAssistant = availableAssistant;
    }

    @Override
    public String toString() {
        return "AssistantRemark{" +
                "student=" + student +
                ", course=" + course +
                ", teacher=" + teacher +
                ", availableAssistant=" + availableAssistant +
                '}';
    }
}
