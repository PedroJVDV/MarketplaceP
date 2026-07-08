package com.pedrojvdv.marketplace.database.model;

import com.pedrojvdv.marketplace.enums.DiscountActive;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

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

}
