package in.gov.wildlifemisportal.credential.userDetails;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDetail_tRepository extends JpaRepository<UserDetail_t, Long> {
    boolean existsByUserName(String userName);

    boolean existsByEmailId(String emailId);

    Optional<UserDetail_t> findByUserName(String username);
}