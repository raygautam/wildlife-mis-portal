//package in.gov.wildlife.mis.portal.appUser;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import in.gov.wildlife.mis.portal.comman.LoginRequest;
//import in.gov.wildlife.mis.portal.credential.jwt.JwtHelper;
//import in.gov.wildlife.mis.portal.role.RoleRepository;
//import lombok.extern.slf4j.Slf4j;
//import org.modelmapper.ModelMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@CrossOrigin("*")
//@Slf4j
//@RequestMapping("/admin")
//
//public class AppUserManagementController {
//
//    @Autowired
//    private AppUserServiceInter userDetailServiceInter;
//
//    @Autowired
//    private AuthenticationManager authenticationManager;
//
////    @Autowired
////    private JwtUtils jwtUtils;
//
//    @Autowired
//    private JwtHelper helper;
//
//    @Autowired
//    private ModelMapper modelMapper;
//
//    @Autowired
//    private RoleRepository role_mRepository;
//
//    @Autowired
//    private AppUserManagementRepository userDetailTRepository;
//
////    @Autowired
////    private RefreshTokenService refreshTokenService;
//
//    @Autowired
//    private BCryptPasswordEncoder bCryptPasswordEncoder;
//
////    public UserDetailController() {
////    }
//
//    @PostMapping("/login")
//    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
//        log.info("loginRequest.getUserName() : {}", loginRequest.getUserName());
//        log.info(" loginRequest.getPassword() : {}", loginRequest.getPassword());
//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(loginRequest.getUserName(),
//                        loginRequest.getPassword()));
////                        bCryptPasswordEncoder.encode(loginRequest.getPassword())));
//        if(authentication.isAuthenticated()){
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//            AppUserImpl userDetails = (AppUserImpl) authentication.getPrincipal();
////        try{
//            log.info("userDetails {}", userDetails);
//            String jwt = helper.generateToken(userDetails);
//            log.info("jwt {}", jwt);
//            List<String> roles = userDetails.getAuthorities().stream()
//                    .map(GrantedAuthority::getAuthority)
//                    .toList();
//            log.info("roles {}", roles);
//
////            RefreshToken refreshToken = refreshTokenService.createRefreshToken(userDetails.getId());
//            log.info("roles {}", roles);
//
//            return ResponseEntity.ok(
////                    jwt
//                    JwtResponse.builder()
//                            .token(jwt)
//                            .type("Bearer")
//                            .refreshToken(refreshTokenService.createRefreshToken(userDetails.getId()).getToken())
//                            .id(userDetails.getId())
//                            .username(userDetails.getUsername())
//                            .email(userDetails.getEmail())
//                            .roles(roles)
//                            .stateName(userDetails.getStateName())
//                            .divisionName(userDetails.getDivisionName())
//                            .districtName(userDetails.getDistrictName())
//                            .rangeName(userDetails.getRangeName())
//                            .isActive(userDetails.getActive())
//                            .build()
//            );
//        }else {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("UNAUTHORIZED")
//        }
////        return null;
////
//
////---------------------------------
////        JwtResponse jwtResponse=new JwtResponse();
////            jwtResponse.setToken(jwt);
////            jwtResponse.setType("Bearer");
//////            jwtResponse.setRefreshToken(refreshToken.getToken());
////            jwtResponse.setId(userDetails.getId());
////            jwtResponse.setUsername(userDetails.getUsername());
////            jwtResponse.setEmail(userDetails.getEmail());
////            jwtResponse.setRoles(roles);
////            jwtResponse.setStateName(userDetails.getStateName());
////            jwtResponse.setDivisionName(userDetails.getDivisionName());
////            jwtResponse.setDistrictName(userDetails.getDistrictName());
////            jwtResponse.setRangeName(userDetails.getRangeName());
////            jwtResponse.setIsActive(userDetails.getActive());
////----------------------------
//
//
////                ------------------------------
////        }catch (Exception e){
////            ErrorResponse errorResponse=new ErrorResponse();
////            errorResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
////            errorResponse.setMessage(e.getMessage());
////            return ResponseEntity.ok(errorResponse);
////        }
////        String jwt = jwtUtils.generateJwtToken(userDetails);
////
////        List<String> roles = userDetails.getAuthorities().stream()
////                .map(GrantedAuthority::getAuthority)
////                .collect(Collectors.toList());
////
////        RefreshToken refreshToken = refreshTokenService.createRefreshToken(userDetails.getId());
////
////        JwtResponse jwtResponse=new JwtResponse();
////        jwtResponse.setToken(jwt);
////        jwtResponse.setRefreshToken(refreshToken.getToken());
////        jwtResponse.setId(userDetails.getId());
////        jwtResponse.setUsername(userDetails.getUsername());
////        jwtResponse.setEmail(userDetails.getEmail());
////        jwtResponse.setRoles(roles);
////        jwtResponse.setLevelName(userDetails.getLevelName());
////        jwtResponse.setLevelType(userDetails.getLevelType());
////        return ResponseEntity.ok(jwtResponse);
//    }
//
//    @PostMapping("/refreshToken")
//    public ResponseEntity<?> refreshToken(@Valid @RequestBody TokenRefreshRequest request) {
//        String requestRefreshToken = request.getRefreshToken();
////        return ResponseEntity.ok(requestRefreshToken);
//        return refreshTokenService.findByToken(requestRefreshToken)
//                .map(refreshTokenService::verifyExpiration)
//                .map(RefreshToken::getUserId)
//                .map(user -> {
//                    String token = helper.generateTokenFromUsername(user.getUserName());
//                    return ResponseEntity.ok(new TokenRefreshResponse(token, requestRefreshToken, "Bearer"));
//                })
//                .orElseThrow(() -> new TokenRefreshException(requestRefreshToken,
//                        "Refresh token is not in database!"));
//    }
//
////    @GetMapping("/token/refresh")
////    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
////        String authorizationHeader = request.getHeader(AUTHORIZATION);
////        if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
////            try {
////                String refresh_token = authorizationHeader.substring("Bearer ".length());
////                Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
////                JWTVerifier verifier = JWT.require(algorithm).build();
////                DecodedJWT decodedJWT = verifier.verify(refresh_token);
////                String username = decodedJWT.getSubject();
////                User user = userService.getUser(username);
////                String access_token = JWT.create()
////                        .withSubject(user.getUsername())
////                        .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
////                        .withIssuer(request.getRequestURL().toString())
////                        .withClaim("roles", user.getRoles().stream().map(Role::getName).collect(Collectors.toList()))
////                        .sign(algorithm);
////                Map<String, String> tokens = new HashMap<>();
////                tokens.put("access_token", access_token);
////                tokens.put("refresh_token", refresh_token);
////                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
////                new ObjectMapper().writeValue(response.getOutputStream(), tokens);
////            }catch (Exception exception) {
////                response.setHeader("error", exception.getMessage());
////                response.setStatus(FORBIDDEN.value());
////                //response.sendError(FORBIDDEN.value());
////                Map<String, String> error = new HashMap<>();
////                error.put("error_message", exception.getMessage());
////                response.setContentType(MimeTypeUtils.APPLICATION_JSON_VALUE);
////                new ObjectMapper().writeValue(response.getOutputStream(), error);
////            }
////        } else {
////            throw new RuntimeException("Refresh token is missing");
////        }
////    }
////}
//
//    @PostMapping("/signout")
//    public ResponseEntity<?> logoutUser() {
//        AppUserImpl userDetails = (AppUserImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        Long userId = userDetails.getId();
//        refreshTokenService.deleteByUserId(userId);
//        return ResponseEntity.ok(new MessageResponse("Log out successful!"));
//    }
//
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
//    @PostMapping("/insertUserDetail")
//    public ResponseEntity<?> insertUserDetail(@RequestBody AppUserManagementDto userDetailDto) throws JsonProcessingException {
//        if (userDetailTRepository.existsByUserName(userDetailDto.getUserName())) {
//            return ResponseEntity
//                    .badRequest()
//                    .body(new MessageResponse("Error: Username is already taken!"));
//        }
//
//        if (userDetailTRepository.existsByEmailId(userDetailDto.getEmailId())) {
//            return ResponseEntity
//                    .badRequest()
//                    .body(new MessageResponse("Error: Email is already in use!"));
//        }
//
////        return ResponseEntity.ok(userDetailDto);
//        return new ResponseEntity<>("User " + userDetailServiceInter.insertUserDetail(userDetailDto) + " register successfully.", HttpStatus.CREATED);
//    }
//
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
////    @PreAuthorize("hasRole('ROLE_USER')")
//    @GetMapping("/getUserDetails")
//    public ResponseEntity<?> getUserDetails() {
//        return new ResponseEntity<>(userDetailServiceInter.getUserDetails(), HttpStatus.OK);
//    }
//
////    @GetMapping("/getUserDetailsByLevelName")
//
//    //get userId and roleName
////    @PreAuthorize("hasRole('ROLE_ADMIN')")
////    @GetMapping("/getUserIdAndRoleName")
////    public ResponseEntity<?> getUserIdAndRoleName(){
////        return new ResponseEntity<>(userDetailServiceInter.getUserIdAndRoleName(), HttpStatus.OK);
////    }
//}
