package com.pedrojvdv.marketplace.database.repository;

import com.pedrojvdv.marketplace.database.model.WishListEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IWishListRepository extends JpaRepository<WishListEntity, Long> {

}
