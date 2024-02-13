package in.gov.wildlifemisportal.credential.userDetails;

import com.forest.wildlife.credential.role.Role_m;
import lombok.Data;

import java.io.Serializable;
import java.util.Set;


@Data
public class GetUserDetailDto implements Serializable {
//    private  Integer userId;
//    private  String emailId;
//    private  String password;
    private  Long userId;
    private Set<Role_m> roleName;
    private  String levelType;
    private  String levelName;
}