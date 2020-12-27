package com.project.timeline.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
public class RestPostsTemplate {

    private Integer pageNumber;

    private Integer totalCount;

    private boolean hasMore = false;

    private Integer pageCount = 0;

    private List posts;


}
