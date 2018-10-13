package com.fast.family.security.jwt;

import com.fast.family.security.SecurityConstants;
import com.fast.family.security.SecurityProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@Component
@Slf4j
public class JWTHelper {

    @Autowired
    private SecurityProperties securityProperties;

    private String secretKey = "";

    private String authoritiesKey = "";

    private long tokenValidityInMilliseconds;

    private long tokenValidityInMillisecondsForRememberMe;


    @PostConstruct
    public void init(){

        this.authoritiesKey = securityProperties.getJwt()
                .getAuthoritiesKey();

        this.secretKey = securityProperties.getJwt()
                .getSecret();

        this.tokenValidityInMilliseconds = securityProperties.getJwt()
                .getTokenValidityInseconds() * 1000;

        this.tokenValidityInMillisecondsForRememberMe = securityProperties.getJwt()
                .getTokenValidityInSecondsForRememberMe() * 1000;
    }

    public String createToken(Authentication authentication,boolean rememberMe){
        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));
        long now = (new Date()).getTime();
        Date validity;
        if (rememberMe){
            validity = new Date(now + this.tokenValidityInMillisecondsForRememberMe);
        } else {
            validity = new Date(now + this.tokenValidityInMilliseconds);
        }
        return Jwts.builder()
                .setSubject(authentication.getName())
                .claim(authoritiesKey,authorities)
                .signWith(SignatureAlgorithm.HS512,secretKey)
                .setExpiration(validity)
                .compact();
    }


    public Authentication getAuthentication(String token){
        Claims claims = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();
        Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.get(authoritiesKey).toString().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());
        User principal = new User(claims.getSubject(),"",authorities);
        return new UsernamePasswordAuthenticationToken(principal,"",authorities);
    }

    public String resolveToken(HttpServletRequest request){
        String bearerToken= request.getHeader(SecurityConstants.AUTHORIZATION_HEADER);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")){
            return bearerToken.substring(7, bearerToken.length());
        }
        return null;
    }

    public boolean validateToken(String authToken){
        try{
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(authToken);
            return true;
        } catch (Exception e){
            log.error("token验证失败",e);
            return false;
        }
    }
}
