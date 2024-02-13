package in.gov.wildlife.mis.portal.credential.role;

//import com.example.jwt.jwtauthenticationdemo.Uppercase;
import lombok.Builder;
import lombok.Data;


/**
 * A DTO for the {@link Role_m} entity
 */
@Data
@Builder
public class RoleDto {
    private  Integer roleId;
    private  String roleName;
}