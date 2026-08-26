package com.pedrojvdv.marketplace.service.Wish;

import com.pedrojvdv.marketplace.database.model.Product.ProductEntity;
import com.pedrojvdv.marketplace.database.model.User.UserEntity;
import com.pedrojvdv.marketplace.database.model.Wish.WishListEntity;
import com.pedrojvdv.marketplace.database.repository.Product.IProductRepository;
import com.pedrojvdv.marketplace.database.repository.User.IUserRepository;
import com.pedrojvdv.marketplace.database.repository.Wish.IWishListRepository;
import com.pedrojvdv.marketplace.dto.User.UserDto;
import com.pedrojvdv.marketplace.dto.Wish.WishListDto;
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
                .users(user)
                .product(product)
                .build());
    }

    public void updateWishList(WishListDto wishListDto) throws NotFoundException {
        wishListRepository.findById(wishListDto.getId())
                .ifPresentOrElse(wish -> {
                    wish.setProduct(wish.getProduct());
                    wish.setUsers(wish.getUsers());
                    wishListRepository.save(wish);
                }, () -> {
                    throw new NotFoundException("Lista de desejos não encontrada!");
                });
    }

    public void deleteWishList(Long id) throws NotFoundException {
        wishListRepository.findById(id).ifPresentOrElse(wishListRepository::delete,
                () -> {
                throw new NotFoundException("ID da lista de desejos é inexistente!");
        });
    }

    public List<WishListDto> getAllWishLists() {
        List<WishListDto> wish = wishListRepository.findAll()
                .stream()
                .map(this::toDto)
                .toList();

        if (wish.isEmpty()) {
            throw new NotFoundException("Nenhuma lista de desejos encontrada!");
        }
        return wish;
    }

    public List<WishListDto> getWishListByUserId(Long userId) throws NotFoundException {
        List<WishListDto> wish = wishListRepository.findByUsers_Id(userId)
                .stream()
                .map(this::toDto)
                .toList();

        if (wish.isEmpty()) {
            throw new NotFoundException("Nenhuma lista de desejos encontrada para este usuário!");
        }
        return wish;
    }

    public List<WishListDto> getWishListByUserEmail(String email) throws NotFoundException {
        List<WishListDto> wish = wishListRepository.findByUsers_Email(email)
                .stream()
                .map(this::toDto)
                .toList();

        if (wish.isEmpty()) {
            throw new NotFoundException("Nenhuma lista de desejo encontrada para este email de usuário!");
        }
        return wish;
    }

    private WishListDto toDto(WishListEntity p) {
        WishListDto dto = new WishListDto();

        dto.setId(p.getId());
        dto.setUserId(p.getUsers().getId());
        dto.setProductId(p.getProduct().getId());
        return dto;
    }
}


