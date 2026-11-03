package com.pedrojvdv.marketplace.controller.wishList;


import com.pedrojvdv.marketplace.dto.Wish.WishListAdminResponseDto;
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
    public void createWishList(@Valid @RequestBody WishListResponseDto wishListResponseDto) {
        wishListService.createWishList(wishListResponseDto);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateWishList(@Valid @RequestBody WishListResponseDto wishListResponseDto) {
        wishListService.updateWishList(wishListResponseDto);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteWishList() {
        wishListService.deleteWishList();
    }

    //GET
    @GetMapping("/filter/email")
    @ResponseStatus(HttpStatus.OK)
    public Optional<WishListAdminResponseDto> findByEmail(@RequestParam String email) {
        return wishListService.getWishListByUserEmail(email);
    }

    @GetMapping("/myWishList")
    @ResponseStatus(HttpStatus.OK)
    public List<WishListResponseDto> getUserWishList() {
        return wishListService.getUserWishList();
    }

    @GetMapping("/filter/{userId}/admin")
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
