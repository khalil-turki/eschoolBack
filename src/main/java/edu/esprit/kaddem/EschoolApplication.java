package edu.esprit.kaddem;

import com.stripe.Stripe;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.io.IOException;

@SpringBootApplication
public class EschoolApplication {
   @PostConstruct
   public void setup(){

       // This is your test secret API key.
       Stripe.apiKey = "";

   }



    public static void main(String[] args) throws IOException {
        SpringApplication.run(EschoolApplication.class, args);
    }

}
