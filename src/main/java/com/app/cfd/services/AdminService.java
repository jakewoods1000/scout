package com.app.cfd.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AdminService {
    @Value("${okta.oauth2.issuer}")
    private String issuer;
    @Value("${okta.oauth2.client-id}")
    private String clientId;
    @Value("${okta.oauth2.client-secret}")
    private String clientSecret;

    public String getBearer() {
        String url = "https://dev-ix48gk13ziy7zfxi.us.auth0.com/oauth/token";
        HttpHeaders headers = new HttpHeaders();
        String body = "{\"client_id\":\"" + clientId + "\",\"client_secret\":\"" + clientSecret + "\",\"audience\":\"https://dev-ix48gk13ziy7zfxi.us.auth0.com/api/v2/\",\"grant_type\":\"client_credentials\"}";
        headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate template = new RestTemplate();
        HttpEntity<String> request = new HttpEntity<>(body, headers);

        return template.postForObject(url, request, String.class);
    }
}
