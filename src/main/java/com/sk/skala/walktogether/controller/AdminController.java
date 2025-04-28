package com.sk.skala.walktogether.controller;

import com.sk.skala.walktogether.dto.CategoryKeywordsCreateDTO;
import com.sk.skala.walktogether.dto.CategoryKeywordsReadDTO;
import com.sk.skala.walktogether.service.AdminService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@Tag(name = "Admin API", description = "관리자 전용 기능 API 모음")
public class AdminController {

    private final AdminService adminService;
    public AdminController(AdminService adminService) {this.adminService = adminService;}
    @Operation(summary = "후기 삭제", description = "후기 ID를 입력받아 해당 후기를 삭제합니다.")
    @DeleteMapping("/reviews/{walkReviewId}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long walkReviewId) {
        adminService.deleteReview(walkReviewId);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "카테고리 키워드 추가", description = "새로운 카테고리 키워드를 추가합니다.")
    @PostMapping("/category-keywords")
    public ResponseEntity<Void> addCategoryKeyword(@RequestBody CategoryKeywordsCreateDTO dto) {
        adminService.addCategoryKeyword(dto);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "카테고리 키워드 전체 조회", description = "등록된 모든 카테고리 키워드를 조회합니다.")
    @GetMapping("/category-keywords")
    public ResponseEntity<List<CategoryKeywordsReadDTO>> getAllCategoryKeywords() {
        List<CategoryKeywordsReadDTO> result = adminService.getAllCategoryKeywords();
        return ResponseEntity.ok(result);
    }

    @Operation(summary = "인기 산책로 등록", description = "이용자 10명 이상인 산책로를 인기 산책로로 등록합니다.")
    @PostMapping("/popular-routes/{routeId}")
    public ResponseEntity<Void> registerPopularRoute(@PathVariable Long routeId) {
        adminService.registerPopularRoute(routeId);
        return ResponseEntity.ok().build();
    }
}
