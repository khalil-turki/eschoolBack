package edu.esprit.kaddem.services;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.*;
import com.stripe.model.checkout.Session;
import com.stripe.param.PaymentLinkCreateParams;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PaymentService {

    @Value("${stripe.private_key}")
    private String secretKey;

    @PostConstruct
    public void init() {
        Stripe.apiKey = secretKey;
    }

    @SneakyThrows
    public String createCheckoutSession() {
        var product = this.createProduct();
        var price = this.createPrice(product);

        List<Object> lineItems = new ArrayList<>();
        Map<String, Object> lineItem1 = new HashMap<>();
        lineItem1.put("price", price.getId());
        lineItem1.put("quantity", 1);
        lineItems.add(lineItem1);
        Map<String, Object> params = new HashMap<>();
        params.put(
                "success_url",
                "https://example.com/success"
        );
        params.put(
                "cancel_url",
                "https://example.com/cancel"
        );
        params.put("line_items", lineItems);
        params.put("mode", "payment");

        Session session = Session.create(params);
        System.out.println("session = " + session);
        session.getId();
        return session.getUrl();
    }

    @SneakyThrows
    private Price createPrice(Product product) {
        Map<String, Object> params = new HashMap<>();
        params.put("unit_amount", 150000);
        params.put("currency", "usd");
        params.put("product", product.getId());
        return Price.create(params);
    }

    @SneakyThrows
    private Product createProduct() {
        var randomInt = (int) (Math.random() * 1000);
        Map<String, Object> params = new HashMap<>();
        params.put("name", "Kaddem" + randomInt);
        return Product.create(params);
    }

    @SneakyThrows
    private Object processWebhookMessage(Charge event) {
        System.out.println("event = " + event);
        return "ok";
    }

    public void fullfillOrder(StripeObject session) {
        System.out.println("session = " + session);
    }
}
