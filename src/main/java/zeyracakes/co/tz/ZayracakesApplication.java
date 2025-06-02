package zeyracakes.co.tz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@CrossOrigin(origins = "*")
public class ZayracakesApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZayracakesApplication.class, args);
	}

}
