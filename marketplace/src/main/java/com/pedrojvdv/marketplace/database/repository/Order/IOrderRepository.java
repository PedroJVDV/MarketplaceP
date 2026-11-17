package com.pedrojvdv.marketplace.database.repository.Order;

import com.pedrojvdv.marketplace.database.model.Order.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface IOrderRepository extends JpaRepository<OrderEntity, Long> {


    Optional<OrderEntity> findByQuantity(Integer quantity);


    @Query(value = """
                        SELECT o from OrderEntity o
                        WHERE EXTRACT( hour FROM o.orderTime) = :hour
                        AND EXTRACT(minute FROM o.orderTime) = :minute
            """)
    List<OrderEntity> findOrderByHourAndMinute(int hour, int minute);

    @Query(value = """
                    SELECT o
                    FROM OrderEntity o
                    WHERE o.users.id = :userId
                    AND o.id IS NOT NULL
            """)
    List<OrderEntity> getAllOrdersByUserId(Long userId);

}
