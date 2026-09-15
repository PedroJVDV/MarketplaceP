package com.pedrojvdv.marketplace.database.repository.Discount;

import com.pedrojvdv.marketplace.enums.Discount.DiscountActive;

import java.math.BigDecimal;

public interface DiscountSelectionProjection {

    BigDecimal getDiscountValue();
    DiscountActive getDiscountActive();
}
