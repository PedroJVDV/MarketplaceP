package com.pedrojvdv.marketplace.dto.Wish;

import com.pedrojvdv.marketplace.database.model.Product.ProductEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WishListResponseDto {

    @NotNull
    @NotBlank
    private UUID productCode;

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
