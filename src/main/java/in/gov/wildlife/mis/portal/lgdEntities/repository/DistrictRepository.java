package in.gov.wildlife.mis.portal.lgdEntities.repository;

import in.gov.wildlife.mis.portal.lgdEntities.dtos.responseDTO.NumberOfDistrictDTO;
import in.gov.wildlife.mis.portal.lgdEntities.entities.State;
import in.gov.wildlife.mis.portal.lgdEntities.entities.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;


@Repository
public interface DistrictRepository extends JpaRepository<District,Long> {


    Collection<District> findAllByState(State referenceById);

//    @Query("SELECT New com.forest.wildlife.lgdEntities.dtos.responseDTO.NumberOfDistrictDTO(COUNT(s.stateCode), d.districtCode, d.districtName) FROM District d JOIN d.state s" +
//            "GROUP BY s.stateCode")
    @Query("SELECT NEW com.forest.wildlife.lgdEntities.dtos.responseDTO.NumberOfDistrictDTO(COUNT(d.state), d.districtCode, d.districtName) FROM District d JOIN d.state s " +
            "GROUP BY d.state")
    List<NumberOfDistrictDTO> findTotalNumberOfDistrict();
}
