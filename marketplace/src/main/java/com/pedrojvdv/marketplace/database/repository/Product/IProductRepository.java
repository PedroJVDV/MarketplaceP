package com.pedrojvdv.marketplace.database.repository.Product;

import com.pedrojvdv.marketplace.database.model.Product.ProductEntity;
import org.jspecify.annotations.NullMarked;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;

public interface IProductRepository extends JpaRepository<ProductEntity, Long> {

    List<ProductEntity> findByName(String name);

    @NullMarked
    List<ProductEntity> findAll();

    List<ProductEntity> findByPriceBetween(BigDecimal priceMin, BigDecimal priceMax);

    List<ProductEntity> findByPriceGreaterThan(BigDecimal price);

    List<ProductEntity> findByPriceLessThan(BigDecimal price);

    List<ProductEntity> findByPrice(BigDecimal price);

    List<ProductEntity> findByQuantity(Integer quantity);

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
