package com.pedrojvdv.marketplace.dto.Sale;

import com.pedrojvdv.marketplace.database.model.embedabbles.Adress;
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

    private Adress saleLocation;
    private Integer quantity;
    private LocalDateTime publishDate;

}
