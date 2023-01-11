package Entity;

import java.util.Date;

public class AvailableAssistant {
    /*课程号*/
    private String cno;
    /*学号*/
    private String sno;
    /*是否入选，0未审核，1选中，2未选中*/
    private int status;
    /*学生工作自述*/
    private String student_comment;
    /*教师评语*/
    private String teacher_comment;
    /*学生评价时间*/
    private Date student_comment_time;
    /*教师评价时间*/
    private Date teacher_comment_time;
    /*是否通过，0未审核，1合格，2不合格*/
    private int result;

    public String getCno() {
        return cno;
    }

    public void setCno(String cno) {
        this.cno = cno;
    }

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getStudent_comment() {
        return student_comment;
    }

    public void setStudent_comment(String student_comment) {
        this.student_comment = student_comment;
    }

    public String getTeacher_comment() {
        return teacher_comment;
    }

    public void setTeacher_comment(String teacher_comment) {
        this.teacher_comment = teacher_comment;
    }

    public Date getStudent_comment_time() {
        return student_comment_time;
    }

    public void setStudent_comment_time(Date student_comment_time) {
        this.student_comment_time = student_comment_time;
    }

    public Date getTeacher_comment_time() {
        return teacher_comment_time;
    }

    public void setTeacher_comment_time(Date teacher_comment_time) {
        this.teacher_comment_time = teacher_comment_time;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "AvailableAssistant{" +
                "cno='" + cno + '\'' +
                ", sno='" + sno + '\'' +
                ", status=" + status +
                ", student_comment='" + student_comment + '\'' +
                ", teacher_comment='" + teacher_comment + '\'' +
                ", student_comment_time=" + student_comment_time +
                ", teacher_comment_time=" + teacher_comment_time +
                ", result=" + result +
                '}';
    }
}
