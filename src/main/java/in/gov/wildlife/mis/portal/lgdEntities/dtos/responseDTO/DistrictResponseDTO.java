package in.gov.wildlife.mis.portal.lgdEntities.dtos.responseDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DistrictResponseDTO {
    private Long districtCode;
    private String districtName;
    private Long stateCode;
//    private String stateName;
    private List<RangeResponseDTO> ranges;
}