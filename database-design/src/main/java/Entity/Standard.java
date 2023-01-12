package Entity;

public class Standard {
	private String id;
	private String name;
	private String getlevel;
	private String pubtime;
	private String material;
	private String sid;
	private String state;
	public Standard() {
		
	}
	public Standard(String id,String name,String getlevel,String pubtime,String material,String sid,String state) {
		this.id=id;
		this.getlevel=getlevel;
		this.material=material;
		this.name=name;
		this.pubtime=pubtime;
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
	public String getGetlevel() {
		return getlevel;
	}
	public void setGetlevel(String getlevel) {
		this.getlevel = getlevel;
	}
	public String getPubtime() {
		return pubtime;
	}
	public void setPubtime(String pubtime) {
		this.pubtime = pubtime;
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
		return name+"\t"+getlevel+"\t"+pubtime+"\t"+material+"\t"+sid+"\t"+status;
	}
}
