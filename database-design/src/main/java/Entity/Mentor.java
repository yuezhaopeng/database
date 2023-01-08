package Entity;

public class Mentor {
    private String Mno;
    private String Mname;
    private String Lno;

    public Mentor() {
    }

    public Mentor(String mno, String mname, String lno) {
        Mno = mno;
        Mname = mname;
        Lno = lno;
    }

    public String getMno() {
        return Mno;
    }

    public void setMno(String mno) {
        Mno = mno;
    }

    public String getMname() {
        return Mname;
    }

    public void setMname(String mname) {
        Mname = mname;
    }

    public String getLno() {
        return Lno;
    }

    public void setLno(String lno) {
        Lno = lno;
    }

    @Override
    public String toString() {
        return "Mentor{" +
                "Mno='" + Mno + '\'' +
                ", Mname='" + Mname + '\'' +
                ", Lno='" + Lno + '\'' +
                '}';
    }
}
