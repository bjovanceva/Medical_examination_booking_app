package org.example.medical_examination_booking_app.web.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final RestTemplate restTemplate = new RestTemplate();

    @PostMapping("/create")
    public ResponseEntity<?> createOrder(@RequestBody Map<String, Object> request) {
        try {
            String clientId="";
            String clientSecret="";

            // 1️⃣ Get access token
            String auth = Base64.getEncoder().encodeToString((clientId + ":" + clientSecret).getBytes());
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Basic " + auth);
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

            HttpEntity<String> tokenRequest = new HttpEntity<>("grant_type=client_credentials", headers);
            Map tokenResponse = restTemplate.postForObject(
                    "https://api-m.sandbox.paypal.com/v1/oauth2/token",
                    tokenRequest,
                    Map.class
            );
            String accessToken = (String) tokenResponse.get("access_token");
            System.out.println(request.get("amount"));

            // 2️⃣ Create order
            HttpHeaders orderHeaders = new HttpHeaders();
            orderHeaders.set("Authorization", "Bearer " + accessToken);
            orderHeaders.setContentType(MediaType.APPLICATION_JSON);

            double amount = Double.parseDouble(request.get("amount").toString());
            Map<String, Object> orderBody = Map.of(
                    "intent", "CAPTURE",
                    "purchase_units", List.of(Map.of(
                            "amount", Map.of(
                                    "currency_code", "USD",
                                    "value", String.format("%.2f", amount)
                            )
                    ))
            );

            HttpEntity<Map<String, Object>> orderRequest = new HttpEntity<>(orderBody, orderHeaders);
            Map orderResponse = restTemplate.postForObject(
                    "https://api-m.sandbox.paypal.com/v2/checkout/orders",
                    orderRequest,
                    Map.class
            );

            return ResponseEntity.ok(Map.of("id", orderResponse.get("id")));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(Map.of("error", e.getMessage()));
        }
    }
}
