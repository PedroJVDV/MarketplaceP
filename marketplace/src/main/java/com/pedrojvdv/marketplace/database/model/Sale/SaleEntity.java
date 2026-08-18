package com.pedrojvdv.marketplace.database.model.Sale;

import com.pedrojvdv.marketplace.database.model.Discount.DiscountEntity;
import com.pedrojvdv.marketplace.database.model.Product.ProductEntity;
import com.pedrojvdv.marketplace.database.model.User.UserEntity;
import com.pedrojvdv.marketplace.database.model.embedabbles.Adress;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "sale")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class SaleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "adress", nullable = false)
    private Adress saleLocation;

    @Column(nullable = false)
    private Integer quantity;

    @Column(name = "publish_date", nullable = false, updatable = false)
    private LocalDateTime publishDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity users;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "discount_id")
    private DiscountEntity discount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private ProductEntity product;

}
