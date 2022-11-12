package edu.esprit.kaddem.controller;

import edu.esprit.kaddem.services.TwilioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TwillioController {



    @Autowired
    TwilioService twillioService;

    @Value("+14246229984")
    private String from;

    @Value("+21698975800")
    private String to;

    @GetMapping("/sendSms")
    public String sendSms() {

        String body = "Salam alaykoum, votre payement a été effectué avec succés, merci de votre confiance";
        twillioService.sendSms(to, from, body);
        return "message sent successfully";


    }





    @GetMapping("/makeCall")
    public String makeVoiceCall() {

        twillioService.makeCall(from, to);
        return "call initiated..";


    }






}
