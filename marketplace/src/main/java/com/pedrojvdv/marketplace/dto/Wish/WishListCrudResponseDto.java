package com.pedrojvdv.marketplace.dto.Wish;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WishListCrudResponseDto {

    @NotBlank
    private String productCode;

    @NotBlank
    private String wishListGroupName;
}
