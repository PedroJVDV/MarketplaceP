package com.pedrojvdv.marketplace.database.model.Wish;


import com.pedrojvdv.marketplace.database.model.Product.ProductEntity;
import com.pedrojvdv.marketplace.database.model.User.UserEntity;
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
    private UserEntity users;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id", nullable = false)
    private ProductEntity product;

}
