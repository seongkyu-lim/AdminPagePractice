package com.loopy.domain.network.response;

//사용자 주문 정보 조회 response.

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserOrderInfoApiResponse  {

    private UserApiResponse userApiResponse;

}
