package haagahelia.harjtyo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import haagahelia.harjtyo.domain.Time;

@SpringBootApplication
public class HarjtyoApplication {

	public static void main(String[] args) {
		SpringApplication.run(HarjtyoApplication.class, args);
		
		Time time = new Time();
		
		
		System.out.println("--- HarjtyoApplication running ---");
		System.out.println("--- " + time.getCurrentdate()+ " " + time.getClock() + " ---");



		
		
		

		
		
		
		

		

		
		
	}

}
