package in.gov.wildlife.mis.portal.role;

import in.gov.wildlife.mis.portal.domian.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Set<Role> findRoleIdByRoleName(String roleSuperAdmin);
}