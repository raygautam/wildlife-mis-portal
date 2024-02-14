package in.gov.wildlife.mis.portal.role;

//import com.speedment.jpastreamer.application.JPAStreamer;

import in.gov.wildlife.mis.portal.domian.Role;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
@RequestMapping("/admin")
@RestController
public class RoleController {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private RoleRepository roleMRepository;

//    @Autowired
//    private JPAStreamer jpaStreamer;

//    @PostMapping("/insertRole")
//    public ResponseEntity<?> insertRole(@RequestBody Role_mDto roleMDto){
//        roleMDto.setRoleName(roleMDto.getRoleName().toUpperCase());
//        return new ResponseEntity<>(roleMRepository.save(modelMapper.map(roleMDto, Role_m.class)), HttpStatus.CREATED);
//    }

    @PostMapping("/insertRole")
    public ResponseEntity<?> insertRole(@RequestBody List<RoleDto> roleMDto) {

        if (roleMDto.size() > 1) {
            // Batch save using saveAll() method
            List<Role> roles = roleMDto.stream().map((roleDto) -> modelMapper.map(roleDto, Role.class)).collect(Collectors.toList());
            List<Role> savedRoles = roleMRepository.saveAll(roles);
            return new ResponseEntity<>(savedRoles, HttpStatus.CREATED);
        } else if (roleMDto.size() == 1) {
            // Single save using save() method
            Role role = modelMapper.map(roleMDto.get(0), Role.class);
            Role savedRole = roleMRepository.save(role);
            return new ResponseEntity<>(savedRole, HttpStatus.CREATED);
        } else {
            // Return an error response for empty input
            throw new IllegalArgumentException("No roles found in the request.");
        }
    }

    @GetMapping("/getRoles")
    public ResponseEntity<?> getRoles(){
        List<RoleDto> roleMDto=roleMRepository.findAll()
                .stream()
                .map(role_m -> modelMapper.map(role_m, RoleDto.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(roleMDto, HttpStatus.OK);
    }
}
