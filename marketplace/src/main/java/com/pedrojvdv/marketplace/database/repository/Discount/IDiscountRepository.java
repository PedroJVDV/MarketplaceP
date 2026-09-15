package com.pedrojvdv.marketplace.database.repository.Discount;

import com.pedrojvdv.marketplace.database.model.Discount.DiscountEntity;
import com.pedrojvdv.marketplace.dto.Discount.DiscountDto;
import com.pedrojvdv.marketplace.enums.Discount.DiscountActive;
import org.jspecify.annotations.NullMarked;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface IDiscountRepository extends JpaRepository<DiscountEntity, Long> {

    @NullMarked
    Optional<DiscountEntity> findById(Long id);

    //TODO: NEW STRUCTURE ABOUT QUERY PARAMS... AT THIS TIME THAT ARE NOT GOOD!

    @Query("""
            SELECT d.discountValue AS discountValue, d.discountActive AS discountActive
            FROM DiscountEntity d
            WHERE d.discountValue = :discountValue
            """)
    List<DiscountSelectionProjection> getByDiscountValue(BigDecimal discountValue);

    @Query("""
            SELECT d.discountValue AS discountValue, d.discountActive AS discountActive
            FROM DiscountEntity d
            """)
    List<DiscountSelectionProjection> findAllDiscounts();

    @Query("""
            SELECT d.discountValue AS discountValue, d.discountActive AS discountActive
            FROM DiscountEntity d
            WHERE d.discountActive = DiscountActive.YES
            """)
    List<DiscountSelectionProjection> getByActiveDiscount(DiscountActive discountActive);

    @Query("""
            SELECT d.discountValue AS discountValue, d.discountActive AS discountActive
            FROM DiscountEntity d
            WHERE d.discountActive = DiscountActive.NO
            """)
    List<DiscountSelectionProjection> getByInativeDiscount(DiscountActive discountActive);
}
