package com.loopy.domain.network.request;

import com.loopy.domain.enumclass.OrderGroupOrderType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderGroupApiRequest {

    private Long id;
    private String status;
    private OrderGroupOrderType orderType;
    private String revAddress;
    private String revName;
    private String paymentType;
    private BigDecimal totalPrice;
    private Long totalQuantity;
    private LocalDateTime orderAt;
    private LocalDateTime arrivalDate;
    private Long userId;

}
