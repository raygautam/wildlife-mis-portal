package in.gov.wildlife.mis.portal.credential.security.payload.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class TokenRefreshRequest {
  @NotBlank
  private String refreshToken;
}
