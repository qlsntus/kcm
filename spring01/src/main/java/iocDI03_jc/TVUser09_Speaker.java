package iocDI03_jc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Component;

//** Java Bean Configuration class를 이용한 DI
//=> Bean 컨테이너 : AnnotationConfigApplicationContext 사용
//=> Test02 : 스피커 1개 사용 TV



class Speaker {
	   public Speaker() { System.out.println("~~ Speaker Default 생성자 ~~"); }
	   public void volumeUp() { System.out.println("~~ Speaker volumeUp ~~"); } 
	   public void volumeDown() { System.out.println("~~ Speaker volumeDown ~~"); }
	} //Speaker

	//1) 고전적 방법 (직접 new : 소스 재컴파일)
	//=> Speaker 생성 : 직접코드에서

	class SsTVs implements TV {
	   
	   private Speaker speaker = new Speaker();
	   
	   public SsTVs() { System.out.println("~~ SsTVs Default 생성자 ~~"); }
	   
	   @Override
	   public void powerOn() { System.out.println("~~ SsTVs powerOn ~~"); }
	   @Override
	   public void powerOff() { System.out.println("~~ SsTVs powerOff ~~"); }
	   @Override
	   public void volumeDown() { speaker.volumeDown(); }
	   @Override
	   public void volumeUp() { speaker.volumeUp(); }
	} //SsTVs

//2) IOC/DI

	class LgTVs implements TV {
	
	   @Autowired(required = false)
	   private Speaker speaker;
	   private String color;
	   private int price;
	   
	   public LgTVs() { System.out.println("~~ LgTVs Default 생성자 ~~"); }
	   
	   public LgTVs(Speaker speaker, String color, int price) { 
	      this.speaker=speaker;
	      this.color=color;
	      this.price=price;
	      System.out.printf("~~ LgTVs 초기화 생성자 : color=%s, price=%d \n",color,price); 
	   }
	   
	   @Override
	   public void powerOn() { System.out.println("~~ LgTVs powerOn ~~"); }
	   @Override
	   public void powerOff() { System.out.println("~~ LgTVs powerOff ~~"); }
	   @Override
	   public void volumeDown() { speaker.volumeDown(); }
	   @Override
	   public void volumeUp() { speaker.volumeUp(); }
	} //LgTVs

	//3) IOC/DI -> 
	class AiTVs implements TV {
	   
	   @Autowired
	   private Speaker speaker;
	   private String color;
	   private int price;
	   
	   public AiTVs() { System.out.println("~~ AiTVs Default 생성자 ~~"); }
	   
	   public void setSpeaker(Speaker speaker) { this.speaker=speaker; }
	   public void setColor(String color) { this.color=color; }
	   public void setPrice(int price) { this.price=price; }
	   
	   @Override
	   public void powerOn() { System.out.printf("~~ AiTVs powerOn : color=%s, price=%d \n",color,price); }
	   @Override
	   public void powerOff() { System.out.println("~~ AiTVs powerOff ~~"); }
	   @Override
	   public void volumeDown() { speaker.volumeDown(); }
	   @Override
	   public void volumeUp() { speaker.volumeUp(); }
	} //AiTVs

public class TVUser09_Speaker {

	public static void main(String[] args) {
	      // 1. 스프링 컨테이너 생성
	      AbstractApplicationContext sc = new
	           AnnotationConfigApplicationContext(JavaConfig02.class);

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

	} // main

} //class
