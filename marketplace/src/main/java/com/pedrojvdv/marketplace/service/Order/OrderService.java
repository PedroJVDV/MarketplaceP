package com.pedrojvdv.marketplace.service.Order;

import com.pedrojvdv.marketplace.database.model.Order.OrderEntity;
import com.pedrojvdv.marketplace.database.model.Product.ProductEntity;
import com.pedrojvdv.marketplace.database.model.User.UserEntity;
import com.pedrojvdv.marketplace.database.repository.Order.IOrderRepository;
import com.pedrojvdv.marketplace.database.repository.Product.IProductRepository;
import com.pedrojvdv.marketplace.database.repository.User.IUserRepository;
import com.pedrojvdv.marketplace.dto.Order.OrderDto;
import com.pedrojvdv.marketplace.dto.Product.ProductDto;
import com.pedrojvdv.marketplace.dto.User.UserDto;
import com.pedrojvdv.marketplace.exception.BadRequestException;
import com.pedrojvdv.marketplace.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final IOrderRepository orderRepository;
    private final IProductRepository productRepository;
    private final IUserRepository userRepository;


    @Transactional(rollbackFor = Exception.class)
    public void createOrder(OrderDto orderDto, String login) throws BadRequestException {
        if (login == null || login.isBlank()) {
            throw new BadRequestException("Login autenticado inválido.");
        }

        UserEntity user = userRepository.findUserEntityByUsernameLogin(login)
                .orElseGet(() -> userRepository.findByEmail(login)
                .orElseThrow(() ->
                        new BadRequestException("Usuário não encontrado!")));

        ProductEntity product = productRepository.findById(orderDto.getProductId())
                .orElseThrow(() -> new BadRequestException("Produto não encontrado!"));

        orderRepository.save(OrderEntity.builder()
                .users(user)
                .product(product)
                .quantity(orderDto.getQuantity())
                .orderTime(LocalDateTime.now())
                .build());
    }

    @Transactional(rollbackFor = Exception.class)
    public void updateOrder(OrderDto orderDto) throws NotFoundException {
        OrderEntity order = orderRepository.findByQuantity(orderDto.getQuantity())
                .orElseThrow(() -> new NotFoundException("Quantidade não definida para esta ordem!"));

        if (orderDto.getQuantity() != null) {
            order.setQuantity(orderDto.getQuantity());
        }
        orderRepository.save(order);
    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteOrder(Long id) throws NotFoundException {
        OrderEntity order = orderRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Order inexistente!"));
        orderRepository.delete(order);
    }

    @Transactional(readOnly = true)
    public List<OrderDto> getAllOrders() {
        return orderRepository.findAll()
                .stream()
                .map(this::toDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<OrderDto> getByOrderTime(LocalDateTime orderTime) {
        return orderRepository.findByOrderTime(orderTime)
                .stream()
                .map(this::toDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<OrderDto> getAllOrdersByUserId(Long userId) {
        return orderRepository.getAllOrdersByUserId(userId)
                .stream()
                .map(this::toDto)
                .toList();
    }

    private OrderDto toDto(OrderEntity p) {
        OrderDto dto = new OrderDto();

        dto.setOrderId(p.getId());
        dto.setOrderTime(p.getOrderTime());
        dto.setQuantity(p.getQuantity());
        dto.setProductId(p.getProduct().getId());
        dto.setDiscountId(p.getDiscount().getId());
        dto.setUserId(p.getUsers().getId());

        return dto;
    }
}
