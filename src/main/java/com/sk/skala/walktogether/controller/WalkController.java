package com.sk.skala.walktogether.controller;

import com.sk.skala.walktogether.dto.*;
import com.sk.skala.walktogether.service.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/walks")
@RequiredArgsConstructor
@Tag(name = "Walk API", description = "산책 관련 전체 기능 API")
public class WalkController {

    private final WalkService walkService;
    private final WalkRouteService walkRouteService;
    private final GroupWalkService groupWalkService;
    private final GroupWalkRequestService groupWalkRequestService;
    private final SimpleReviewService simpleReviewService;
    private final LikesService likesService;


    @Operation(summary = "산책 기록 생성", description = "새로운 산책 기록을 생성합니다.")
    @PostMapping("/records")
    public ResponseEntity<WalkReadDTO> createWalk(@RequestBody WalkCreateDTO dto) {
        return ResponseEntity.ok(walkService.createWalk(dto));
    }

    @Operation(summary = "산책 기록 조회", description = "산책 기록 ID로 산책 정보를 조회합니다.")
    @GetMapping("/records/{walkId}")
    public ResponseEntity<WalkReadDTO> getWalk(@PathVariable Long walkId) {
        return ResponseEntity.ok(walkService.getWalk(walkId));
    }

    @Operation(summary = "산책 기록 전체 조회", description = "모든 산책 기록을 조회합니다.")
    @GetMapping("/records")
    public ResponseEntity<List<WalkReadDTO>> getAllWalks() {
        return ResponseEntity.ok(walkService.getAllWalks());
    }


    @Operation(summary = "산책로 생성", description = "새로운 산책로를 등록합니다.")
    @PostMapping("/routes")
    public ResponseEntity<WalkRouteReadDTO> createWalkRoute(@RequestBody WalkRouteCreateDTO dto) {
        return ResponseEntity.ok(walkRouteService.createWalkRoute(dto));
    }

    @Operation(summary = "산책로 수정", description = "기존 산책로의 정보를 수정합니다.")
    @PutMapping("/routes/{routeId}")
    public ResponseEntity<WalkRouteReadDTO> updateWalkRoute(@PathVariable Long routeId,
                                                            @RequestBody WalkRouteUpdateDTO dto) {
        dto.setRouteId(routeId);
        return ResponseEntity.ok(walkRouteService.updateWalkRoute(dto));
    }

    @Operation(summary = "산책로 조회", description = "산책로 ID로 상세 정보를 조회합니다.")
    @GetMapping("/routes/{routeId}")
    public ResponseEntity<WalkRouteReadDTO> getWalkRoute(@PathVariable Long routeId) {
        return ResponseEntity.ok(walkRouteService.getWalkRoute(routeId));
    }

    @Operation(summary = "산책로 전체 조회", description = "모든 산책로를 조회합니다.")
    @GetMapping("/routes")
    public ResponseEntity<List<WalkRouteReadDTO>> getAllWalkRoutes() {
        return ResponseEntity.ok(walkRouteService.getAllWalkRoutes());
    }

    @Operation(summary = "산책로 삭제", description = "산책로를 삭제합니다.")
    @DeleteMapping("/routes/{routeId}")
    public ResponseEntity<Void> deleteWalkRoute(@PathVariable Long routeId) {
        walkRouteService.deleteWalkRoute(routeId);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "그룹 산책 생성", description = "새로운 그룹 산책을 등록합니다.")
    @PostMapping("/group")
    public ResponseEntity<GroupWalkReadDTO> createGroupWalk(@RequestBody GroupWalkCreateDTO dto) {
        return ResponseEntity.ok(groupWalkService.createGroupWalk(dto));
    }

    @Operation(summary = "그룹 산책 수정", description = "그룹 산책 정보를 수정합니다.")
    @PutMapping("/group/{walkGroupId}")
    public ResponseEntity<GroupWalkReadDTO> updateGroupWalk(@PathVariable Long walkGroupId,
                                                            @RequestBody GroupWalkUpdateDTO dto) {
        dto.setWalkGroupId(walkGroupId);
        return ResponseEntity.ok(groupWalkService.updateGroupWalk(dto));
    }

    @Operation(summary = "그룹 산책 단건 조회", description = "그룹 산책 ID로 상세 정보를 조회합니다.")
    @GetMapping("/group/{walkGroupId}")
    public ResponseEntity<GroupWalkReadDTO> getGroupWalk(@PathVariable Long walkGroupId) {
        return ResponseEntity.ok(groupWalkService.getGroupWalk(walkGroupId));
    }

    @Operation(summary = "그룹 산책 전체 조회", description = "모든 그룹 산책을 조회합니다.")
    @GetMapping("/group")
    public ResponseEntity<List<GroupWalkReadDTO>> getAllGroupWalks() {
        return ResponseEntity.ok(groupWalkService.getAllGroupWalks());
    }

