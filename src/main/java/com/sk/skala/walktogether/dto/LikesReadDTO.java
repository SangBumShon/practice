package com.sk.skala.walktogether.dto;

import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LikesReadDTO {
    private Long likeId;
    private Long postId;
    private Long likedUserId;
    private LocalDateTime likedAt;
}
