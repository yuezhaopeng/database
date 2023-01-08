package Entity;

public class Leader {
    private String Lno;
    private String Lname;

    public String getLno() {
        return Lno;
    }

    public void setLno(String lno) {
        Lno = lno;
    }

    public String getLname() {
        return Lname;
    }

    public void setLname(String lname) {
        Lname = lname;
    }

    public Leader(String lno, String lname) {
        Lno = lno;
        Lname = lname;
    }

    public Leader() {
    }

    @Override
    public String toString() {
        return "Leader{" +
                "Lno='" + Lno + '\'' +
                ", Lname='" + Lname + '\'' +
                '}';
    }
}
