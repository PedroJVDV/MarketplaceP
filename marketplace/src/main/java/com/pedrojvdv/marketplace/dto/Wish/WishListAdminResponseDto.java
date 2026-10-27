package com.pedrojvdv.marketplace.dto.Wish;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WishListAdminResponseDto {

    @NotNull
    @NotBlank
    private Long productId;
    @NotNull
    private Long userId;
    @NotNull
    private Long id;
    @NotNull
    @NotBlank
    private String productName;
    @NotNull
    @NotBlank
    private BigDecimal productPrice;

    @NotNull
    @NotBlank
    private String wishListGroupName;

}
