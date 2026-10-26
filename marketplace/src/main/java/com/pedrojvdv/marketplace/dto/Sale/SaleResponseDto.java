package com.pedrojvdv.marketplace.dto.Sale;

import com.pedrojvdv.marketplace.database.model.embedabbles.Adress;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SaleResponseDto {

    @NotNull
    @NotBlank
    private Adress saleLocation;
    private Integer quantity;
    @NotNull
    @NotBlank
    private LocalDateTime publishDate;
    @NotNull
    @NotBlank
    private String username;
    @NotNull
    @NotBlank
    private String productName;
    private BigDecimal discountValue;
}
