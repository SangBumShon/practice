package com.sk.skala.walktogether.mapper;

import com.sk.skala.walktogether.dto.UserSignupDTO;
import com.sk.skala.walktogether.dto.UserInfoUpdateDTO;
import com.sk.skala.walktogether.dto.UserReadDTO;
import com.sk.skala.walktogether.model.User;

public class UserMapper {

    // SignupDTO → Entity 변환 (회원가입용)
    public static User toEntity(UserSignupDTO dto) {
        if (dto == null) return null;

        return User.builder()
                .userEmail(dto.getUserEmail())
                .userPassword(dto.getUserPassword())
                .userNickname(dto.getUserNickname())
                .userGender(dto.getUserGender())
                .userAge(dto.getUserAge())
                .userAddress(dto.getUserAddress())
                .build();
    }

    // Entity → ReadDTO 변환 (회원 조회용)
    public static UserReadDTO toReadDTO(User entity) {
        if (entity == null) return null;

        return UserReadDTO.builder()
                .userId(entity.getUserId())
                .userEmail(entity.getUserEmail())
                .userNickname(entity.getUserNickname())
                .userGender(entity.getUserGender())
                .userAge(entity.getUserAge())
                .userAddress(entity.getUserAddress())
                .build();
    }

    // UpdateDTO 적용 (회원정보 수정용)
    public static void updateEntity(User entity, UserInfoUpdateDTO dto) {
        if (entity == null || dto == null) return;

        if (dto.getUserNickname() != null) {
            entity.setUserNickname(dto.getUserNickname());
        }
        if (dto.getUserAddress() != null) {
            entity.setUserAddress(dto.getUserAddress());
        }
        if (dto.getUserPassword() != null) {
            entity.setUserPassword(dto.getUserPassword());
        }
    }
}
