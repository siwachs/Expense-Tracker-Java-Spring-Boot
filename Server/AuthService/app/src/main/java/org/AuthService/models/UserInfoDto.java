package org.AuthService.models;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import org.AuthService.entities.UserInfo;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UserInfoDto extends UserInfo {
    private String userName;
    private String lastName;
    private String phoneNumber;
    private String email;
}
