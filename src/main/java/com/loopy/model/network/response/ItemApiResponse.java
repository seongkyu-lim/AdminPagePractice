package com.loopy.model.network.response;


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
public class ItemApiResponse {

    private Long id;
    private String status;
    private String name;
    private String title;
    private String brandName;
    private BigDecimal price;
    private String content;
    private LocalDateTime registeredAt;
    private LocalDateTime unregisteredAt;
}