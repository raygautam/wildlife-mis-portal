package in.gov.wildlife.mis.portal.credential.jwt;

import com.forest.wildlife.auditTrail.AuditTrail;
import com.forest.wildlife.auditTrail.AuditTrailRepository;
import com.forest.wildlife.auditTrail.RequestBodyCachingWrapper;
import com.forest.wildlife.credential.service.UserDetailsServiceImpl;
import com.forest.wildlife.exceptionHandling.exception.commonException.JwtExpiredException;
import com.forest.wildlife.exceptionHandling.exception.securityException.CustomMalformedJwtException;
import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bucket4j;
import io.github.bucket4j.Refill;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;


@Component
@Slf4j
@WebFilter("/*")
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private Logger logger = LoggerFactory.getLogger(OncePerRequestFilter.class);
    @Autowired
    private JwtHelper jwtHelper;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private AuditTrailRepository auditTrailRepository;

    private final Bucket bucket;


    public JwtAuthenticationFilter() {
        Bandwidth limit = Bandwidth.classic(50, Refill.intervally(50, Duration.ofSeconds(1)));
        this.bucket = Bucket4j.builder()
                .addLimit(limit)
                .build();
    }
    @Override
    protected void doFilterInternal(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull FilterChain filterChain) throws ServletException, IOException {
        if (bucket.tryConsume(1)) {
            String requestHeader = request.getHeader("Authorization");
            logger.info(" Header :  {}", requestHeader);
            String username = null;
            String token = null;
            if (requestHeader != null && requestHeader.startsWith("Bearer")) {
                //looking good
                token = requestHeader.substring(7);
                try {

                    username = this.jwtHelper.getUsernameFromToken(token);

                } catch (IllegalArgumentException e) {
                    logger.info("Illegal Argument while fetching the username !!");
                    e.printStackTrace();
                    throw new IllegalArgumentException("Illegal Argument while fetching the username !!");
                } catch (ExpiredJwtException e) {
                    logger.info("Given jwt token is expired !!");
                    e.printStackTrace();
                    throw new JwtExpiredException(token,"Given jwt token is expired !!");
                } catch (MalformedJwtException e) {
                    logger.info("Some changed has done in token !! Invalid Token");
                    e.printStackTrace();
                    throw new CustomMalformedJwtException("JWT token is malformed");

                } catch (Exception e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }

            } else {
                logger.info("Invalid Header Value !! ");
            }

            //
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {


                //fetch user detail from username
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
                Boolean validateToken = this.jwtHelper.validateToken(token, userDetails);
                if (validateToken) {

                    //set the authentication
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);


                } else {
                    logger.info("Validation fails !!");
                }

            }

            try {
                RequestBodyCachingWrapper requestWrapper = new RequestBodyCachingWrapper(request);
                String requestBody = requestWrapper.getRequestBody();
                filterChain.doFilter(requestWrapper, response);
                AuditTrail auditTrail=AuditTrail.builder()
                        .url(request.getRequestURI())
                        .userName(request.getRemoteUser())
                        .payload(requestBody.isEmpty() ?"[]":requestBody)
                        .ipAddress(request.getRemoteAddr())
                        .userAgent(request.getHeader("User-Agent"))
                        .requestOn(LocalDateTime.now())
                        .httpMethod(request.getMethod())
                        .statusCode(response.getStatus())
                        .build();
                auditTrailRepository.save(auditTrail);
            } catch (Exception e) {
                logger.error("An error occurred while processing the filter chain: {}", e.getMessage());
                e.printStackTrace();
            }
        } else {
            response.getWriter().write("Too many requests. Please try again later.");
            response.setStatus(429);// HTTP 429 Too Many Requests
        }
     }
}



