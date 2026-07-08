package com.pedrojvdv.marketplace.database.model;

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

}
