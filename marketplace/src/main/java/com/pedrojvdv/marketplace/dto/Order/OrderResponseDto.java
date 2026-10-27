package com.pedrojvdv.marketplace.dto.Order;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponseDto {

    @NotNull
    @NotBlank
    private Integer quantity;
    @NotNull
    @NotBlank
    private LocalDateTime orderDate;
    @NotNull
    @NotBlank
    private String productName;

    private BigDecimal discountValue;
}
