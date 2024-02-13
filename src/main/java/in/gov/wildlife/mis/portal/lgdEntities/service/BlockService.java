package in.gov.wildlife.mis.portal.lgdEntities.service;


import com.forest.wildlife.lgdEntities.entities.Block;
import com.forest.wildlife.lgdEntities.entities.District;
import com.forest.wildlife.lgdEntities.repository.BlockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlockService {

    @Autowired
    BlockRepository blockRepository;

    public List<Block> getAllBlocks(){
        List<Block> blocksList = blockRepository.findAll();
        return  blocksList;
    }

    public List<Block> geAllBlocksWithDistrict(District district){
        List<Block> blocksList = blockRepository.findAllByDistrict(district);
        return blocksList;
    }
}
