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

    public Long signup(UserSignupDTO dto) {
        User entity = UserMapper.toEntity(dto);

        User saved = userRepository.save(entity);

        return saved.getUserId();
    }

    public UserReadDTO updateUserInfo(UserInfoUpdateDTO dto) {
        User entity = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        UserMapper.updateEntity(entity, dto);

        return UserMapper.toReadDTO(entity);
    }

    @Transactional(readOnly = true)
    public UserReadDTO getUserInfo(Long userId) {
        User entity = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));
        return UserMapper.toReadDTO(entity);
    }
}
