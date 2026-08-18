package com.pedrojvdv.marketplace.dto.Order;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {

    private Integer quantity;
    private LocalDateTime orderTime;

    private Long userId;
    private Long productId;
}
