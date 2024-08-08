package com.plog.server.post.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class LikeResponse {
    private Long postId;
    private String title;
    private LocalDate time;
    private String userNickname;

    public LikeResponse(Long postId, String title, LocalDate time, String userNickname) {
        this.postId = postId;
        this.title = title;
        this.time = time;
        this.userNickname = userNickname;
    }
}
