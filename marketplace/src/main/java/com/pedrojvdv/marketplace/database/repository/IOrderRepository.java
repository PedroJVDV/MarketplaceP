package com.pedrojvdv.marketplace.database.repository;

import com.pedrojvdv.marketplace.database.model.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface IOrderRepository extends JpaRepository<OrderEntity, Long> {


    Optional<OrderEntity> findByQuantity(Integer quantity);

    List<OrderEntity> findByOrderTime(LocalDateTime orderTime);

    @Query("""
                    SELECT o FROM OrderEntity o
                    WHERE o.user.id = :userId
                    AND o.quantity = :quantity
            """)
    List<OrderEntity> getAllOrdersByUserId(Long userId);

}
