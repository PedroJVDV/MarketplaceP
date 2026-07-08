package com.pedrojvdv.marketplace.database.model.embedabbles;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Size;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Embeddable
public class Adress {

    @Column(name = "city_name", nullable = false, length = 50)
    private String city;

    @Column(name = "hood", nullable = false, length = 50)
    private String  neighborhood;

    @Column(name = "street_name", nullable = false, length = 125)
    private String street;

    @Size(min = 5, max = 5, message = "O CEP deve ter exatamente 5 digitos!")
    @Column(name = "cep_number", nullable = false, length = 5)
    private String cep;

    @Column(name = "house_number", nullable = false)
    private String number;

}
