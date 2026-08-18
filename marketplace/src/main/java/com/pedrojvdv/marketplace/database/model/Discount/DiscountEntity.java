package com.pedrojvdv.marketplace.database.model.Discount;

import com.pedrojvdv.marketplace.database.model.Order.OrderEntity;
import com.pedrojvdv.marketplace.database.model.Product.ProductEntity;
import com.pedrojvdv.marketplace.database.model.Sale.SaleEntity;
import com.pedrojvdv.marketplace.enums.Discount.DiscountActive;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "discount")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class DiscountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "discount_value", nullable = false)
    private BigDecimal discountValue;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DiscountActive discountActive;

    @OneToMany(mappedBy = "discount", fetch = FetchType.LAZY)
    private Set<OrderEntity> order = new HashSet<>();

    @OneToMany(mappedBy = "discount", fetch = FetchType.LAZY)
    private Set<ProductEntity> product = new HashSet<>();

    @OneToMany(mappedBy = "discount", fetch = FetchType.LAZY)
    private Set<SaleEntity> sale = new HashSet<>();

}
