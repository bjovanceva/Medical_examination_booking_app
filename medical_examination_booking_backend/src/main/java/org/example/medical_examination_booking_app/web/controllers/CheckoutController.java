package org.example.medical_examination_booking_app.web.controllers;

import com.stripe.model.Event;
import com.stripe.model.checkout.Session;
import com.stripe.net.Webhook;
import com.stripe.param.checkout.SessionCreateParams;
import dto.ReservationListDto;
import org.example.medical_examination_booking_app.model.domain.MedicalExaminationsReservationList;
import org.example.medical_examination_booking_app.model.enumerations.ReservationListStatus;
import org.example.medical_examination_booking_app.service.application.MedicalExaminationsReservationListApplicationService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/checkout")
public class CheckoutController {

    private final MedicalExaminationsReservationListApplicationService medicalExaminationsReservationListApplicationService;

    public CheckoutController(MedicalExaminationsReservationListApplicationService medicalExaminationsReservationListApplicationService) {
        this.medicalExaminationsReservationListApplicationService = medicalExaminationsReservationListApplicationService;
    }


//    @Value("${stripe.secret.key}")
//    private String stripeSecretKey;
//
//    @Value("${stripe.webhook.secret}")
//    private String endpointSecret;

    @PostMapping("/create-checkout-session")
    public Map<String, Object> createCheckoutSession(@RequestBody Map<String, Object> data) throws Exception {
        Long amount = ((Number) data.get("amount")).longValue(); // in cents

        SessionCreateParams params =
                SessionCreateParams.builder()
                        .setMode(SessionCreateParams.Mode.PAYMENT)
                        .setSuccessUrl("http://localhost:3000/success?session_id={CHECKOUT_SESSION_ID}") // frontend success page
                        .setCancelUrl("http://localhost:3000/cancel")   // frontend cancel page
                        .addLineItem(
                                SessionCreateParams.LineItem.builder()
                                        .setQuantity(1L)
                                        .setPriceData(
                                                SessionCreateParams.LineItem.PriceData.builder()
                                                        .setCurrency("usd")
                                                        .setUnitAmount(amount) // e.g. 500 = $5.00
                                                        .setProductData(
                                                                SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                                                        .setName("Reservation Payment")
                                                                        .build()
                                                        )
                                                        .build()
                                        )
                                        .build()
                        )
                        .build();

        Session session = Session.create(params);

        Map<String, Object> response = new HashMap<>();
        response.put("id", session.getId());
        return response;
    }

    @GetMapping("/cleanList")
    public ResponseEntity<Void> cleanList(@AuthenticationPrincipal Jwt jwt) {
        String username = jwt.getClaim("preferred_username");
        System.out.println("CleanList is Called for user: " + username);

        medicalExaminationsReservationListApplicationService.save(username);


        medicalExaminationsReservationListApplicationService.getActiveReservationList(username)
                .ifPresent(list -> System.out.println("Remaining reservations: " + list.medicalExaminationReservations().size()));

        return ResponseEntity.ok().build();
    }

    }



