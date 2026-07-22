package com.pedrojvdv.marketplace.dto;


import com.pedrojvdv.marketplace.enums.DiscountActive;
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
