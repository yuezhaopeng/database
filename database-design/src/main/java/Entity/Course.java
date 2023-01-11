package Entity;

public class Course {
    private String cno;
    private String cname;
    private String ctype;
    private String target;
    private int chour;
    private int enrolled_number;
    private boolean selected;

    public String getCno() {
        return cno;
    }

    public void setCno(String cno) {
        this.cno = cno;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getCtype() {
        return ctype;
    }

    public void setCtype(String ctype) {
        this.ctype = ctype;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public int getChour() {
        return chour;
    }

    public void setChour(int chour) {
        this.chour = chour;
    }

    public int getEnrolled_number() {
        return enrolled_number;
    }

    public void setEnrolled_number(int enrolled_number) {
        this.enrolled_number = enrolled_number;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    @Override
    public String toString() {
        return "Course{" +
                "cno='" + cno + '\'' +
                ", cname='" + cname + '\'' +
                ", ctype='" + ctype + '\'' +
                ", target='" + target + '\'' +
                ", chour=" + chour +
                ", enrolled_number=" + enrolled_number +
                ", selected=" + selected +
                '}';
    }
}
