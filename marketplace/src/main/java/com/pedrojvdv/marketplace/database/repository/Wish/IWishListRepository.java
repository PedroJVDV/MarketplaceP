package com.pedrojvdv.marketplace.database.repository.Wish;

import com.pedrojvdv.marketplace.database.model.Wish.WishListEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IWishListRepository extends JpaRepository<WishListEntity, Long> {

    List<WishListEntity> findByUsers_Id(Long userId);
    List<WishListEntity> findByProduct_Id(Long productId);
    List<WishListEntity> findByUsers_Email(String email);
}
