package com.sk.skala.walktogether.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoUpdateDTO {
    private Long userId;            // 수정할 사용자 ID
    private String userNickname;    // 닉네임 변경
    private String userAddress;     // 주소 변경
    private String userPassword;    // 비밀번호 변경 (옵션)
}
