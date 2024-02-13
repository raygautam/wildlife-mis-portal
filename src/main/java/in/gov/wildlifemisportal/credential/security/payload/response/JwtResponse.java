package in.gov.wildlifemisportal.credential.security.payload.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JwtResponse {
    private String token;
    private String type;
    private String refreshToken;
    private Long id;
    private String username;
    private String email;
    private  String stateName;
    private  String divisionName;
    private  String districtName;
    private  String rangeName;
    private Boolean isActive;
    private List<String> roles;
}

