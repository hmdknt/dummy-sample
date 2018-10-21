package com.kento.dummy.controller;

import com.kento.dummy.domain.model.AuthInfo;
import com.kento.dummy.domain.service.AuthenticationInformationList;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.user.OidcUserAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping
public class AuthController {

    @Autowired
    AuthenticationInformationList authenticationInformationList;

    @GetMapping("/auth")
    public String login() {
        return "auth/index";
    }

    @GetMapping("/auth/result")
    public String auth(OAuth2AuthenticationToken oAuth2AuthenticationToken , AuthInfo authInfo, Model model)  {

        authInfo = authenticationInformationList.getAuthInfo(oAuth2AuthenticationToken, authInfo);

//        OAuth2AuthorizedClient oAuth2AuthorizedClient =
//                oAuth2AuthorizedClientService.loadAuthorizedClient(
//                        oAuth2AuthenticationToken.getAuthorizedClientRegistrationId(),
//                        oAuth2AuthenticationToken.getName());
//
//        String accessToken = oAuth2AuthorizedClient.getAccessToken().getTokenValue();
//
//        authInfo.setName((String) oAuth2AuthenticationToken.getPrincipal().getAttributes().get("name"));
//        authInfo.setEmailAddress((String) oAuth2AuthenticationToken.getPrincipal().getAttributes().get("email"));
//        authInfo.setAccessToken(accessToken);

        model.addAttribute("authInfo", authInfo);

        return "/auth/result";
    }

    @PostMapping("/google")
    public String google(@RequestParam("botId") String botId,  Model model) {

        System.out.println(botId);

        return "/google/result";
    }

    @GetMapping("/back")
    public String top(Model model) {
        return "forward:auth";
    }
}
