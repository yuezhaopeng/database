package Entity;

public class ActivityAttend {
    private String Sno;
    private String actNo;
    private String actReportName;
    private String actPic;
    private String Maudit;
    private String Sps;
    private String Mps;

    public ActivityAttend() {
    }

    public ActivityAttend(String sno, String actNo, String actReportName, String actPic, String maudit, String sps, String mps) {
        Sno = sno;
        this.actNo = actNo;
        this.actReportName = actReportName;
        this.actPic = actPic;
        Maudit = maudit;
        Sps = sps;
        Mps = mps;
    }

    @Override
    public String toString() {
        return "activityAttend{" +
                "Sno='" + Sno + '\'' +
                ", actNo='" + actNo + '\'' +
                ", actReportName='" + actReportName + '\'' +
                ", actPic='" + actPic + '\'' +
                ", Maudit='" + Maudit + '\'' +
                ", Sps='" + Sps + '\'' +
                ", Mps='" + Mps + '\'' +
                '}';
    }

    public String getSno() {
        return Sno;
    }

    public void setSno(String sno) {
        Sno = sno;
    }

    public String getActNo() {
        return actNo;
    }

    public void setActNo(String actNo) {
        this.actNo = actNo;
    }

    public String getActReportName() {
        return actReportName;
    }

    public void setActReportName(String actReportName) {
        this.actReportName = actReportName;
    }

    public String getActPic() {
        return actPic;
    }

    public void setActPic(String actPic) {
        this.actPic = actPic;
    }

    public String getMaudit() {
        return Maudit;
    }

    public void setMaudit(String maudit) {
        Maudit = maudit;
    }

    public String getSps() {
        return Sps;
    }

    public void setSps(String sps) {
        Sps = sps;
    }

    public String getMps() {
        return Mps;
    }

    public void setMps(String mps) {
        Mps = mps;
    }

}
