package Entity;

public class Prove {
    public String id;
    public String name;
    public String time;
    public String material;
    public int contribution;
    public String status;
    public String sid;
    public String address;

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    @Override
    public String toString() {
        return "֤��{" +
                ", ����='" + name + '\'' +
                ", ƽ̨����λ='" + address + '\'' +
                ", ƽ̨����ʱ��='" + time + '\'' +
                ", ��֤����='" + material + '\'' +
                ", ���׶�=" + contribution +
                ", ���״̬='" + status + '\'' +
                ", ѧ��='" + sid + '\'' +
                '}';
    }
}
