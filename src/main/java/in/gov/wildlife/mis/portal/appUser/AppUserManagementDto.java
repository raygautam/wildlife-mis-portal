package in.gov.wildlife.mis.portal.appUser;

import lombok.*;

import java.util.Set;

/**
 * A DTO for the {@link AppUser} entity
 */
@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
//@JsonIgnoreProperties(value = NonNull)
public class AppUserManagementDto {
//    @NotBlank(message = "Id field not be null and not empty and not blank")
    private  Long id;

//    @NotBlank(message = "Email field not be null and not empty and not blank")
//    @Email(regexp = "^(|([A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+))$", message = "Email address is invalid please provide valid email id.")
    private  String userName;

//    @NotBlank(message = "Name field not be null and not empty and not blank")
    private  String name;

//    @NotBlank(message = "field not be null and not empty and not blank")
    private  String password;

//    @NotBlank(message = "Role field not be null and not empty and not blank")
    private Set<Integer> roleId;

    private Long service;

    private Long division;

    private Long range;

}