package com.sk.skala.walktogether.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserSignupDTO {
    private String userEmail;
    private String userNickname;
    private String userGender;
    private String userPassword;
    private int userAge;
    private String userAddress;
}
