package Entity;

public class ActivityDetail {
    private String actNo;
    private String actName;
    private String actAdd;
    private String actDate;
    private String Mno;

    public ActivityDetail(String actNo, String actName, String actAdd, String actDate, String mno) {
        this.actNo = actNo;
        this.actName = actName;
        this.actAdd = actAdd;
        this.actDate = actDate;
        this.Mno = mno;
    }

    @Override
    public String toString() {
        return "activityDetail{" +
                "actNo='" + actNo + '\'' +
                ", actName='" + actName + '\'' +
                ", actAdd='" + actAdd + '\'' +
                ", actDate='" + actDate + '\'' +
                ", Mno='" + Mno + '\'' +
                '}';
    }

    public ActivityDetail() {
    }

    public String getMno() {
        return Mno;
    }

    public void setMno(String mno) {
        Mno = mno;
    }

    public String getActNo() {
        return actNo;
    }

    public void setActNo(String actNo) {
        this.actNo = actNo;
    }

    public String getActName() {
        return actName;
    }

    public void setActName(String actName) {
        this.actName = actName;
    }

    public String getActAdd() {
        return actAdd;
    }

    public void setActAdd(String actAdd) {
        this.actAdd = actAdd;
    }

    public String getActDate() {
        return actDate;
    }

    public void setActDate(String actDate) {
        this.actDate = actDate;
    }

}
