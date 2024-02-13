package in.gov.wildlifemisportal.credential.userDetails;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Set;

/**
 * A DTO for the {@link UserDetail_t} entity
 */
@Data

//@JsonIgnoreProperties(value = NonNull)
public class UserDetailDto{
    @NotBlank(message = "Id field not be null and not empty and not blank")
    private  Long userId;

    @NotBlank(message = "UserName field not be null and not empty and not blank")
    private  String userName;

    @NotBlank(message = "Email field not be null and not empty and not blank")
    @Email(regexp = "^(|([A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+))$", message = "Email address is invalid please provide valid email id.")
    private  String emailId;

    @NotBlank(message = "field not be null and not empty and not blank")
    private  String password;

    @NotBlank(message = "Role field not be null and not empty and not blank")
    private Set<Integer> roleId;

}