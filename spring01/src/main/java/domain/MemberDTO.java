package domain;

//** DTO
//=> private 맴버변수
//=> getter/setter
//=> toString

public class MemberDTO {
	// 1) private 멤버변수
	private String id; // Primary_Key
	private String password; // not null
	private String name;
	private int age;
	private int jno;
	private String info;
	private double point;
	private String birthday;
	private String rid; //추천인
	
	// 2) getter/setter
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getJno() {
		return jno;
	}
	public void setJno(int jno) {
		this.jno = jno;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public double getPoint() {
		return point;
	}
	public void setPoint(double point) {
		this.point = point;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getRid() {
		return rid;
	}
	public void setRid(String rid) {
		this.rid = rid;
	}
	
	// 3) toString
	
	@Override
	public String toString() {
		return "MemberDTO [id=" + id + ", password=" + password + ", name=" + name + ", age=" + age + ", jno=" + jno
				+ ", info=" + info + ", point=" + point + ", birthday=" + birthday + ", rid=" + rid + "]";
	}
	
	
	
	

	
	

}
