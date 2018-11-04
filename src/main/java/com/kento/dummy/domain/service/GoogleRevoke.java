package com.kento.dummy.domain.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class GoogleRevoke {

    public void revoke(String accessToken){
        String uri = "https://accounts.google.com/o/oauth2/revoke";

        UriComponentsBuilder builder = UriComponentsBuilder
                .fromHttpUrl(uri)
                .queryParam("token", accessToken);

        RestTemplate restTemplate = new RestTemplate();

        String response = restTemplate.getForObject(builder.toUriString(), String.class);

    }
}
