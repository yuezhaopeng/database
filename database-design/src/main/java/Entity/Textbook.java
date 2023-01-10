package Entity;

public class Textbook {
    public String id;
    public String name;
    public String press;
    public String time;
    public String material;
    public int contribution;
    public String status;
    public String sid;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPress() {
        return press;
    }

    public void setPress(String press) {
        this.press = press;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public int getContribution() {
        return contribution;
    }

    public void setContribution(int contribution) {
        this.contribution = contribution;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }


    @Override
    public String toString() {
        return "�̲�{" +
                "�̲�����='" + name + '\'' +
                ", �̲ĳ�����='" + press + '\'' +
                ", �̲ĳ���ʱ��='" + time + '\'' +
                ", ��֤����='" + material + '\'' +
                ", ���׶�=" + contribution +
                ", ���״̬='" + status + '\'' +
                ", ѧ��='" + sid + '\'' +
                '}';
    }
}
