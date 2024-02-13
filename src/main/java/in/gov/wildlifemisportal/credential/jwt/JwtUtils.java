package in.gov.wildlifemisportal.credential.jwt;//package com.forest.wildlife.credential.jwt;
//
//////import com.bezkoder.springjwt.security.services.UserDetailsImpl;
////import com.example.jwt.jwtauthenticationdemo.credential.security.payload.response.MessageResponse;
//
//
//import com.forest.wildlife.credential.service.UserDetailsImpl;
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import io.jsonwebtoken.security.Keys;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//
//import javax.crypto.SecretKey;
//import java.security.Key;
//import java.util.Date;
////import java.util.HashMap;
////import java.util.Map;
////import java.util.function.Function;
//
//@Component
//public class JwtUtils{
//  private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);
//
//  @Value("${wildLife.app.jwtSecret}")
//  private String jwtSecret;
//
////  @Value("${wildLife.app.jwtExpirationMs}")
////  private int jwtExpirationMs;
//
////  @Value("${jwt.refreshExpirationDateInMs}")
////  private int refreshExpirationDateInMs;
//
//  public String generateJwtToken(UserDetailsImpl userPrincipal) {
//    return generateTokenFromUsername(userPrincipal.getUsername());
//  }
//
//  public String generateTokenFromUsername(String username) {
//    Key signingKey = Keys.secretKeyFor(SignatureAlgorithm.HS512);
////    return Jwts.builder().setSubject(username).setIssuedAt(new Date())
////            .setExpiration(new Date((new Date()).getTime() +60*1000)).signWith(SignatureAlgorithm.HS512, jwtSecret)
//////            .setExpiration(new Date((new Date()).getTime() +60*1000)).signWith(signingKey,SignatureAlgorithm.HS512)
////            .compact();
//
////    SecretKey key = Keys.hmacShaKeyFor(jwtSecret.getBytes());
//
//    return Jwts.builder()
//            .setSubject(username)
//            .setIssuedAt(new Date())
//            .setExpiration(new Date((new Date()).getTime() + 60 * 1000))
//            .signWith(signingKey, SignatureAlgorithm.HS512)
//            .compact();
//  }
//
//  public String getUserNameFromJwtToken(String token) {
////    return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
////    SecretKey secret = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
////    return Jwts.parserBuilder().setSigningKey(secret).build().parseClaimsJws(token).getBody().getSubject();
//
//    SecretKey secretKey = Keys.hmacShaKeyFor(jwtSecret.getBytes());
//    Claims claims = Jwts.parserBuilder()
//            .setSigningKey(secretKey)
//            .build()
//            .parseClaimsJws(token)
//            .getBody();
//    return claims.getSubject();
//  }
//
//
//
//}
