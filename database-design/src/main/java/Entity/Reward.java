package Entity;

public class Reward {
	private String id;
	private String name;
	private String relevel;
	private String getlevel;
	private String rank;
	private String gettime;
	private String material;
	private String sid;
	private String state;
	public Reward() {
		
	}
	public Reward(String id,String name,String relevel,String getlevel,String rank,String gettime,String material,String sid,String state) {
		this.getlevel=getlevel;
		this.gettime=gettime;
		this.id=id;
		this.material=material;
		this.name=name;
		this.rank=rank;
		this.relevel=relevel;
		this.state=state;
		this.sid=sid;
	}
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
	public String getRelevel() {
		return relevel;
	}
	public void setRelevel(String relevel) {
		this.relevel = relevel;
	}
	public String getGetlevel() {
		return getlevel;
	}
	public void setGetlevel(String getlevel) {
		this.getlevel = getlevel;
	}
	public String getRank() {
		return rank;
	}
	public void setRank(String rank) {
		this.rank = rank;
	}
	public String getGettime() {
		return gettime;
	}
	public void setGettime(String gettime) {
		this.gettime = gettime;
	}
	public String getMaterial() {
		return material;
	}
	public void setMaterial(String material) {
		this.material = material;
	}
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	@Override
	public String toString() {
		String status="";
		if(state.contentEquals("0")) {
			status="未审核";
		}else if(state.contentEquals("1")) {
			status="初审结果：同意";
		}else if(state.contentEquals("2")) {
			status="终审结果：同意";
		}else if(state.contentEquals("3")) {
			status="初审结果：拒绝";
		}else if(state.contentEquals("4")) {
			status="终审结果：拒绝";
		}
		return name+"\t"+relevel+"\t"+getlevel+"\t"+rank+"\t"+gettime+"\t"+material+"\t"+sid+"\t"+status;
	}
}
