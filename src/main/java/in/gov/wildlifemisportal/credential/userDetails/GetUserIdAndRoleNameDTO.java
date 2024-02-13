package in.gov.wildlifemisportal.credential.userDetails;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link UserDetail_t} entity
 */
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class GetUserIdAndRoleNameDTO implements Serializable {
//    private  Integer userId;
//    private  String roleName;
    private  String label;
    private  Long value;
}
