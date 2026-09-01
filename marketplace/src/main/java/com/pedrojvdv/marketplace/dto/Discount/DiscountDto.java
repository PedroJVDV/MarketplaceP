package com.pedrojvdv.marketplace.dto.Discount;


import com.pedrojvdv.marketplace.enums.Discount.DiscountActive;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DiscountDto {

    private BigDecimal discountValue;
    private DiscountActive discountActive;

}
