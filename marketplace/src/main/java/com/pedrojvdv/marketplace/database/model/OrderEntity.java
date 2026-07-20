package com.pedrojvdv.marketplace.database.model;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

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

    @CreationTimestamp
    @Column(name =  "order_time", nullable = false, updatable = false)
    private LocalDateTime orderTime;

}
