package Entity;

public class Student {
    private String Sno;
    private String Sname;
    private String Smajor;
    private String Mno;
    /**
     * 1.学硕，2.专硕，3，博士
     */
    private String Stype;

    public Student(String sno, String sname, String smajor, String mno, String stype) {
        Sno = sno;
        Sname = sname;
        Smajor = smajor;
        Mno = mno;
        Stype = stype;
    }

    public Student() {
    }

    @Override
    public String toString() {
        return "Student{" +
                "Sno='" + Sno + '\'' +
                ", Sname='" + Sname + '\'' +
                ", Smajor='" + Smajor + '\'' +
                ", Mno='" + Mno + '\'' +
                ", Stype='" + Stype + '\'' +
                '}';
    }

    public String getSno() {
        return Sno;
    }

    public void setSno(String sno) {
        Sno = sno;
    }

    public String getSname() {
        return Sname;
    }

    public void setSname(String sname) {
        Sname = sname;
    }

    public String getSmajor() {
        return Smajor;
    }

    public void setSmajor(String smajor) {
        Smajor = smajor;
    }

    public String getMno() {
        return Mno;
    }

    public void setMno(String mno) {
        Mno = mno;
    }
}
