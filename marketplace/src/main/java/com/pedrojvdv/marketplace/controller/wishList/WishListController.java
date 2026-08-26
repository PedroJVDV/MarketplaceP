package com.pedrojvdv.marketplace.controller.wishList;


import com.pedrojvdv.marketplace.dto.Wish.WishListDto;
import com.pedrojvdv.marketplace.service.Wish.WishListService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/wishlist")
@RequiredArgsConstructor
@Validated
public class WishListController {

    private final WishListService wishListService;

    //POST,DELETE
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createWishList(@Valid @RequestBody WishListDto wishListDto) {
        wishListService.createWishList(wishListDto);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateWishList(@Valid @RequestBody WishListDto wishListDto) {
        wishListService.updateWishList(wishListDto);
    }

    @DeleteMapping("/delete/{wishId}/admin")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteWishList(@Valid @PathVariable Long wishId) {
        wishListService.deleteWishList(wishId);
    }

    //GET
    @GetMapping("/filter/email")
    @ResponseStatus(HttpStatus.OK)
    public void findByEmail(@RequestParam String email) {
        wishListService.getWishListByUserEmail(email);
    }

    @GetMapping("/filter/{userId}/admin")
    @ResponseStatus(HttpStatus.OK)
    public void findByUserId(@Valid @PathVariable Long userId) {
        wishListService.getWishListByUserId(userId);
    }

    @GetMapping("/filter/wishlists")
    @ResponseStatus(HttpStatus.OK)
    public List<WishListDto> getWishLists() {
        return wishListService.getAllWishLists();
    }
}
