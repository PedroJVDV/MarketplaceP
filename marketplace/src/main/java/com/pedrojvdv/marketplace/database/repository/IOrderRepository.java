package com.pedrojvdv.marketplace.database.repository;

import com.pedrojvdv.marketplace.database.model.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface IOrderRepository extends JpaRepository<OrderEntity, Long> {

    List<OrderEntity> findByQuantity(Integer quantity);

    List<OrderEntity> findByOrderTime(LocalDateTime orderTime);

}
