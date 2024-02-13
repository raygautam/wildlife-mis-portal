package in.gov.wildlife.mis.portal.lgdEntities.dtos.responseDTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RangeResponseDTO {
        private  Integer rangeId;
        private  String rangeName;
//        private  Integer divisionId;
//        private  String divisionName;
//        private List<DistrictResponseDTO> districtResponseDTOS;
}
