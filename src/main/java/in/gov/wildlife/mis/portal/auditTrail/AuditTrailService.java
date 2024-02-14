package in.gov.wildlife.mis.portal.auditTrail;

import in.gov.wildlife.mis.portal.exception.DataRetrievalException;
import in.gov.wildlife.mis.portal.exception.Error;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AuditTrailService {
    @Autowired
    private AuditTrailRepository auditTrailRepository;

    public ResponseEntity<?> getAuditTrailData() {
        try{
            return ResponseEntity.ok(auditTrailRepository.findAll());
        }catch (DataAccessException e){
            Error error=new Error(e.getMessage());
            throw new DataRetrievalException("Failed to fetch audit trail records.", error);
        }
    }
}
