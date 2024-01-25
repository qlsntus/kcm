package domain;

public class JoDTO {
	// ** private 으로 맴버변수 정의
	private int jno;
	private String jname;
	private String captain;
	private String project;
	private String slogan;
	
	// ** 생성자
	// => default 생성자, 모든값을 초기화하는 생성자
	public JoDTO() { }
	public JoDTO(int jno, String jname, String captain, String project, String slogan) {
		this.jno=jno;
		this.jname=jname;
		this.captain=captain;
		this.project=project;
		this.slogan=slogan;
	}
	
	// ** setter/getter
	public int getJno() {
		return jno;
	}
	public void setJno(int jno) {
		this.jno = jno;
	}
	public String getJname() {
		return jname;
	}
	public void setJname(String jname) {
		this.jname = jname;
	}
	public String getCaptain() {
		return captain;
	}
	public void setCaptain(String captain) {
		this.captain = captain;
	}
	public String getProject() {
		return project;
	}
	public void setProject(String project) {
		this.project = project;
	}
	public String getSlogan() {
		return slogan;
	}
	public void setSlogan(String slogan) {
		this.slogan = slogan;
	}
	
	// ** toString
	@Override
	public String toString() {
		return "JoDTO [jno=" + jno + ", jname=" + jname + ", captain=" + captain + ", project=" + project + ", slogan="
				+ slogan + "]";
	}
	
} //class
