package in.gov.wildlife.mis.portal.credential.userDetails;
;
import in.gov.wildlife.mis.portal.credential.role.RoleRepository;
import in.gov.wildlife.mis.portal.domian.AppUser;
import in.gov.wildlife.mis.portal.domian.Role;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class AppUserServiceImple implements UserDetailServiceInter{

//    @Autowired
//    private ModelMapper modelMapper;
    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private RoleRepository roleRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public String insertAppUser(AppUserDto userDetailDto)  {

        return appUserRepository.save(ConvertAppUserDtoToAppUser(userDetailDto)).getUserName();
    }


    public AppUser ConvertAppUserDtoToAppUser(AppUserDto appUserDto)  {
        appUserDto.setPassword(passwordEncoder.encode(appUserDto.getPassword()));
        log.info("UserDetailDto object : {}",appUserDto);

        AppUser appUser = new AppUser();
        appUser.setId(appUserDto.getId());
        appUser.setUserName(appUserDto.getUserName().toUpperCase());
        appUser.setName(appUserDto.getName());
        appUser.setPassword(appUserDto.getPassword());
        // convert role IDs to a set of Role_m objects
        Set<Role> roles = new HashSet<>();
        for (Integer roleId : appUserDto.getRoleId()) {
            roles.add(roleRepository.getReferenceById(roleId));
        }
        appUser.setRoles(roles);
        appUser.setService(appUserDto.getService());
        appUser.setAccountNonLocked(Boolean.TRUE);
        appUser.setLockTime(null);
        appUser.setFailedAttempt(0);

//               userDetail_t.s

        log.info("userDetail_t object : {}",appUser);
        return appUser;
    }


    //filter stream example
//    @Override
//    public List<GetUserDetailDto> getUserDetails() {
//        List<GetUserDetailDto> getUserDetailDto = jpaStreamer.stream(UserDetail_t.class)
//                .map(userDetail_t -> modelMapper.map(userDetail_t, GetUserDetailDto.class))
//                .collect(Collectors.toList());
//
//        return getUserDetailDto.stream()
//                .filter(getUserDetailDto1 ->getUserDetailDto1.getRoleName().contentEquals("RFO") )
//                .collect(Collectors.toList());
//    }

//    ->open it
    @Override
    public List<GetAppUserDto> getUserDetails() {
//        return jpaStreamer.stream(UserDetail_t.class)
//                .map(userDetail_t -> modelMapper.map(userDetail_t, GetUserDetailDto.class))
//                .collect(Collectors.toList());

        return userDetailTRepository.findAll().stream()
                .map(this::covertToGetUserDetailDto)
                .collect(Collectors.toList());
    }

    public GetAppUserDto covertToGetUserDetailDto(AppUser userDetail_t){
        GetAppUserDto getUserDetailDto=new GetAppUserDto();
        getUserDetailDto.setUserId(userDetail_t.getUserId());
        getUserDetailDto.setRoleName(userDetail_t.getRoleId());
//        getUserDetailDto.setLevelType(userDetail_t.getLevelType());
//        getUserDetailDto.setLevelName(userDetail_t.getLevelName());
        return getUserDetailDto;
    }

    @Override
    public List<GetUserIdAndRoleNameDTO> getUserIdAndRoleName() {
//        return jpaStreamer.stream(UserDetail_t.class)
//                .map(userDetail_t -> convertToGetUserDetailDto(userDetail_t))
//                .collect(Collectors.toList());
        return userDetailTRepository.findAll().stream()
                .map(this::convertToGetUserDetailDto)
                .collect(Collectors.toList());
    }

    public GetUserIdAndRoleNameDTO convertToGetUserDetailDto(AppUser userDetail_t){
        GetUserIdAndRoleNameDTO getUserIdAndRoleNameDTO=new GetUserIdAndRoleNameDTO();
//        if(userDetail_t.getRoleM().getRoleName().contains("DFO")
//        || userDetail_t.getRoleM().getRoleName().contains("DHO")
//        || userDetail_t.getRoleM().getRoleName().contains("DVO")){
//            getUserIdAndRoleNameDTO.setLabel(userDetail_t.getLevelName());
            getUserIdAndRoleNameDTO.setValue(userDetail_t.getUserId());
//        }
        return getUserIdAndRoleNameDTO;
    }


//    @Override
//    public List<GetUserDetailDto> getUserDetails
}
