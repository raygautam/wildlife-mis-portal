package in.gov.wildlife.mis.portal.lgdEntities.dtos.responseDTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SubDistrictResponseDTO {
    private Long subDistrictCode;
    private String subDistrictName;
    private Long district;
    private Long block;
}
