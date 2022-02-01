package com.loopy.domain.network.response;


import com.loopy.domain.enumclass.ItemStatus;
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
    private ItemStatus status;
    private String name;
    private String title;
    private String brandName;
    private BigDecimal price;
    private String content;
    private LocalDateTime registeredAt;
    private LocalDateTime unregisteredAt;
    private Long parterId;

}
