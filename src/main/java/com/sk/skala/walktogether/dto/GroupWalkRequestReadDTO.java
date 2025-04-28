package com.sk.skala.walktogether.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GroupWalkRequestReadDTO {
    private Long requestId;
    private Long groupWalkId;
    private Long requestUserId;
}
