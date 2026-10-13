package com.pedrojvdv.marketplace.dto.Order;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {

    @NotNull
    @NotBlank
    private Long orderId;
    private Integer quantity;
    @NotNull
    @NotBlank
    private LocalDateTime orderTime;

    @NotNull
    @NotBlank
    private String usernameLogin;
    @NotNull
    @NotBlank
    private Long userId;
    @NotNull
    @NotBlank
    private Long productId;
    private Long discountId;
}
