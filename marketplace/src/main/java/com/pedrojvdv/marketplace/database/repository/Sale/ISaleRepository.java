package com.pedrojvdv.marketplace.database.repository.Sale;

import com.pedrojvdv.marketplace.database.model.Sale.SaleEntity;
import com.pedrojvdv.marketplace.database.model.embedabbles.Adress;
import org.jspecify.annotations.NullMarked;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ISaleRepository extends JpaRepository<SaleEntity, Long> {

    List<SaleEntity> findByPublishDateGreaterThanEqualAndPublishDateLessThan(LocalDateTime startOfDay, LocalDateTime StartOfNextDay);

    List<SaleEntity> findByUsers_Id(Long userId);

    @NullMarked
    Optional<SaleEntity> findById(Long id);

    List<SaleEntity> findByQuantity(Integer quantity);

    @Query("SELECT s FROM SaleEntity s WHERE s.saleLocation.cep = :cep")
    List<SaleEntity> findByCep_Number(String cep);

    @Query("SELECT s FROM SaleEntity s WHERE s.saleLocation.city = :city")
    List<SaleEntity> findByCity_Name(String city);

    @Query("""
            SELECT s FROM SaleEntity s
            WHERE s.saleLocation.cep = :cep
              AND s.saleLocation.city = :city
              AND s.saleLocation.neighborhood = :hood
              AND s.saleLocation.street = :streetName
              AND s.saleLocation.number = :houseNumber
            """)
    List<SaleEntity> findByAdress(
            String cep,
            String city,
            String hood,
            String streetName,
            String houseNumber
    );

}
