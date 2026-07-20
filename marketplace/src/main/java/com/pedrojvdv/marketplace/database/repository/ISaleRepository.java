package com.pedrojvdv.marketplace.database.repository;

import com.pedrojvdv.marketplace.database.model.SaleEntity;
import com.pedrojvdv.marketplace.database.model.embedabbles.Adress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ISaleRepository extends JpaRepository<SaleEntity, Long> {

    List<SaleEntity> findBySaleLocation(Adress adress);

    List<SaleEntity> findByPublishDate(LocalDateTime purchaseDate);

}
