package Entity;

import java.math.BigDecimal;
import java.sql.Date;

public class ProjectMentor {
    // project_mentor（Pno, Pname, Ptype, total_money, Mno） Mno是外键
    private String Pno;
    private String Pname;
    private String Ptype;
    private BigDecimal totalMoney;
    private String Mno;
    private Date startTime;
    private Date endTime;
    private String Lno;

    public String getPno() {
        return Pno;
    }

    public void setPno(String pno) {
        Pno = pno.trim(); // 设置的是char，如果这里不trim，会导致存进来的Pno后面带有空格
    }

    public String getPname() {
        return Pname;
    }

    public void setPname(String pname) {
        Pname = pname;
    }

    public String getPtype() {
        return Ptype;
    }

    public void setPtype(String ptype) {
        Ptype = ptype;
    }

    public BigDecimal getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(BigDecimal totalMoney) {
        this.totalMoney = totalMoney;
    }

    public String getMno() {
        return Mno;
    }

    public void setMno(String mno) {
        Mno = mno;
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

    public String getLno() {
        return Lno;
    }

    public void setLno(String lno) {
        Lno = lno;
    }

    @Override
    public String toString() {
        return "ProjectMentor{" +
                "Pno='" + Pno + '\'' +
                ", Pname='" + Pname + '\'' +
                ", Ptype='" + Ptype + '\'' +
                ", totalMoney=" + totalMoney +
                ", Mno='" + Mno + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", Lno='" + Lno + '\'' +
                '}';
    }

}
