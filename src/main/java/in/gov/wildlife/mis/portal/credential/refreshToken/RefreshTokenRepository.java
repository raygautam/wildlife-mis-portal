//package in.gov.wildlife.mis.portal.credential.refreshToken;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Modifying;
//import org.springframework.stereotype.Repository;
//
//import java.util.Optional;
//
//@Repository
//public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
//  Optional<RefreshToken> findByToken(String token);
//
//  @Modifying
//  void deleteByUserIdUserId(Long userId);
//}
