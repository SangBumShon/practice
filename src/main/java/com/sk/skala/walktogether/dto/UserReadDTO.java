package com.sk.skala.walktogether.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserReadDTO {
    private Long userId;
    private String userEmail;
    private String userNickname;
    private String userGender;
    private int userAge;
    private String userAddress;
}
