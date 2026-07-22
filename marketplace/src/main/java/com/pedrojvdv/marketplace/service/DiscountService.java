package com.pedrojvdv.marketplace.service;

import com.pedrojvdv.marketplace.database.model.DiscountEntity;
import com.pedrojvdv.marketplace.database.repository.IDiscountRepository;
import com.pedrojvdv.marketplace.dto.DiscountDto;
import com.pedrojvdv.marketplace.enums.DiscountActive;
import com.pedrojvdv.marketplace.exception.NotFoundException;
import lombok.RequiredArgsConstructor;

import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DiscountService {

    private final IDiscountRepository discountRepository;

    public DiscountEntity createDiscount(DiscountDto discountDto) {

        DiscountEntity discount = DiscountEntity.builder()
                .discountValue(discountDto.getDiscountValue())
                .discountActive(discountDto.getDiscountActive())
                .build();

        return discountRepository.save(discount);
    }

    @Transactional
    public void updateDiscount(Long id, DiscountDto discountDto) throws BadRequestException {
        DiscountEntity discount = discountRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Desconto não encontrado!"));

        if (discountDto.getDiscountValue() != null) {
            discount.setDiscountValue(discountDto.getDiscountValue());
        }
        if (discountDto.getDiscountActive() != null) {
            discount.setDiscountActive(discountDto.getDiscountActive());
        }

        discountRepository.save(discount);
    }

    @Transactional
    public void deleteDiscount(Long id)throws NotFoundException {
        DiscountEntity discount = discountRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Desconto não encontrado!"));

        discountRepository.delete(discount);
    }

    public List<DiscountDto> getAllDiscounts() {
        return discountRepository.getAllDiscounts();
    }

    public Optional<DiscountEntity> getDiscountById(Long id) {
        return discountRepository.findById(id);
    }

    public List<DiscountDto> getAllDiscountsByValue(BigDecimal discountValue) {
        return discountRepository.getByDiscountValue(discountValue);
    }

    public List<DiscountDto> getAllDiscountsActive(DiscountActive discountActive) {
        return discountRepository.getByActiveDiscount(discountActive);
    }

    public List<DiscountDto> getAllDiscountsInactive(DiscountActive discountActive) {
        return discountRepository.getByInativeDiscount(discountActive);
    }

}
