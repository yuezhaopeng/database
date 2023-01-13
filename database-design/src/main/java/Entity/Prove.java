package Entity;

public class Prove {
    public String id;
    public String name;     //名称
    public String time;     //平台上线时间
    public String material; //佐证材料
    public int contribution;    //贡献度
    public String status;       //审核状态
    public String sid;          //学号
    public String address;      //平台服务单位

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
        return "证明{" +
                "名称='" + name + '\'' +
                ", 平台服务单位='" + address + '\'' +
                ", 平台上线时间='" + time + '\'' +
                ", 佐证材料='" + material + '\'' +
                ", 贡献度=" + contribution +
                ", 审核状态='" + status + '\'' +
                ", 学号='" + sid + '\'' +
                '}';
    }
}
