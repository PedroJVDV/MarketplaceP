package com.pedrojvdv.marketplace.database.repository.Wish;

import com.pedrojvdv.marketplace.database.model.Wish.WishListEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IWishListRepository extends JpaRepository<WishListEntity, Long> {


    Optional<WishListEntity> findByWishListGroupName(String wishListGroupName);
    Optional<WishListEntity> findByUsers_Id(Long userId);
    Optional<WishListEntity> findByProduct_Id(Long productId);
    List<WishListEntity> findByProduct_name(String productName);
    Optional<WishListEntity> findByUsers_Email(String email);
}
