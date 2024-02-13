package in.gov.wildlifemisportal.credential.jwt;//package com.forest.wildlife.credential.jwt;
//
////import com.bezkoder.springjwt.security.services.UserDetailsServiceImpl;
//
//import com.forest.wildlife.credential.service.UserDetailsServiceImpl;
//import com.forest.wildlife.exceptionHandling.exception.securityException.JwtExpiredException;
//import io.jsonwebtoken.*;
//import io.jsonwebtoken.io.Decoders;
//import io.jsonwebtoken.security.Keys;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import jakarta.validation.constraints.NotNull;
//import lombok.extern.slf4j.Slf4j;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.HttpStatus;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//import org.springframework.util.StringUtils;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import javax.crypto.SecretKey;
//import java.io.IOException;
//
//@Slf4j
//public class AuthTokenFilter extends OncePerRequestFilter {
//  @Autowired
//  private JwtUtils jwtUtils;
//
//  @Autowired
//  private UserDetailsServiceImpl userDetailsService;
//  @Value("${wildLife.app.jwtSecret}")
//  private String jwtSecret;
//  private static final Logger logger = LoggerFactory.getLogger(AuthTokenFilter.class);
//
////  @Override
////  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
////
////    final String requestTokenHeader = request.getHeader("Authorization");
//
////    String username = null;
////    String jwtToken = null;
////
////    if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
////      jwtToken = requestTokenHeader.substring(7);
////      try {
////        username = jwtUtils.getUsernameFromToken(jwtToken);
////      } catch (IllegalArgumentException e) {
////        System.out.println("Unable to get JWT Token");
////      } catch (ExpiredJwtException e) {
////        System.out.println("JWT Token has expired");
////      }
////    } else {
////      System.out.println("JWT token does not start with Bearer");
////    }
////
////    if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
////
////      UserDetails userDetails = userDetailsService.loadUserByUsername(username);
////
////      if (jwtUtils.validateToken(jwtToken, userDetails)) {
////
////        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
////        usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
////        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
////      }
////    }
////    filterChain.doFilter(request, response);
////
////  }
//
//
//
//  @Override
//  protected void doFilterInternal(HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull FilterChain filterChain)
//      throws ServletException, IOException , JwtExpiredException {
//    if(request.getServletPath().equals("/admin/login") || request.getServletPath().equals("/admin/refreshToken")){
//      filterChain.doFilter(request, response);
//    }
//
////    if(request.getServletPath().equals("/citizen/getCitizenByEpicNo/{epicNo}") || request.getServletPath().equals("/citizen/add")){
////      if(request.getHeader("citizenLoginToken").equals("afb1ecc6-2ef2-310f-a0e0-a933c6cbce20")){
////        filterChain.doFilter(request, response);
////      }else{
////        response.sendError(HttpStatus.UNAUTHORIZED.value(),"UNAUTHORIZED");
////        log.info("UNAUTHORIZED");
////      }
//
////    }
//
//    try {
//      String jwt = parseJwt(request);
////      log.info("jwt : {}",request);
//
//
//
//// && validateJwtToken(jwt, request)
//      if (jwt != null && validateJwtToken(jwt, request)) {
////          SecretKey secret = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
////          Jwts.parserBuilder().setSigningKey(secret).build().parseClaimsJws(jwt);
////        Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(jwt);
//          String username = jwtUtils.getUserNameFromJwtToken(jwt);
//
//          UserDetails userDetails = userDetailsService.loadUserByUsername(username);
//          UsernamePasswordAuthenticationToken authentication =
//                  new UsernamePasswordAuthenticationToken(
//                          userDetails,
//                          null,
//                          userDetails.getAuthorities());
//          authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//
//          SecurityContextHolder.getContext().setAuthentication(authentication);
//
//      }
//    } catch (Exception e) {
//      String exceptionType = e.getClass().getSimpleName();
//      logger.error("Cannot set user authentication: {}", exceptionType);
//    }
//
////    filterChain.doFilter(request, response);
//  }
//
//  private String parseJwt(HttpServletRequest request) {
//    String headerAuth = request.getHeader("Authorization");
//
//    if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
//      return headerAuth.substring(7);
//    }
//
//    return null;
//  }
//
//  public boolean validateJwtToken(String authToken, HttpServletRequest request) throws JwtExpiredException {
//    try {
////      Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
////      SecretKey secret = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
////      Jwts.parserBuilder().setSigningKey(secret).build().parseClaimsJws(authToken);
//
//      SecretKey secret = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
//      Jwts.parserBuilder()
//              .setSigningKey(secret)
//              .build()
//              .parseClaimsJws(authToken);
//      return true;
//    }catch (MalformedJwtException e) {
//      logger.error("Invalid JWT token: {}", e.getMessage());
//    } catch (ExpiredJwtException e) {
//      logger.error("JWT token is expired: {}", e.getMessage());
////      request.setAttribute("expired", e.getMessage());
//      throw new JwtExpiredException(
//              authToken,"Jwt token was expired. Please make a new signin request or try with refresh token"
//      );
//    } catch (UnsupportedJwtException e) {
//      logger.error("JWT token is unsupported: {}", e.getMessage());
//    } catch (IllegalArgumentException e) {
//      logger.error("JWT claims string is empty: {}", e.getMessage());
//    }
//    return false;
////    throw new JwtExpiredException(authToken,"JWT token validation failed");
//  }
//
////  public Claims parseJwtToken(String authToken, String jwtSecret) {
////    byte[] secretKeyBytes = jwtSecret.getBytes();
////    Claims claims = Jwts.parserBuilder()
////            .setSigningKey(secretKeyBytes)
////            .build()
////            .parseClaimsJws(authToken)
////            .getBody();
////    return claims;
////  }
//
////  @ExceptionHandler(JwtExpiredException.class)
////  public void handleJwtExpiredException(JwtExpiredException ex, HttpServletResponse response) throws IOException {
////    response.sendError(HttpStatus.UNAUTHORIZED.value(), ex.getMessage());
////  }
//}
