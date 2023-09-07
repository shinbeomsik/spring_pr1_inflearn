package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

public class StatefulServiceTest {

	@Test
	void satefulServiceSingleton() {
		ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
		StatefulService statefulService1 = ac.getBean(StatefulService.class);
		StatefulService statefulService2 = ac.getBean(StatefulService.class);
		
		//ThreatA : A 사용자가 10000원을 주문
		 int userAprice = statefulService1.order("userA", 10000);
		//ThreatB : B 사용자가 20000원을 주문
		 int userBprice = statefulService2.order("userA", 20000);
		
		//ThreadA : 사용자A가 주문 금액을조회
		 //int price = statefulService1.getPrice();
		 
		 System.out.println("price = " + userAprice);
		 
		// Assertions.assertThat(statefulService1.getPrice()).isEqualTo(20000);
	}

	static class TestConfig {

		@Bean
		public StatefulService statefulService() {
			return new StatefulService();
		}

	}
}
