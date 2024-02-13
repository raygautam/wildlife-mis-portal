package in.gov.wildlife.mis.portal.lgdEntities.dtos.responseDTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DivisionResponseDTO {
        private  Integer divisionId;
        private  String divisionName;
        private Long stateCode;
        private String stateName;
}
