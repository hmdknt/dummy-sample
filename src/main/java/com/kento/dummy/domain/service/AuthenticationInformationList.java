package com.kento.dummy.domain.service;

import com.kento.dummy.domain.model.AuthInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class AuthenticationInformationList {

    @Autowired
    private OAuth2AuthorizedClientService oAuth2AuthorizedClientService;

    // 認証情報取得
    public AuthInfo getAuthInfo(OAuth2AuthenticationToken oAuth2AuthenticationToken, AuthInfo authInfo) {

        OAuth2AuthorizedClient oAuth2AuthorizedClient =
                oAuth2AuthorizedClientService.loadAuthorizedClient(
                        oAuth2AuthenticationToken.getAuthorizedClientRegistrationId(),
                        oAuth2AuthenticationToken.getName());

        String sub = (String) oAuth2AuthenticationToken.getPrincipal().getAttributes().get("sub");
        String userName = (String) oAuth2AuthenticationToken.getPrincipal().getAttributes().get("name");
        String mailAddress = (String) oAuth2AuthenticationToken.getPrincipal().getAttributes().get("email");
        String accessToken = oAuth2AuthorizedClient.getAccessToken().getTokenValue();
        String refreshToken = oAuth2AuthorizedClient.getRefreshToken().getTokenValue();

        if (!StringUtils.isEmpty((sub))) {
            authInfo.setSub(sub);
        } else {
            authInfo.setSub("NOT_FOUND");
        }

        if (!StringUtils.isEmpty((userName))) {
            authInfo.setName(userName);
        } else {
            authInfo.setName("NOT_FOUND");
        }

        if (!StringUtils.isEmpty((mailAddress))) {
            authInfo.setEmailAddress(mailAddress);
        } else {
            authInfo.setEmailAddress("NOT_FOUND");
        }

        if (!StringUtils.isEmpty((accessToken))) {
            authInfo.setAccessToken(accessToken);
        } else {
            authInfo.setAccessToken("NOT_FOUND");
        }

        if (!StringUtils.isEmpty((refreshToken))) {
            authInfo.setRefreshToken(refreshToken);
        } else {
            authInfo.setRefreshToken("NOT_FOUND");
        }

        return authInfo;


    }

    public void setBean (String bean) {
        if(!StringUtils.isEmpty(bean)) {

        } else {

        }

    }
}
