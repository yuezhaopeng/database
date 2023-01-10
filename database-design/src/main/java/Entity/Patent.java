package Entity;

public class Patent {
    public String id;
    public String name;
    public String type;
    public String time;
    public String status;
    public String material;
    public int contribution;
    public String tstatus;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getTstatus() {
        return tstatus;
    }

    public void setTstatus(String tstatus) {
        this.tstatus = tstatus;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    @Override
    public String toString() {
        return "ר��{" +
                "ר����='" + id + '\'' +
                ", ר������='" + name + '\'' +
                ", ר������='" + type + '\'' +
                ", ר������ʱ��='" + time + '\'' +
                ", ��֤����='" + status + '\'' +
                ", ר��״̬='" + material + '\'' +
                ", ���׶�����=" + contribution +
                ", ���״̬='" + tstatus + '\'' +
                ", ѧ��='" + sid + '\'' +
                '}';
    }
}
