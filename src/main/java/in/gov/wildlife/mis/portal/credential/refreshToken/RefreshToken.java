package in.gov.wildlife.mis.portal.credential.refreshToken;

import com.forest.wildlife.credential.userDetails.UserDetail_t;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.time.Instant;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RefreshToken {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "refreshToken_gen")
  @SequenceGenerator(name = "refreshToken_gen", sequenceName = "refreshToken_gen_seq", allocationSize = 1)
  private Long refreshTokenId;

  @OneToOne
  @JoinColumn(name = "user_id", referencedColumnName = "user_id")
  private UserDetail_t userId;

  @Column(nullable = false, unique = true)
  private String token;

  @Column(nullable = false)
  private Instant expiryDate;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
    RefreshToken that = (RefreshToken) o;
    return getRefreshTokenId() != null && Objects.equals(getRefreshTokenId(), that.getRefreshTokenId());
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }
}
