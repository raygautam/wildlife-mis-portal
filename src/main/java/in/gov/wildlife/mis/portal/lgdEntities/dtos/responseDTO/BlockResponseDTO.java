package in.gov.wildlife.mis.portal.lgdEntities.dtos.responseDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BlockResponseDTO {
    private Long blockCode;
    private String blockName;
    private Long districtCode;
}
