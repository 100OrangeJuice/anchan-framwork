package org.sakura.anchan;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("org.sakura.anchan.DAO")
public class AnchanApplication {

	public static void main(String[] args) {
		SpringApplication.run(AnchanApplication.class, args);
	}
}
