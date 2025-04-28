package com.sk.skala.walktogether.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GroupWalkRequestCreateDTO {
    private Long groupWalkId;
    private Long requestUserId;
}
