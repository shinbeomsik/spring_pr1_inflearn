package hello.core.singleton;

public class StatefulService {
	
	//private int price; //상태를 유지하는 필드   ,,-->만약 서비스1로 10000원을 넣었는데  b사용자 때문에 20000원이 들어옴
	
	public int order(String name, int price) { 
		System.out.println("name = " + name + " price = " + price);
		
		//this.price = price;
		return price;
	}
	
	
	/*
	 * public int getPrice() { // return price; }
	 */
}
