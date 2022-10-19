package edu.esprit.kaddem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class EschoolApplication {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(EschoolApplication.class, args);
        // open http://localhost:8081/swagger-ui/index.html in the browser
        Runtime.getRuntime()
                .exec("cmd.exe /c dir http://localhost:8081/swagger-ui/index.html");
    }

}
