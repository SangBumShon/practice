package com.sk.skala.walktogether.controller;

import com.sk.skala.walktogether.dto.UserSignupDTO;
import com.sk.skala.walktogether.dto.UserInfoUpdateDTO;
import com.sk.skala.walktogether.dto.UserReadDTO;
import com.sk.skala.walktogether.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Tag(name = "User API", description = "회원 관련 API")
public class UserController {

    private final UserService userService;

    @Operation(summary = "회원가입", description = "새로운 사용자를 등록합니다.")
    @PostMapping("/signup")
    public ResponseEntity<Long> signup(@RequestBody UserSignupDTO dto) {
        Long userId = userService.signup(dto);
        return ResponseEntity.ok(userId);
    }

    @Operation(summary = "회원정보 수정", description = "사용자의 닉네임, 주소, 비밀번호를 수정합니다.")
    @PutMapping("/{userId}")
    public ResponseEntity<UserReadDTO> updateUser(@PathVariable Long userId,
                                                  @RequestBody UserInfoUpdateDTO dto) {
        dto.setUserId(userId);  
        UserReadDTO updatedUser = userService.updateUserInfo(dto);
        return ResponseEntity.ok(updatedUser);
    }

    @Operation(summary = "회원정보 조회", description = "사용자의 정보를 조회합니다.")
    @GetMapping("/{userId}")
    public ResponseEntity<UserReadDTO> getUserInfo(@PathVariable Long userId) {
        UserReadDTO userInfo = userService.getUserInfo(userId);
        return ResponseEntity.ok(userInfo);
    }
}
