package com.pedrojvdv.marketplace.database.repository;

import com.pedrojvdv.marketplace.database.model.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface IProductRepository extends JpaRepository<ProductEntity, Long> {

    List<ProductEntity> findByName(String name);

    List<ProductEntity> findByPriceBetween(BigDecimal priceMin, BigDecimal priceMax);
    List<ProductEntity> findByPriceGreaterThan(BigDecimal price);
    List<ProductEntity> findByPriceLessThan(BigDecimal price);
    List<ProductEntity> findByPrice(BigDecimal price);

}
