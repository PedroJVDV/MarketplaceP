package com.pedrojvdv.marketplace.controller.order;

import com.pedrojvdv.marketplace.dto.Order.OrderDto;
import com.pedrojvdv.marketplace.exception.NotFoundException;
import com.pedrojvdv.marketplace.service.Order.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/v1/order")
@RequiredArgsConstructor
@Validated
public class OrderController {

    private final OrderService orderService;

    //POST-DELETE
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createOrder(@Valid @RequestBody OrderDto orderDto)throws NotFoundException {
        orderService.createOrder(orderDto);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public void updateOrder(@Valid @RequestBody OrderDto orderDto) throws NotFoundException {
        orderService.updateOrder(orderDto);
    }

    @DeleteMapping("{orderId}/delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrder(@Valid @PathVariable("orderId") Long orderId) throws NotFoundException {
        orderService.deleteOrder(orderId);
    }

    //GET
    @GetMapping("/{userId}/admin")
    @ResponseStatus(HttpStatus.OK)
    public void findByOrderId(@PathVariable("userId") Long userId) {
        orderService.getAllOrdersByUserId(userId);
    }

    @GetMapping("/ordertime")
    @ResponseStatus(HttpStatus.OK)
    public List<OrderDto> getByOrderTime(@RequestParam LocalDateTime orderTime) {
        return orderService.getByOrderTime(orderTime);
    }

    @GetMapping("/all-orders")
    @ResponseStatus(HttpStatus.OK)
    public List<OrderDto> getAllOrders() {
        return orderService.getAllOrders();
    }

}
