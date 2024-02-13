package in.gov.wildlife.mis.portal.lgdEntities.repository;

import in.gov.wildlife.mis.portal.lgdEntities.entities.Division;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DivisionRepository extends JpaRepository<Division, Integer> {
}