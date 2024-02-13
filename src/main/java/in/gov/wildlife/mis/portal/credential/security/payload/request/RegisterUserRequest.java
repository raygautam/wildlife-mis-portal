package in.gov.wildlife.mis.portal.credential.security.payload.request;

import lombok.Data;

import java.util.Set;

@Data
public class RegisterUserRequest {
    private  Integer userId;
    private  String emailId;
    private  String password;
    private Set<Integer> role;
    private  String levelType;
    private  String levelName;
}
