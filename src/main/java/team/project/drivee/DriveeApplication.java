package team.project.drivee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
public class DriveeApplication {

    public static void main(String[] args) {
        SpringApplication.run(DriveeApplication.class, args);
    }

}
