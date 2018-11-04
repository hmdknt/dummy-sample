package com.kento.dummy.controller;

import com.kento.dummy.domain.model.AuthInfo;
import com.kento.dummy.domain.model.GoogleTask;
import com.kento.dummy.domain.service.GoogleRevoke;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.jaas.LoginExceptionResolver;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;


import javax.security.auth.login.LoginContext;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping
public class AuthController {

    @Autowired
    private OAuth2AuthorizedClientService oAuth2AuthorizedClientService;

    @Autowired
    GoogleRevoke googleRevoke;

    @GetMapping("/auth")
    public String login() {
        return "auth/index";
    }

    @GetMapping("/auth/result")
    public String auth(OAuth2AuthenticationToken oAuth2AuthenticationToken, AuthInfo authInfo, Model model) {

        OAuth2AuthorizedClient oAuth2AuthorizedClient =
                oAuth2AuthorizedClientService.loadAuthorizedClient(
                        oAuth2AuthenticationToken.getAuthorizedClientRegistrationId(),
                        oAuth2AuthenticationToken.getName());

        String accessToken = oAuth2AuthorizedClient.getAccessToken().getTokenValue();

        authInfo.setName((String) oAuth2AuthenticationToken.getPrincipal().getAttributes().get("name"));
        authInfo.setEmailAddress((String) oAuth2AuthenticationToken.getPrincipal().getAttributes().get("email"));
        authInfo.setAccessToken(accessToken);

        model.addAttribute("authInfo", authInfo);

        return "/auth/result";
    }

    @PostMapping("/google")
    public String google(@RequestParam String accessToken, Model model) {
        return "/google/result";
    }

    @GetMapping(value = "/back")
    public String top(@RequestParam String accessToken, Model model) {

        String uri = "https://accounts.google.com/o/oauth2/revoke";

        UriComponentsBuilder builder = UriComponentsBuilder
                .fromHttpUrl(uri)
                .queryParam("token", accessToken);

        RestTemplate restTemplate = new RestTemplate();

        String response = restTemplate.getForObject(builder.toUriString(), String.class);

//        googleRevoke.revoke(accessToken);
//        System.out.println(123);

        return "forward:auth";
    }
}
