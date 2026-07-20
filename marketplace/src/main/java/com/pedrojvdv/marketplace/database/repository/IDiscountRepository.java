package com.pedrojvdv.marketplace.database.repository;


import com.pedrojvdv.marketplace.database.model.DiscountEntity;
import com.pedrojvdv.marketplace.database.model.ProductEntity;
import com.pedrojvdv.marketplace.enums.DiscountActive;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface IDiscountRepository extends JpaRepository<DiscountEntity, Long> {

    List<DiscountEntity> findByDiscountValue(BigDecimal discountValue);

    List<DiscountEntity> findByDiscountActive(DiscountActive discountActive);

}
