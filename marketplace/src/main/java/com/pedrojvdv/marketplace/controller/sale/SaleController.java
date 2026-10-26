package com.pedrojvdv.marketplace.controller.sale;


import com.pedrojvdv.marketplace.dto.Sale.SaleDto;
import com.pedrojvdv.marketplace.dto.Sale.SaleResponseDto;
import com.pedrojvdv.marketplace.exception.NotFoundException;
import com.pedrojvdv.marketplace.service.Sale.SaleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/sale")
@RequiredArgsConstructor
@Validated
public class SaleController {

    private final SaleService saleService;

    //POST,DELETE
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createSale(@Valid @RequestBody SaleDto saleDto) throws NotFoundException {
        saleService.createSale(saleDto);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public void updateSale(@Valid @RequestBody SaleDto saleDto) throws NotFoundException {
        saleService.updateSale(saleDto);
    }

    @DeleteMapping("/{saleId}")
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void deleteSale(@Valid @PathVariable("saleId") Long saleId ) throws NotFoundException {
        saleService.deleteSale(saleId);
    }

    //GET
    @GetMapping("/filter/full-adress")
    @ResponseStatus(HttpStatus.OK)
    public List<SaleResponseDto> findByFullAdress(@RequestParam String cep,
                                          @RequestParam String city,
                                          @RequestParam String hood,
                                          @RequestParam String streetName,
                                          @RequestParam String houseNumber) throws NotFoundException {
        return saleService.getSalesByFullAdress(cep, city, hood, streetName, houseNumber);
    }

    @GetMapping("/filter/city")
    @ResponseStatus(HttpStatus.OK)
    public List<SaleResponseDto> findByCity(@RequestParam String city) throws NotFoundException {
        return saleService.getSaleByCity(city);
    }

    @GetMapping("/filter/cep")
    @ResponseStatus(HttpStatus.OK)
    public List<SaleResponseDto> findByCep(@RequestParam String cep) throws NotFoundException {
        return saleService.getSaleByCep(cep);
    }

    @GetMapping("/filter/quantity")
    @ResponseStatus(HttpStatus.OK)
    public List<SaleResponseDto> findByQuantity(@RequestParam Integer quantity) throws NotFoundException {
        return saleService.getByQuantity(quantity);
    }

    @GetMapping("/filter/date")
    @ResponseStatus(HttpStatus.OK)
    public List<SaleResponseDto> findByDate(@RequestParam
                                        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate date) throws NotFoundException {
        return saleService.getByPublishDate(date);
    }

    @GetMapping("/filter/saleUID/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<SaleDto> findByUserId(@PathVariable("userId") Long userId) throws NotFoundException {
        return saleService.getSaleByUserId(userId);
    }

    @GetMapping("/filter/saleID/{saleId}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<SaleResponseDto> findBySaleId(@PathVariable("saleId") Long saleId) throws NotFoundException {
        return saleService.getSalesById(saleId);
    }

    @GetMapping("/filter/sales")
    @ResponseStatus(HttpStatus.OK)
    public List<SaleResponseDto> getAllSales() throws NotFoundException {
        return saleService.getAllSales();
    }
}
