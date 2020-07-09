package com.tenseii.app.services;

import com.tenseii.app.models.GitHubCredentials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.Base64;

@Service
public class GitHubHttpsService {
    private final static String GIT_HUB_BASE_URL = "https://api.github.com/";

    private static RestTemplate restTemplate;
    private static HttpHeaders authHeaders;
    private GitHubCredentials credentials;

    @Autowired
    public GitHubHttpsService(RestTemplateBuilder restTemplateBuilder, GitHubCredentials credentials) {
        restTemplate = restTemplateBuilder.build();
        this.credentials = credentials;
        buildAuthHeaders();
    }

    private void buildAuthHeaders() {
        authHeaders = new HttpHeaders() {{
            var auth = credentials.getUserName() + ":" + credentials.getPassword();
            byte[] encodedAuth = Base64.getEncoder().encode(auth.getBytes());
            var authHeader = "Basic " + new String(encodedAuth);
            set("Authorization", authHeader);
        }};
    }

    public static ResponseEntity<String> performGet(String url) {
        return restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(authHeaders), String.class);
    }

    public static boolean isValidResponse(ResponseEntity response) {
        return response.getStatusCode().equals(HttpStatus.OK)
                && response.getBody() != null
                && !response.getBody().toString().isEmpty();
    }

    public static String buildTargetUrl(String additionUrl) {
        return GIT_HUB_BASE_URL + additionUrl;
    }
}
