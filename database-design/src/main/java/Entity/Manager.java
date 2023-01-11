package Entity;

public class Manager {
    private String mno;
    private String mname;

    public String getMno() {
        return mno;
    }

    public void setMno(String mno) {
        this.mno = mno;
    }

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    @Override
    public String toString() {
        return "Manager{" +
                "mno='" + mno + '\'' +
                ", mname='" + mname + '\'' +
                '}';
    }
}
