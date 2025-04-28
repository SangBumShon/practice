package com.sk.skala.walktogether.service;

import com.sk.skala.walktogether.dto.UserSignupDTO;
import com.sk.skala.walktogether.dto.UserInfoUpdateDTO;
import com.sk.skala.walktogether.dto.UserReadDTO;
import com.sk.skala.walktogether.mapper.UserMapper;
import com.sk.skala.walktogether.model.User;
import com.sk.skala.walktogether.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // 회원가입
    public Long signup(UserSignupDTO dto) {
        // 1. DTO → Entity 변환
        User entity = UserMapper.toEntity(dto);

        // 2. 저장
        User saved = userRepository.save(entity);

        // 3. 생성된 User ID 반환
        return saved.getUserId();
    }

    // 회원정보 수정
    public UserReadDTO updateUserInfo(UserInfoUpdateDTO dto) {
        // 1. 사용자 찾기
        User entity = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        // 2. 수정 적용
        UserMapper.updateEntity(entity, dto);
        // JPA dirty checking으로 자동 반영

        // 3. 수정 후 DTO 반환
        return UserMapper.toReadDTO(entity);
    }

    // 회원정보 조회
    @Transactional(readOnly = true)
    public UserReadDTO getUserInfo(Long userId) {
        User entity = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));
        return UserMapper.toReadDTO(entity);
    }
}
