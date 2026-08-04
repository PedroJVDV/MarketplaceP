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

import java.util.List;

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

    public void updateWishList(WishListDto wishListDto) throws NotFoundException {
        wishListRepository.findById(wishListDto.getId())
                .ifPresentOrElse(wish -> {
                    wish.setProduct(wish.getProduct());
                    wish.setUser(wish.getUser());
                    wishListRepository.save(wish);
                }, () -> {
                    throw new NotFoundException("Lista de desejos não encontrada!");
                });
    }

    public void deleteWishList(WishListDto wishListDto, Long id) throws NotFoundException {
        wishListRepository.findById(wishListDto.getId()).ifPresent(wish -> {
            if (wishListDto.getId().equals(id)) {
                wishListRepository.delete(wish);
            } else {
                throw new NotFoundException("ID da lista de desejos é inexistente!");
            }
        });
    }

    public List<WishListEntity> getAllWishLists() {
        List<WishListEntity> wish = wishListRepository.findAll();

        if (wish.isEmpty()) {
            throw new NotFoundException("Nenhuma lista de desejos encontrada!");
        }
        return wish;
    }

    public List<WishListEntity> getWishListByUserId(Long userId) throws NotFoundException {
        List<WishListEntity> wish = wishListRepository.findByUser_Id(userId);

        if (wish.isEmpty()) {
            throw new NotFoundException("Nenhuma lista de desejos encontrada para este usuário!");
        }
        return wish;
    }

    public List<WishListEntity> getWishListByUserEmail(String email) throws NotFoundException {
        List<WishListEntity> wish = wishListRepository.findByUser_Email(email);

        if (wish.isEmpty()) {
            throw new NotFoundException("Nenhuma lista de desejo encontrada para este email de usuário!");
        }
        return wish;
    }
}

