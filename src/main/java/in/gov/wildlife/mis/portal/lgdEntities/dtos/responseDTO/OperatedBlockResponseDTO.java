package in.gov.wildlife.mis.portal.lgdEntities.dtos.responseDTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OperatedBlockResponseDTO {
    private Integer operatedBlockId;
    private String operatedBlockName;
    private DistrictResponseDTO district;
    private RangeResponseDTO range;
}
