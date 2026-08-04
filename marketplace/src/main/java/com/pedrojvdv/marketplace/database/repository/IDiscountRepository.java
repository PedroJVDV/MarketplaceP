package com.pedrojvdv.marketplace.database.repository;

import com.pedrojvdv.marketplace.database.model.DiscountEntity;
import com.pedrojvdv.marketplace.dto.DiscountDto;
import com.pedrojvdv.marketplace.enums.DiscountActive;
import org.jspecify.annotations.NullMarked;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface IDiscountRepository extends JpaRepository<DiscountEntity, Long> {

    @NullMarked
    Optional<DiscountEntity> findById(Long id);

    @Query("""
            SELECT NEW com.pedrojvdv.marketplace.dto.DiscountDto(d.discountValue, d.discountActive)
            FROM DiscountEntity d
            WHERE d.discountValue = :discount_value
            """)
    List<DiscountDto> getByDiscountValue(BigDecimal discountValue);

    @Query("""
            SELECT NEW com.pedrojvdv.marketplace.dto.DiscountDto(d.discountValue, d.discountActive)
            FROM DiscountEntity d
            """)
    List<DiscountDto> getAllDiscounts();

    @Query("""
            SELECT NEW com.pedrojvdv.marketplace.dto.DiscountDto(d.discountValue, d.discountActive)
            FROM DiscountEntity d
            WHERE d.discountActive = DiscountActive.YES
            """)
    List<DiscountDto> getByActiveDiscount(DiscountActive discountActive);

    @Query("""
            SELECT NEW com.pedrojvdv.marketplace.dto.DiscountDto(d.discountValue, d.discountActive)
            FROM DiscountEntity d
            WHERE d.discountActive = DiscountActive.NO
            """)
    List<DiscountDto> getByInativeDiscount(DiscountActive discountActive);
}
