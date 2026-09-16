package com.pedrojvdv.marketplace.dto.Discount;


import com.pedrojvdv.marketplace.enums.Discount.DiscountActive;
import lombok.*;

import java.math.BigDecimal;
import java.util.Set;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DiscountDto {

    private BigDecimal discountValue;
    private DiscountActive discountActive;

    private Long productId;
    private Long discountId;
}
