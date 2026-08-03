package com.pedrojvdv.marketplace.database.repository;

import com.pedrojvdv.marketplace.database.model.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface IProductRepository extends JpaRepository<ProductEntity, Long> {

    Optional<ProductEntity> findByName(String name);

    List<ProductEntity> getAllProduct();

    List<ProductEntity> findByPriceBetween(BigDecimal priceMin, BigDecimal priceMax);

    List<ProductEntity> findByPriceGreaterThan(BigDecimal price);

    List<ProductEntity> findByPriceLessThan(BigDecimal price);

    List<ProductEntity> findByPrice(BigDecimal price);

    List<ProductEntity> getByQuantity(Integer quantity);

    @Query(value = """
                    SELECT p FROM ProductEntity p JOIN FETCH DiscountEntity d
                    WHERE d.discountValue > 0
                    AND d.discountActive = DiscountActive.YES
            """)
    List<ProductEntity> getProductWithDiscount();

    @Query(value = """
                    SELECT p FROM ProductEntity p JOIN FETCH DiscountEntity d
                    WHERE d.discountValue <= 0
                    AND d.discountActive = DiscountActive.NO
            """)
    List<ProductEntity> getProductWithoutDiscount();
}
