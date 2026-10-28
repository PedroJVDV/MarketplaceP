package com.pedrojvdv.marketplace.service.Wish;

import com.pedrojvdv.marketplace.database.model.Product.ProductEntity;
import com.pedrojvdv.marketplace.database.model.User.UserEntity;
import com.pedrojvdv.marketplace.database.model.Wish.WishListEntity;
import com.pedrojvdv.marketplace.database.repository.Product.IProductRepository;
import com.pedrojvdv.marketplace.database.repository.Wish.IWishListRepository;
import com.pedrojvdv.marketplace.dto.Wish.WishListAdminResponseDto;
import com.pedrojvdv.marketplace.dto.Wish.WishListResponseDto;
import com.pedrojvdv.marketplace.exception.NotFoundException;
import com.pedrojvdv.marketplace.service.Auth.AuthenticatedUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WishListService {

    private final IWishListRepository wishListRepository;
    private final IProductRepository productRepository;
    private final AuthenticatedUserService authenticatedUserService;

    @PreAuthorize("isAuthenticated()")
    @Transactional(rollbackFor = Exception.class)
    public void createWishList(WishListResponseDto wishListResponseDto) {

        UserEntity user = authenticatedUserService.getCurrentAuthenticatedUser();
        ProductEntity product = productRepository.findByPublicIdAndQuantityGreaterThan(wishListResponseDto.getProductCode(), 0)
                .orElseThrow(() -> new NotFoundException("Produto não encontrado ou indisponível!"));

        WishListEntity wishList = WishListEntity.builder()
                .users(user)
                .product(product)
                .build();

        wishListRepository.save(wishList);
    }

    @PreAuthorize("isAuthenticated()")
    @Transactional(rollbackFor = Exception.class)
    public void updateWishList(WishListResponseDto wishListResponseDto) throws NotFoundException {

        wishListRepository.findByWishListGroupName(wishListResponseDto.getWishListGroupName())
                .ifPresentOrElse(wishList -> {
                    wishList.setWishListGroupName(wishListResponseDto.getWishListGroupName());

                    wishListRepository.save(wishList);
                }, () -> {
                    throw new NotFoundException("Lista de desejos não encontrada!");
                });
    }

    @PreAuthorize("isAuthenticated()")
    @Transactional(rollbackFor = Exception.class)
    public void deleteWishList() throws NotFoundException {
        wishListRepository.findByUsers_Id(authenticatedUserService.getCurrentAuthenticatedUser().getId())
                .ifPresentOrElse(wishListRepository::delete,
                        () -> {
                            throw new NotFoundException("Nenhuma lista de desejos vinculada a este usuário!");
                        });
    }

    @PreAuthorize("isAuthenticated()")
    public List<WishListAdminResponseDto> getAllWishLists() {
        List<WishListAdminResponseDto> wish = wishListRepository.findAll()
                .stream()
                .map(this::toAdminDto)
                .toList();

        if (wish.isEmpty()) {
            throw new NotFoundException("Nenhuma lista de desejos encontrada!");
        }
        return wish;
    }

    @PreAuthorize("isAuthenticated()")
    public void getWishListByUserId(Long userId) throws NotFoundException {
        List<WishListAdminResponseDto> wish = wishListRepository.findByUsers_Id(userId)
                .stream()
                .map(this::toAdminDto)
                .toList();

        if (wish.isEmpty()) {
            throw new NotFoundException("Nenhuma lista de desejos encontrada para este usuário!");
        }
    }

    @PreAuthorize("isAuthenticated()")
    public void getWishListByUserEmail(String email) throws NotFoundException {
        List<WishListAdminResponseDto> wish = wishListRepository.findByUsers_Email(email)
                .stream()
                .map(this::toAdminDto)
                .toList();
        if (wish.isEmpty()) {
            throw new NotFoundException("Nenhuma lista de desejo encontrada para este email de usuário!");
        }
    }

    @PreAuthorize("isAuthenticated() && hasAnyRole('USER', 'SELLER', 'ADMIN')")
    public List<WishListResponseDto> getUserWishList() throws NotFoundException {
        List<WishListResponseDto> wishList = wishListRepository.findWishListByUsers_id(authenticatedUserService.getCurrentAuthenticatedUser().getId())
                .stream()
                .map(this::toUserDto)
                .toList();
        if (wishList.isEmpty()) {
            throw new NotFoundException("Nenhuma lista de desejos encontrada para este usuário!");
        }
        return wishList;
    }

    private WishListResponseDto toUserDto(WishListEntity wishListEntity) {
        WishListResponseDto dto = new WishListResponseDto();

        dto.setWishListGroupName(wishListEntity.getWishListGroupName());
        dto.setProductCode(wishListEntity.getProduct().getPublicId());
        dto.setProductName(wishListEntity.getProduct().getName());
        dto.setProductPrice(wishListEntity.getProduct().getPrice());
        return dto;
    }

    private WishListAdminResponseDto toAdminDto(WishListEntity p) {
        WishListAdminResponseDto dto = new WishListAdminResponseDto();

        dto.setId(p.getId());
        dto.setUserId(p.getUsers().getId());
        dto.setProductId(p.getProduct().getId());
        dto.setProductName(p.getProduct().getName());
        dto.setProductPrice(p.getProduct().getPrice());
        dto.setWishListGroupName(p.getWishListGroupName());

        return dto;
    }
}


