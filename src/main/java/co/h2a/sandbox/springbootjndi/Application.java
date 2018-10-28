package co.h2a.sandbox.springbootjndi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * Exemplos retirados de:
 * https://www.java4s.com/spring-boot-tutorials/spring-boot-configure-datasource-using-jndi-with-example/
 */

@SpringBootApplication
public class Application extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
