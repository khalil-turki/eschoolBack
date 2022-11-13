package edu.esprit.kaddem.controller;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.StripeObject;
import com.stripe.model.checkout.Session;
import com.stripe.net.Webhook;
import edu.esprit.kaddem.model.PaymentSession;
import edu.esprit.kaddem.services.PaymentService;
import edu.esprit.kaddem.services.TwilioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/payment")
public class PayementController {
    @Autowired private PaymentService paymentService;

    @Autowired
    TwilioService twillioService;

    @Value("+14246229984")
    private String from;

    @Value("+21698975800")
    private String to;

    @Value("${stripe.public_key}")
    private String publicKey;

    @Value("${stripe.webhook_secret}")
    private String webhookSecret;

    @GetMapping("/pay")
    public String pay() {
        var session = new PaymentSession();

        String body = "Salam alaykoum, votre payement a été effectué avec succés, merci de votre confiance";
        twillioService.sendSms(to, from, body);
        return paymentService.createCheckoutSession();

    }


    @PostMapping(value = "/webhook")
    public String webhook(@RequestBody String payload, @RequestHeader("Stripe-Signature") String sigHeader) throws StripeException {
        assert sigHeader != null;
        try {
            var event = Webhook.constructEvent(payload, sigHeader, this.webhookSecret);
            System.out.println("event = " + event);
            if ("checkout.session.completed".equals(event.getType())) {
                StripeObject session = event.getDataObjectDeserializer().getObject().get();

                // Fulfill the purchase...
                paymentService.fullfillOrder(session);
            }

        } catch (StripeException e) {
            e.printStackTrace();
        }
        return "ok";
    }
}