package com.pedrojvdv.marketplace.dto.Order;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class OrderUpdateDto {
    @NotNull
    @NotBlank
    private Long orderId;
    @NotNull
    @NotBlank
    private Integer quantity;
}
