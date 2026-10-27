package com.pedrojvdv.marketplace.dto.Sale;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pedrojvdv.marketplace.database.model.embedabbles.Adress;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SaleDto {

    //ONLY FOR
    @NotNull
    @NotBlank
    private Adress saleLocation;
    private Integer quantity;
    @NotNull
    @NotBlank
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime publishDate;
    @NotNull
    @NotBlank
    private Long userId;
    @NotNull
    @NotBlank
    private Long productId;
    private Long discountId;

}