    @Operation(summary = "그룹 산책 삭제", description = "그룹 산책을 삭제합니다.")
    @DeleteMapping("/group/{walkGroupId}")
    public ResponseEntity<Void> deleteGroupWalk(@PathVariable Long walkGroupId) {
        groupWalkService.deleteGroupWalk(walkGroupId);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "그룹 산책 신청", description = "그룹 산책에 참가 신청을 합니다.")
    @PostMapping("/group-requests")
    public ResponseEntity<GroupWalkRequestReadDTO> createGroupWalkRequest(@RequestBody GroupWalkRequestCreateDTO dto) {
        return ResponseEntity.ok(groupWalkRequestService.createGroupWalkRequest(dto));
    }

    @Operation(summary = "그룹 산책 신청 단건 조회", description = "신청 ID로 신청 정보를 조회합니다.")
    @GetMapping("/group-requests/{requestId}")
    public ResponseEntity<GroupWalkRequestReadDTO> getGroupWalkRequest(@PathVariable Long requestId) {
        return ResponseEntity.ok(groupWalkRequestService.getGroupWalkRequest(requestId));
    }

    @Operation(summary = "그룹 산책 신청 전체 조회", description = "모든 그룹 산책 신청을 조회합니다.")
    @GetMapping("/group-requests")
    public ResponseEntity<List<GroupWalkRequestReadDTO>> getAllGroupWalkRequests() {
        return ResponseEntity.ok(groupWalkRequestService.getAllGroupWalkRequests());
    }

    @Operation(summary = "그룹 산책 신청 취소", description = "그룹 산책 신청을 취소합니다.")
    @DeleteMapping("/group-requests/{requestId}")
    public ResponseEntity<Void> deleteGroupWalkRequest(@PathVariable Long requestId) {
        groupWalkRequestService.deleteGroupWalkRequest(requestId);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "후기 작성", description = "산책 후기(간단 리뷰)를 작성합니다.")
    @PostMapping("/reviews")
    public ResponseEntity<SimpleReviewReadDTO> createSimpleReview(@RequestBody SimpleReviewCreateDTO dto) {
        return ResponseEntity.ok(simpleReviewService.createSimpleReview(dto));
    }

    @Operation(summary = "후기 수정", description = "작성한 후기를 수정합니다.")
    @PutMapping("/reviews/{walkReviewId}")
    public ResponseEntity<SimpleReviewReadDTO> updateSimpleReview(@PathVariable Long walkReviewId,
                                                                  @RequestBody SimpleReviewUpdateDTO dto) {
        dto.setWalkReviewId(walkReviewId);
        return ResponseEntity.ok(simpleReviewService.updateSimpleReview(dto));
    }

    @Operation(summary = "후기 단건 조회", description = "후기 ID로 상세 정보를 조회합니다.")
    @GetMapping("/reviews/{walkReviewId}")
    public ResponseEntity<SimpleReviewReadDTO> getSimpleReview(@PathVariable Long walkReviewId) {
        return ResponseEntity.ok(simpleReviewService.getSimpleReview(walkReviewId));
    }

    @Operation(summary = "후기 전체 조회", description = "모든 산책 후기를 조회합니다.")
    @GetMapping("/reviews")
    public ResponseEntity<List<SimpleReviewReadDTO>> getAllSimpleReviews() {
        return ResponseEntity.ok(simpleReviewService.getAllSimpleReviews());
    }

    @Operation(summary = "후기 삭제", description = "작성한 후기를 삭제합니다.")
    @DeleteMapping("/reviews/{walkReviewId}")
    public ResponseEntity<Void> deleteSimpleReview(@PathVariable Long walkReviewId) {
        simpleReviewService.deleteSimpleReview(walkReviewId);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "좋아요 등록", description = "후기에 좋아요를 누릅니다.")
    @PostMapping("/likes")
    public ResponseEntity<LikesReadDTO> createLike(@RequestBody LikesCreateDTO dto) {
        return ResponseEntity.ok(likesService.createLike(dto));
    }

    @Operation(summary = "좋아요 단건 조회", description = "좋아요 ID로 상세 정보를 조회합니다.")
    @GetMapping("/likes/{likeId}")
    public ResponseEntity<LikesReadDTO> getLike(@PathVariable Long likeId) {
        return ResponseEntity.ok(likesService.getLike(likeId));
    }

    @Operation(summary = "좋아요 전체 조회", description = "모든 좋아요를 조회합니다.")
    @GetMapping("/likes")
    public ResponseEntity<List<LikesReadDTO>> getAllLikes() {
        return ResponseEntity.ok(likesService.getAllLikes());
    }

    @Operation(summary = "좋아요 취소", description = "좋아요를 취소합니다.")
    @DeleteMapping("/likes/{likeId}")
    public ResponseEntity<Void> deleteLike(@PathVariable Long likeId) {
        likesService.deleteLike(likeId);
        return ResponseEntity.noContent().build();
    }
}
