package org.example.medical_examination_booking_app.web.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@RestController
public class AuthController {

    final static String clientId="";

    final static String clientSecret="";

    final static String tokenUrl="";

    final static String redirectUri="";

    @GetMapping("/auth/callback")
    public ResponseEntity<?> oauthCallback(@RequestParam(required = false) String code,
                                           @RequestParam(required = false) String error) {
        if (error != null) {
            return ResponseEntity.badRequest().body("Error during login: " + error);
        }
        if (code == null) {
            return ResponseEntity.badRequest().body("No authorization code received");
        }

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", clientId);
        params.add("client_secret", clientSecret);
        params.add("code", code);
        params.add("redirect_uri", redirectUri);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);

        try {
            ResponseEntity<Map> response = restTemplate.postForEntity(tokenUrl, request, Map.class);

            if (response.getStatusCode() == HttpStatus.OK) {
                Map<String, Object> tokens = response.getBody();
                // tokens contain access_token, refresh_token, id_token, etc.
                return ResponseEntity.ok(tokens);
            } else {
                return ResponseEntity.status(response.getStatusCode()).body("Failed to get tokens");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Exception during token exchange: " + e.getMessage());
        }
    }
}