package iocDI03_jc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

//** Java Bean Configuration class를 이용한 DI
//=> Test03 : 스피커 2개중 선택 
//=> 생성자 를 이용한 주입.. & JC에서 @, xml 병행사용

//*** JC 와 @ 병행사용
//*** JC , @, xml 병행사용
//=> JC 내에서 xml 로 생성된 객체를 직접 사용하는것은 허용 되지 않음. 
// 단, User 클래스에서는 사용가능

//** 실습
//=> SsTVsi , Speaker 는 xml 로 생성
//=> LgTVsi, AiTVsi 는 JC 의 @Bean 으로 생성
//=> LgTVsi (Speaker 생성자주입) 
// AiTVsi (Speaker @Autowired)
@ImportResource("iocDI03_jc/app10.xml")
@Configuration


public class JavaConfig03 {
	//1) 고전적 방법 ( 직접 new : 소스 재컴파일)
	// => xml 병행 test
	
	//2( IOC:DI: 생성자 주입
	// = > SpeakerB를 xml = 생성시 : 전달불가능
	// = > SpeakerB를 @ 로  생성시 : 전달불가능
	@Bean
	public TV lgtv( ) { return  new LgTVsi(spb(), "Orange",1234000); }
	
	public Speakeri spb() {return new SpeakerB(); }
		
	//3) IOC/DI
	// => AiTVsi를 jc 에서 생성 후 Speaker은 @Autowired 
	// => SpeakerA 를 jc로 생성후 @Bean의 적용에 따라 다른 결과
	// => SpeakerA 를 xml, @ 생성후 확인
	@Bean
	public TV aitv() { return new AiTVsi(); }
	
	// => AiTVsi를 xml에서 생성후 @Autowired Test

} //class
