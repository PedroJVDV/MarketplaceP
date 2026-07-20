package com.pedrojvdv.marketplace.database.model;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "wish_list")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class WishListEntity {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id", nullable = false)
    private ProductEntity product;

}
