package com.loopy.domain.network;

import lombok.*;
import lombok.experimental.Accessors;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain=true)
public class Pagination {

    private Integer totalPages;
    private Long totalElements;
    private Integer currentPage;
    private Integer currentElements;
}
