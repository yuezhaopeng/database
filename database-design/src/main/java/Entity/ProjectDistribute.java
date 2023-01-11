package Entity;

import java.math.BigDecimal;
import java.sql.Date;

public class ProjectDistribute {
    // project_distribute(Sno, Pno, start_time, end_time, responsibility, personal_money, mentorAgree, chiefAgree)
    private String Sno;
    private String Pno;
    private Date startTime;
    private Date endTime;
    private String responsibility;
    private BigDecimal personalMoney;
    private String mentorAgree;
    private String chiefAgree;

    public String getSno() {
        return Sno;
    }

    public void setSno(String sno) {
        Sno = sno;
    }

    public String getPno() {
        return Pno;
    }

    public void setPno(String pno) {
        Pno = pno;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getResponsibility() {
        return responsibility;
    }

    public void setResponsibility(String responsibility) {
        this.responsibility = responsibility;
    }

    public BigDecimal getPersonalMoney() {
        return personalMoney;
    }

    public void setPersonalMoney(BigDecimal personalMoney) {
        this.personalMoney = personalMoney;
    }

    public String getMentorAgree() {
        return mentorAgree;
    }

    public void setMentorAgree(String mentorAgree) {
        this.mentorAgree = mentorAgree;
    }

    public String getChiefAgree() {
        return chiefAgree;
    }

    public void setChiefAgree(String chiefAgree) {
        this.chiefAgree = chiefAgree;
    }

    @Override
    public String toString() {
        return "ProjectDistribute{" +
                "Sno='" + Sno + '\'' +
                ", Pno='" + Pno + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", responsibility='" + responsibility + '\'' +
                ", personalMoney=" + personalMoney +
                ", mentorAgree='" + mentorAgree + '\'' +
                ", chiefAgree='" + chiefAgree + '\'' +
                '}';
    }
}
