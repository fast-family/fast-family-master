package com.fast.family.security.jwt;


import com.fast.family.core.security.SecurityConstants;
import com.fast.family.core.security.SecurityProperties;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.DefaultOAuth2RefreshToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 张顺
 * @version 1.0
 */
@Slf4j
public class CustomJwtTokenEnhancer implements TokenEnhancer {

    @Autowired
    private SecurityProperties properties;

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken oAuth2AccessToken, OAuth2Authentication oAuth2Authentication) {
        if (oAuth2AccessToken instanceof DefaultOAuth2AccessToken) {
            DefaultOAuth2AccessToken token = (DefaultOAuth2AccessToken) oAuth2AccessToken;
            String clientId = oAuth2Authentication.getOAuth2Request().getClientId();
            Date expiration = oAuth2AccessToken.getExpiration();
            String createToken = createToken(clientId, expiration);
            token.setValue(createToken);
            OAuth2RefreshToken refreshToken = oAuth2AccessToken.getRefreshToken();
            if (refreshToken instanceof DefaultOAuth2AccessToken) {
                token.setRefreshToken(new DefaultOAuth2RefreshToken(createToken(clientId, expiration)));
            }
            Map<String, Object> additionalInformation = new HashMap<>();
            additionalInformation.put("client_id", oAuth2Authentication.getOAuth2Request().getClientId());
            token.setAdditionalInformation(additionalInformation);
            return token;
        }
        return oAuth2AccessToken;
    }

    private String createToken(String clientId, Date expiration) {
        return Jwts.builder()
                .setSubject(clientId)
                .claim(SecurityConstants.AUTHORIZATION_HEADER, clientId)
                .signWith(SignatureAlgorithm.HS256, properties.getJwt().getAuthoritiesKey())
                .setExpiration(expiration)
                .compact();
    }
}
