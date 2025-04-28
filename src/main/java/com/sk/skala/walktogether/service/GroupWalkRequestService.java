package com.sk.skala.walktogether.service;

import com.sk.skala.walktogether.dto.GroupWalkRequestCreateDTO;
import com.sk.skala.walktogether.dto.GroupWalkRequestReadDTO;
import com.sk.skala.walktogether.mapper.GroupWalkRequestMapper;
import com.sk.skala.walktogether.model.GroupWalk;
import com.sk.skala.walktogether.model.GroupWalkRequest;
import com.sk.skala.walktogether.model.User;
import com.sk.skala.walktogether.repository.GroupWalkRepository;
import com.sk.skala.walktogether.repository.GroupWalkRequestRepository;
import com.sk.skala.walktogether.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GroupWalkRequestService {

    private final GroupWalkRequestRepository groupWalkRequestRepository;
    private final GroupWalkRepository groupWalkRepository;
    private final UserRepository userRepository;

    public GroupWalkRequestService(GroupWalkRequestRepository groupWalkRequestRepository, GroupWalkRepository groupWalkRepository, UserRepository userRepository) {
        this.groupWalkRequestRepository = groupWalkRequestRepository;
        this.groupWalkRepository = groupWalkRepository;
        this.userRepository = userRepository;
    }

    // 그룹 산책 신청 생성
    public GroupWalkRequestReadDTO createGroupWalkRequest(GroupWalkRequestCreateDTO dto) {
        // 1. 신청하려는 그룹 산책 찾기
        GroupWalk groupWalk = groupWalkRepository.findById(dto.getGroupWalkId())
                .orElseThrow(() -> new IllegalArgumentException("그룹 산책을 찾을 수 없습니다."));

        // 2. 신청자(User) 찾기
        User requestUser = userRepository.findById(dto.getRequestUserId())
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        // 3. DTO → Entity 변환
        GroupWalkRequest entity = GroupWalkRequestMapper.toEntity(dto, groupWalk, requestUser);

        // 4. 저장
        GroupWalkRequest saved = groupWalkRequestRepository.save(entity);

        // 5. 반환
        return GroupWalkRequestMapper.toReadDTO(saved);
    }

    // 그룹 산책 신청 단건 조회
    @Transactional(readOnly = true)
    public GroupWalkRequestReadDTO getGroupWalkRequest(Long requestId) {
        GroupWalkRequest entity = groupWalkRequestRepository.findById(requestId)
                .orElseThrow(() -> new IllegalArgumentException("그룹 산책 신청 내역을 찾을 수 없습니다."));
        return GroupWalkRequestMapper.toReadDTO(entity);
    }

    // 그룹 산책 신청 전체 조회
    @Transactional(readOnly = true)
    public List<GroupWalkRequestReadDTO> getAllGroupWalkRequests() {
        return groupWalkRequestRepository.findAll()
                .stream()
                .map(GroupWalkRequestMapper::toReadDTO)
                .collect(Collectors.toList());
    }

    // 그룹 산책 신청 삭제 (취소)
    public void deleteGroupWalkRequest(Long requestId) {
        groupWalkRequestRepository.deleteById(requestId);
    }
}
