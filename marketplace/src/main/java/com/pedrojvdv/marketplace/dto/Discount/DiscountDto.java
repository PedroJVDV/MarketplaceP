package com.pedrojvdv.marketplace.dto.Discount;


import com.pedrojvdv.marketplace.enums.Discount.DiscountActive;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.util.Set;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DiscountDto {

    @NotNull
    @NotBlank
    private BigDecimal discountValue;
    @NotNull
    @NotBlank
    private DiscountActive discountActive;

    @NotNull
    @NotBlank
    private Long productId;
    private Long discountId;
}
