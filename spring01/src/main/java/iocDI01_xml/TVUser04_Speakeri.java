package iocDI01_xml;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

//** TV 의 의존성 처리
//=> TV 는 여러개의 Speaker 중 선택적으로 사용할 수 있음 
//=> Speaker interface 적용 
//=> 생성자 주입, setter 주입
interface Speakeri {
	   void volumeUp();
	   void volumeDown();
	} //Speakeri

	class SpeakerA implements Speakeri {
	   public SpeakerA() { System.out.println("~~ SpeakerAAA Default 생성자 ~~"); }
	   @Override
	   public void volumeUp() { System.out.println("~~ SpeakerAAA volumeUp ~~"); } 
	   @Override
	   public void volumeDown() { System.out.println("~~ SpeakerAAA volumeDown ~~"); }
	} //SpeakerA

	class SpeakerB implements Speakeri {
	   public SpeakerB() { System.out.println("~~ SpeakerBBB Default 생성자 ~~"); }
	   @Override
	   public void volumeUp() { System.out.println("~~ SpeakerBBB volumeUp ~~"); } 
	   @Override
	   public void volumeDown() { System.out.println("~~ SpeakerBBB volumeDown ~~"); }
	} //SpeakerB

	//1) 고전적 방법 (직접 new : 소스 재컴파일)
	//=> SpeakerA, B 교체 : 직접코드에서
	class SsTVsi implements TV {
	   
	   private Speakeri speaker = new SpeakerA();
	   
	   public SsTVsi() { System.out.println("~~ SsTVsi Default 생성자 ~~"); }
	   
	   @Override
	   public void powerOn() { System.out.println("~~ SsTVsi powerOn ~~"); }
	   @Override
	   public void powerOff() { System.out.println("~~ SsTVsi powerOff ~~"); }
	   @Override
	   public void volumeDown() { speaker.volumeDown(); }
	   @Override
	   public void volumeUp() { speaker.volumeUp(); }
	} //SsTVs

	//2) IOC/DI -> 생성자 주입 
	//=> SpeakerA, B 교체 : ~xml 에서 설정시에
	class LgTVsi implements TV {
	   
	   private Speakeri speaker;
	   private String color;
	   private int price;
	   
	   public LgTVsi() { System.out.println("~~ LgTVsi Default 생성자 ~~"); }
	   
	   public LgTVsi(Speakeri speaker, String color, int price) { 
	      this.speaker=speaker;
	      this.color=color;
	      this.price=price;
	      System.out.printf("~~ LgTVsi 초기화 생성자 : color=%s, price=%d \n",color,price); 
	   }
	   
	   @Override
	   public void powerOn() { System.out.println("~~ LgTVsi powerOn ~~"); }
	   @Override
	   public void powerOff() { System.out.println("~~ LgTVsi powerOff ~~"); }
	   @Override
	   public void volumeDown() { speaker.volumeDown(); }
	   @Override
	   public void volumeUp() { speaker.volumeUp(); }
	} //LgTVs

	//3) IOC/DI -> setter 주입
	//=> SpeakerA, B 교체 : ~xml 에서 설정시에
	class AiTVsi implements TV {
	   
	   private Speakeri speaker;
	   private String color;
	   private int price;
	   
	   public AiTVsi() { System.out.println("~~ AiTVsi Default 생성자 ~~"); }
	   
	   public void setSpeaker(Speakeri speaker) { this.speaker=speaker; }
	   public void setColor(String color) { this.color=color; }
	   public void setPrice(int price) { this.price=price; }
	   
	   @Override
	   public void powerOn() { System.out.printf("~~ AiTVsi powerOn : color=%s, price=%d \n",color,price); }
	   @Override
	   public void powerOff() { System.out.println("~~ AiTVsi powerOff ~~"); }
	   @Override
	   public void volumeDown() { speaker.volumeDown(); }
	   @Override
	   public void volumeUp() { speaker.volumeUp(); }
	} //AiTVs

public class TVUser04_Speakeri {

		public static void main(String[] args) {
		      // 1. 스프링 컨테이너 생성
		      AbstractApplicationContext sc = new
		            GenericXmlApplicationContext("iocDI01_xml/app04.xml");

		      // 2. 필요한 객체를 전달받고 서비스 실행 
		      System.out.println("** 1) 고전적 방법 (직접 new : 소스 재컴파일) **");
		      TV sstv = (TV)sc.getBean("sstv");
		      sstv.powerOn();
		      sstv.volumeDown();
		      sstv.volumeUp();
		      sstv.powerOff();
		      
		      System.out.println("** 2) IOC/DI -> 생성자 주입 **");
		      TV lgtv = (TV)sc.getBean("lgtv");
		      lgtv.powerOn();
		      lgtv.volumeDown();
		      lgtv.volumeUp();
		      lgtv.powerOff();
		      
		      System.out.println("** 3) IOC/DI -> setter 주입 **");
		      TV aitv = (TV)sc.getBean("aitv");
		      aitv.powerOn();
		      aitv.volumeDown();
		      aitv.volumeUp();
		      aitv.powerOff();
		      
		      sc.close();
	} //main

}//class
