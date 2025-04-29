package com.sk.skala.walktogether.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoUpdateDTO {
    private Long userId;           
    private String userNickname;    
    private String userAddress;     
    private String userPassword;    
}
