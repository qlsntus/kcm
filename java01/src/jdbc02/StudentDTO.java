package jdbc02;

//** VO (Value Object)
//=> 특정 비즈니스 값을 담은 객체로 값을 표현하기 위한용도
//=> 불변객체 immutable object
// 	 DTO와 유사하나 read only 속성을 가짐
//   그러므로 setter 속성을 띄는 메소드 금지
//=> 특징: 데이터가 전송 과정 중에 변조되지 않음을 보장할 수 있다
//=> 다양한 로직 추가 가능

//** DTO (Data Transfer Object)
//=> 계층간 데이터 교환을 위한 객체
//=> 가변객체 mutable object
//   로직을 포함하지않은 getter/setter 메소드만 가질수 있는 순수 Data 전달용
//=> View 와 통신하며 request, response 처리위해 값의 변경이 유동적 (View Layer)
//=> 네이밍: ~~DTO 

//** 결론 
//=> Spring MyBatis를 쓰는 경우에는 주로 VO라 표현하고 때로는 DTO라 표현하기도 하며
//   Spring JPA를 쓰는 경우에는 Entity 라고 표현한다.
//   그리고 DTO와 VO는 위의 내용처럼 분명한 차이가 있다.

//=> 참고 DTO 와 VO 
//https://multifrontgarden.tistory.com/182?category=471239 

//---------------------------------------------

//** 추가적 분류
//=> 스프링 JPA를 사용하면 객체와 Table을 구체적으로 매핑하기 때문에
//   Entity 로 구분함.

//** Entity
//=> 실제 DB 테이블과 매핑되는 클래스 (DB Layer)
//=> 가변객체 mutable object
//   로직 포함 가능한 getter/setter 메소드를 가질수 있다
//=> 네이밍: 테이블명과 동일 

//** Domain
//=> 어플리케이션 내 로직들이 관여하는 정보와 활동의 영역,
//   즉 해결하고자하는 업무 영역을 도메인(Domain) 이라한다.
//   예를 들어 "온라인 서점" 도메인은 회원관리, 주문, 결제등의 하위도메인을 가진다.
//=> 이러한 도메인을 개념적으로 표현한것을 도메인 모델이라하고 이러한 분석과정을 통해
//   도출된 결과물을 모델객체라 하며 이것은 Entity와 Value로 구분할 수 있다.
//=> 참고 Domain, Entity, Value(Object)
//	 https://doing7.tistory.com/79 

//=> 주로 Entity(Table) 관련 폴더명으로 사용됨  

//---------------------------------------------
//** ~DTO 정의
//=> 맴버변수 : private
//=> 외부에서는 setter/getter 로 접근
//=> 자료확인시 편리성을 위해 toString() 메서드 오버라이딩 

//** 객체 초기화
// => setter
// => 생성자

public class StudentDTO extends JoDTO {
	// 1) private 으로 맴버변수 정의
	private int sno;
	private String name;
	private int age;
	private int jno;
	private String info;
	private double point;
	private String cname; // 조장이름, 필요시사용
	//=> cName: setCName()... 멤버변수명의 2번째 알바뱃도 
	
	// ** 생성자
	// => default 생성자, 모든값을 초기화하는 생성자
	public StudentDTO() { }
	public StudentDTO(int sno, String name, int age, int jno, String info, double point, String cname) {
		this.sno=sno;
		this.name=name;
		this.age=age;
		this.jno=jno;
		this.info=info;
		this.point=point;
		this.cname=cname;
	}
	
	// => 조상의 모든 컬럼포함 모두 초기화하는 생성자
	public StudentDTO(int sno, String name, int age, int jno, String info, double point, String cname,
			String jname, int captain, String project, String slogan) {
		super(jno,jname,captain,project,slogan);
		this.sno=sno;
		this.name=name;
		this.age=age;
		this.jno=jno;
		this.info=info;
		this.point=point;
		this.cname=cname;
	}

	// 2) getter/setter
	public int getSno() {
		return sno;
	}
	public void setSno(int sno) {
		this.sno = sno;
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
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	
	// 3) toString
	// => Object 에 정의된 toString() 메서드를 활용해서
	//    객체의 값을 편리하게 확인하기위해 사용 
	@Override
	public String toString() {
		return "StudentDTO [sno=" + sno + ", name=" + name + ", age=" + age 
				+ ", jno=" + jno + ", jname=" + getJname() 
				+ ", info=" + info + ", point=" + point 
				+ ", captain=" + getCaptain() + ", cname=" + cname 
				+ ", project=" + getProject() + "]";
	}
	
} //class
