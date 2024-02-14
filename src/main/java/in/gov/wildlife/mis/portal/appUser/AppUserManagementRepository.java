package in.gov.wildlife.mis.portal.appUser;

import in.gov.wildlife.mis.portal.domian.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppUserManagementRepository extends JpaRepository<AppUser, Long> {
    boolean existsByUserName(String userName);

//    boolean existsByEmailId(String emailId);

    Optional<AppUser> findByUserName(String username);
}