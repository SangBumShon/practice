package com.sk.skala.walktogether.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LikesCreateDTO {
    private Long postId;
    private Long likedUserId;
}
