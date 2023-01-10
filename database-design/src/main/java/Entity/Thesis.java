package Entity;

public class Thesis {
	private String id;
	private String name;
	private String pubname;
	private String pubtime;
	private String pubstate;
	private String indextype;
	private String base;
	private String material;
	private String sid;
	private String state;
	public Thesis(String id,String name,String pubname,String pubtime,String pubstate,String indextype,String base,String material,String sid,String state) {
		this.id=id;
		this.name=name;
		this.pubname=pubname;
		this.pubtime=pubtime;
		this.pubstate=pubstate;
		this.indextype=indextype;
		this.base=base;
		this.material=material;
		this.sid=sid;
		this.state=state;
	}
	public Thesis() {
		
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
	public String getPubname() {
		return pubname;
	}
	public void setPubname(String pubname) {
		this.pubname = pubname;
	}
	public String getPubtime() {
		return pubtime;
	}
	public void setPubtime(String pubtime) {
		this.pubtime = pubtime;
	}
	public String getIndextype() {
		return indextype;
	}
	public void setIndextype(String indextype) {
		this.indextype = indextype;
	}
	public String getBase() {
		return base;
	}
	public void setBase(String base) {
		this.base = base;
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
	public String getPubstate() {
		return pubstate;
	}
	public void setPubstate(String pubstate) {
		this.pubstate = pubstate;
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
		return name+"\t"+pubname+"\t"+pubtime+"\t"+pubstate+"\t"+indextype+"\t"+base+"\t"+material+"\t"+sid+"\t"+status;
	}
}
