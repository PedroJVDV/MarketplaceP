package com.pedrojvdv.marketplace.service;

import com.pedrojvdv.marketplace.database.model.ProductEntity;
import com.pedrojvdv.marketplace.database.model.UserEntity;
import com.pedrojvdv.marketplace.database.model.WishListEntity;
import com.pedrojvdv.marketplace.database.repository.IProductRepository;
import com.pedrojvdv.marketplace.database.repository.IUserRepository;
import com.pedrojvdv.marketplace.database.repository.IWishListRepository;
import com.pedrojvdv.marketplace.dto.WishListDto;
import com.pedrojvdv.marketplace.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WishListService {

    private final IWishListRepository wishListRepository;
    private final IUserRepository userRepository;
    private final IProductRepository productRepository;

    public void createWishList(WishListDto wishListDto) {
        UserEntity user = userRepository.findById(wishListDto.getUserId())
                .orElseThrow(() -> new NotFoundException("Usuário não encontrado!"));

        ProductEntity product = productRepository.findById(wishListDto.getProductId())
                .orElseThrow(() -> new NotFoundException("Produto não encontrado!"));

        wishListRepository.save(WishListEntity.builder()
                .user(user)
                .product(product)
                .build());
    }

    //TODO: wishList update ++++ functions... 04/08
}

