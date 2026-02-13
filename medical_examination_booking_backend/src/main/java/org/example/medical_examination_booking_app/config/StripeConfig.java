package org.example.medical_examination_booking_app.config;

import com.stripe.Stripe;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StripeConfig {

    @PostConstruct
    public void init() {
        Stripe.apiKey = "sk_test_51S3BlJEN8W16cMGjZGrc424YdPCAWE1jV17uZyzYoFCxcxO7l5ZnkGv9QZwy89BQQUsqHRU0kxyKKQ9tHtcMk3ub00G0UzrHM1";
    }
}
