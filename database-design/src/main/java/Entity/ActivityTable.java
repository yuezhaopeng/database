package Entity;

public class ActivityTable {
    private String Sno;
    private String actNoOne;
    private String actNoTwo;
    private String Maudit;
    private String Laudit;

    public ActivityTable() {
    }

    @Override
    public String toString() {
        return "activityTable{" +
                "Sno='" + Sno + '\'' +
                ", actNoOne='" + actNoOne + '\'' +
                ", actNoTwo='" + actNoTwo + '\'' +
                ", Maudit='" + Maudit + '\'' +
                ", Laudit='" + Laudit + '\'' +
                '}';
    }

    public ActivityTable(String sno, String actNoOne, String actNoTwo, String maudit, String laudit) {
        Sno = sno;
        this.actNoOne = actNoOne;
        this.actNoTwo = actNoTwo;
        Maudit = maudit;
        Laudit = laudit;
    }

    public String getSno() {
        return Sno;
    }

    public void setSno(String sno) {
        Sno = sno;
    }

    public String getActNoOne() {
        return actNoOne;
    }

    public void setActNoOne(String actNoOne) {
        this.actNoOne = actNoOne;
    }

    public String getActNoTwo() {
        return actNoTwo;
    }

    public void setActNoTwo(String actNoTwo) {
        this.actNoTwo = actNoTwo;
    }

    public String getMaudit() {
        return Maudit;
    }

    public void setMaudit(String maudit) {
        Maudit = maudit;
    }

    public String getLaudit() {
        return Laudit;
    }

    public void setLaudit(String laudit) {
        Laudit = laudit;
    }
}
