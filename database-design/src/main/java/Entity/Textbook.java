package Entity;

public class Textbook {
    public String id;
    public String name;     //教材名称
    public String press;    //教材出版社
    public String time;     //教材出版时间
    public String material; //佐证材料
    public int contribution;    //贡献度
    public String status;       //审核状态
    public String sid;      //学号

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
        return "教材{" +
                "教材名称='" + name + '\'' +
                ", 教材出版社='" + press + '\'' +
                ", 教材出版时间='" + time + '\'' +
                ", 佐证材料='" + material + '\'' +
                ", 贡献度=" + contribution +
                ", 审核状态='" + status + '\'' +
                ", 学号='" + sid + '\'' +
                '}';
    }
}
