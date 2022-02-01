package com.loopy.domain.enumclass;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ItemStatus {

    REGISTERED(0, "등록", "상품 등록 상태"),
    UNREGISTERED(1, "등록", "상품 해지 상태"),
    WAITING(2, "검수(대기)", "상품 검수 상태")
    ;

    private Integer id;
    private String title;
    private String description;

}
