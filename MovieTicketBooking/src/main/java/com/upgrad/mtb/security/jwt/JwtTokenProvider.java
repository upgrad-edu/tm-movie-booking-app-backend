package com.upgrad.mtb.security.jwt;

import com.upgrad.mtb.exceptions.CustomerDetailsNotFoundException;
import com.upgrad.mtb.services.CustomerServiceImpl;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;


@Component
public class JwtTokenProvider {

  @Value("${security.jwt.token.secret-key:secret}")
  private String secretKey = "secret";

  @Value("${security.jwt.token.expire-length:600000}")
  private long validityInMilliseconds = 600000;//10 minutes

  @Value("${security.jwt.token.expire-length:3600000}")
  private long refreshValidityInMilliseconds = 3600000; //60 minutes

  @Autowired
  CustomerServiceImpl customerService;

  @PostConstruct
  protected void init() {
    secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
  }

  public String createToken(String emailId) {

    Claims claims = Jwts.claims().setSubject(emailId);

    Date now = new Date();
    Date validity = new Date(now.getTime() + validityInMilliseconds);

    return Jwts.builder()
        .setClaims(claims)
        .setIssuedAt(now)
        .setExpiration(validity)
        .signWith(SignatureAlgorithm.HS256, secretKey)
        .compact();
  }

  public String createRefreshToken(String emailId) {

    Claims claims = Jwts.claims().setSubject(emailId);

    Date now = new Date();
    Date validity = new Date(now.getTime() + refreshValidityInMilliseconds);

    return Jwts.builder()
        .setClaims(claims)
        .setIssuedAt(now)
        .setExpiration(validity)
        .signWith(SignatureAlgorithm.HS256, secretKey)
        .compact();
  }

  public Authentication getAuthentication(String token) throws CustomerDetailsNotFoundException {

    UserDetails userDetails = this.customerService.loadCustomerDetails(getUsername(token));
    return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
  }

  public String getUsername(String token) {
    return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
  }

  public String resolveToken(HttpServletRequest req) {
    String bearerToken = req.getHeader("X-Access-Token");
    if (bearerToken != null) {
      return bearerToken.trim();
    }
    return null;
  }

  public boolean validateToken(String token) {
    if (!customerService.isTokenPresent(token)) {
      throw new InvalidJwtAuthenticationException("Expired or invalid JWT token");
    }
    try {
      Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);

      if (claims.getBody().getExpiration().before(new Date())) {
        return false;
      }

      return true;
    } catch (JwtException | IllegalArgumentException e) {
      throw new InvalidJwtAuthenticationException("Expired or invalid JWT token");
    }
  }
}
