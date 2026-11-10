package com.pedrojvdv.marketplace.controller.wishList;


import com.pedrojvdv.marketplace.dto.Wish.WishListAdminResponseDto;
import com.pedrojvdv.marketplace.dto.Wish.WishListCrudResponseDto;
import com.pedrojvdv.marketplace.dto.Wish.WishListResponseDto;
import com.pedrojvdv.marketplace.service.Wish.WishListService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/wishlist")
@RequiredArgsConstructor
@Validated
public class WishListController {

    private final WishListService wishListService;

    //POST,DELETE
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createWishList(@Valid @RequestBody WishListCrudResponseDto wishListCrudResponseDto) {
        wishListService.createWishList(wishListCrudResponseDto);
    }

    @PatchMapping("/{wishListId}")
    @ResponseStatus(HttpStatus.OK)
    public void updateWishList(@Valid @RequestBody WishListCrudResponseDto wishListCrudResponseDto, @PathVariable Long wishListId) {
        wishListService.updateWishList(wishListCrudResponseDto, wishListId);
    }

    @DeleteMapping("/{wishListId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteWishList(@PathVariable Long wishListId) {
        wishListService.deleteWishList(wishListId);
    }

    //GET
    @GetMapping("/filter/email")
    @ResponseStatus(HttpStatus.OK)
    public List<WishListAdminResponseDto> findByEmail(@RequestParam String email) {
        return wishListService.getWishListByUserEmail(email);
    }

    @GetMapping("/userwishlist")
    @ResponseStatus(HttpStatus.OK)
    public List<WishListResponseDto> getUserWishList() {
        return wishListService.getUserWishList();
    }

    @GetMapping("/filter/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public List<WishListAdminResponseDto> findByUserId(@Valid @PathVariable Long userId) {
        return wishListService.getWishListByUserId(userId);
    }

    @GetMapping("/filter/wishlists")
    @ResponseStatus(HttpStatus.OK)
    public List<WishListAdminResponseDto> getWishLists() {
        return wishListService.getAllWishLists();
    }
}
