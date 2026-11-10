package com.pedrojvdv.marketplace.dto.Wish;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WishListCrudResponseDto {

    @NotNull
    @NotBlank
    private String productCode;
    @NotNull
    @NotBlank
    private String wishListGroupName;
}
