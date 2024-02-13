package in.gov.wildlifemisportal.credential.userDetails;

import com.forest.wildlife.credential.role.Role_m;
import com.forest.wildlife.credential.role.Role_mRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserDetailServiceImple implements UserDetailServiceInter{

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserDetail_tRepository userDetailTRepository;
    @Autowired
    private Role_mRepository role_mRepository;

    private final BCryptPasswordEncoder passwordEncoder;
//    @Autowired
//BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();


    @Override
    public String insertUserDetail(UserDetailDto userDetailDto)  {

//        ArrayList<>
        // create ObjectMapper instance and configure it to ignore Hibernate proxies

//        ObjectMapper objectMapper = new ObjectMapper(); throws JsonProcessingException
//        objectMapper.registerModule(new Hibernate5Module());
//
//        // convert UserDetail_t object to JSON string
//        String jsonString = objectMapper.writeValueAsString(userDetailTRepository.save(ConvertUserDetailDtoToUserDetail_t(userDetailDto)));
//        return jsonString;
        return userDetailTRepository.save(ConvertUserDetailDtoToUserDetail_t(userDetailDto)).getUserName();
    }

    //convert json to Particular entity
//    public <T> T getJson(String practiceClass, Class<T> clazz) {
//        T userJson = null;
//
//        try {
//            ObjectMapper objectMapper = new ObjectMapper();
//            userJson = objectMapper.readValue(practiceClass, clazz);
//        } catch (IOException err) {
//            System.out.printf("Error", err.toString());
//        }
//
//        return userJson;
//    }


    public UserDetail_t ConvertUserDetailDtoToUserDetail_t(UserDetailDto userDetailDto)  {
        userDetailDto.setPassword(passwordEncoder.encode(userDetailDto.getPassword()));
        log.info("UserDetailDto object : {}",userDetailDto);

        UserDetail_t userDetail_t = new UserDetail_t();
        userDetail_t.setUserId(userDetailDto.getUserId());
        userDetail_t.setUserName(userDetailDto.getUserName().toUpperCase());
        userDetail_t.setEmailId(userDetailDto.getEmailId());
        userDetail_t.setPassword(userDetailDto.getPassword());

        // convert role IDs to a set of Role_m objects
                Set<Role_m> roles = new HashSet<>();
                for (Integer roleId : userDetailDto.getRoleId()) {
                    roles.add(role_mRepository.getReferenceById(roleId));
                }
                userDetail_t.setRoleId(roles);

//               userDetail_t.s

        log.info("userDetail_t object : {}",userDetail_t);
        return userDetail_t;
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
    public List<GetUserDetailDto> getUserDetails() {
//        return jpaStreamer.stream(UserDetail_t.class)
//                .map(userDetail_t -> modelMapper.map(userDetail_t, GetUserDetailDto.class))
//                .collect(Collectors.toList());

        return userDetailTRepository.findAll().stream()
                .map(this::covertToGetUserDetailDto)
                .collect(Collectors.toList());
    }

    public GetUserDetailDto covertToGetUserDetailDto(UserDetail_t userDetail_t){
        GetUserDetailDto getUserDetailDto=new GetUserDetailDto();
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

    public GetUserIdAndRoleNameDTO convertToGetUserDetailDto(UserDetail_t userDetail_t){
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
