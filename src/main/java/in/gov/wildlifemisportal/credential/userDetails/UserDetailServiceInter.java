package in.gov.wildlifemisportal.credential.userDetails;


import java.util.List;

public interface UserDetailServiceInter {
    String insertUserDetail(UserDetailDto userDetailDto);

    List<GetUserDetailDto> getUserDetails();

    List<GetUserIdAndRoleNameDTO> getUserIdAndRoleName();
}
