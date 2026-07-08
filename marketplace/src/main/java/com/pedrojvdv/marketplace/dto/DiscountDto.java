package com.pedrojvdv.marketplace.dto;


import com.pedrojvdv.marketplace.enums.DiscountActive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DiscountDto {

    private BigDecimal discountValue;
    private DiscountActive discountActive;

}
