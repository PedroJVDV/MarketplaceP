package com.pedrojvdv.marketplace.dto.Wish;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WishListDto {

    private Long id;

    public Long productId;
    public Long userId;
}
