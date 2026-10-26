package com.pedrojvdv.marketplace.dto.Product;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
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
@NotNull
@NotBlank
public class ProductDto {

    @NotNull
    private Long id;
    private Long discountId;
    @NotBlank
    @NotNull
    private String name;
    @NotBlank
    @NotNull
    private BigDecimal price;
    private Integer quantity;

}
