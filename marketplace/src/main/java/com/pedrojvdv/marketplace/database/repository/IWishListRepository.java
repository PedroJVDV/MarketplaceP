package com.pedrojvdv.marketplace.database.repository;

import com.pedrojvdv.marketplace.database.model.WishListEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IWishListRepository extends JpaRepository<WishListEntity, Long> {

    List<WishListEntity> findByUser_Id(Long userId);
    List<WishListEntity> findByProduct_Id(Long productId);
}
