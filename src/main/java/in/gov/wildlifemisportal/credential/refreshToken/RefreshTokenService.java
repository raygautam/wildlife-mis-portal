package in.gov.wildlifemisportal.credential.refreshToken;

import com.forest.wildlife.credential.userDetails.UserDetail_tRepository;
import com.forest.wildlife.exceptionHandling.exception.securityException.TokenRefreshException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
public class RefreshTokenService {

  //  @Value("${wildLife.app.jwtRefreshExpirationDateInMs}")
//  private Long refreshTokenDurationMs;
  private final RefreshTokenRepository refreshTokenRepository;
  private final UserDetail_tRepository userRepository;

  public RefreshTokenService(RefreshTokenRepository refreshTokenRepository, UserDetail_tRepository userRepository) {
    this.refreshTokenRepository = refreshTokenRepository;
    this.userRepository = userRepository;
  }

  public Optional<RefreshToken> findByToken(String token) {
    return refreshTokenRepository.findByToken(token);
  }

  public RefreshToken createRefreshToken(Long userId) {
    RefreshToken refreshToken = new RefreshToken();

    refreshToken.setUserId(userRepository.findById(userId).orElseThrow(()->new RuntimeException(userId+" Data not found??")));
    refreshToken.setExpiryDate(Instant.now().plusMillis(60*60*1000));
    refreshToken.setToken(UUID.randomUUID().toString());

    refreshToken = refreshTokenRepository.save(refreshToken);
    return refreshToken;
  }


  public RefreshToken verifyExpiration(RefreshToken token) {
    if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
      refreshTokenRepository.delete(token);
      throw new TokenRefreshException(token.getToken(), "Refresh token was expired. Please make a new signIn request");
    }

    return token;
  }

  @Transactional
  public void deleteByUserId(Long userId) {
    refreshTokenRepository.deleteByUserIdUserId(userId);
  }
}
