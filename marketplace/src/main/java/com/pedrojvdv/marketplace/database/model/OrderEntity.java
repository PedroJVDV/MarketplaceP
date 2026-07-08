package com.pedrojvdv.marketplace.database.model;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "order")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_quantity", nullable = false)
    private Integer quantity;
}
