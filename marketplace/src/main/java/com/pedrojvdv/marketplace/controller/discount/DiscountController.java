package com.pedrojvdv.marketplace.controller.discount;

import com.pedrojvdv.marketplace.database.model.Discount.DiscountEntity;
import com.pedrojvdv.marketplace.dto.Discount.DiscountDto;
import com.pedrojvdv.marketplace.enums.Discount.DiscountActive;
import com.pedrojvdv.marketplace.exception.NotFoundException;
import com.pedrojvdv.marketplace.service.Discount.DiscountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/v1/discount")
@RequiredArgsConstructor
@Validated
public class DiscountController {

    private final DiscountService discountService;

    //POST
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createDiscount(@Valid @RequestBody DiscountDto discountDto)throws NotFoundException {
        discountService.createDiscount(discountDto);
    }

    @PutMapping("/{discountId}")
    @ResponseStatus(HttpStatus.OK)
    public void updateDiscount(@Valid @RequestBody DiscountDto discountDto, @PathVariable("discountId") Long discountId) throws NotFoundException, BadRequestException {
        discountService.updateDiscount(discountDto, discountId);
    }

    @DeleteMapping("/{discountId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDiscount(@Valid @PathVariable ("discountId") Long discountId) throws NotFoundException {
        discountService.deleteDiscount(discountId);
    }

    //GET
    @GetMapping("/filter/discount-value-fil")
    @ResponseStatus(HttpStatus.OK)
    public List<DiscountDto> getByDiscountValue( @RequestParam BigDecimal discountValue) {
        return discountService.getAllDiscountsByValue(discountValue);
    }

    @GetMapping("/filter/discounts-fil")
    @ResponseStatus(HttpStatus.OK)
    public List<DiscountDto> getAllDiscounts() {
        return discountService.getAllDiscounts();
    }

    @GetMapping("/filter/discount-active-fil")
    @ResponseStatus(HttpStatus.OK)
    public List<DiscountDto> getActiveDiscounts() {
        return discountService.getAllDiscountsActive(DiscountActive.YES);
    }

    @GetMapping("/filter/discount-nonActive-fil")
    @ResponseStatus(HttpStatus.OK)
    public List<DiscountDto> getNonActiveDiscounts() {
        return discountService.getAllDiscountsInactive(DiscountActive.NO);
    }
}
