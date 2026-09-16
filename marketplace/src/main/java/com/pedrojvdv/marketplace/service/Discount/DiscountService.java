package com.pedrojvdv.marketplace.service.Discount;

import com.pedrojvdv.marketplace.database.model.Discount.DiscountEntity;
import com.pedrojvdv.marketplace.database.model.Order.OrderEntity;
import com.pedrojvdv.marketplace.database.repository.Discount.DiscountSelectionProjection;
import com.pedrojvdv.marketplace.database.repository.Discount.IDiscountRepository;
import com.pedrojvdv.marketplace.database.repository.Product.IProductRepository;
import com.pedrojvdv.marketplace.dto.Discount.DiscountDto;
import com.pedrojvdv.marketplace.dto.Order.OrderDto;
import com.pedrojvdv.marketplace.dto.Product.ProductDto;
import com.pedrojvdv.marketplace.enums.Discount.DiscountActive;
import com.pedrojvdv.marketplace.exception.NotFoundException;
import lombok.RequiredArgsConstructor;

import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DiscountService {

    private final IDiscountRepository discountRepository;

    @Transactional(rollbackFor = Exception.class)
    public void createDiscount(DiscountDto discountDto) {

        DiscountEntity discount = DiscountEntity.builder()
                .discountValue(discountDto.getDiscountValue())
                .discountActive(discountDto.getDiscountActive())
                .build();

        discountRepository.save(discount);
    }

    @Transactional(rollbackFor = Exception.class)
    public void updateDiscount(DiscountDto discountDto, Long discountId) throws BadRequestException {
        DiscountEntity discount = discountRepository.findById(discountId)
                .orElseThrow(() -> new BadRequestException("Desconto não encontrado!"));

        if (discountDto.getDiscountValue() != null) {
            discount.setDiscountValue(discountDto.getDiscountValue());
        }
        if (discountDto.getDiscountActive() != null) {
            discount.setDiscountActive(discountDto.getDiscountActive());
        }

        discountRepository.save(discount);
    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteDiscount(Long id) throws NotFoundException {
        DiscountEntity discount = discountRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Desconto não encontrado!"));

        discountRepository.delete(discount);
    }

    @Transactional(readOnly = true)
    public List<DiscountSelectionProjection> getAllDiscounts() {
        return discountRepository.findAllDiscounts();
    }

    @Transactional(readOnly = true)
    public Optional<DiscountEntity> getDiscountById(Long id) {
        return discountRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<DiscountSelectionProjection> getAllDiscountsByValue(BigDecimal discountValue) {
        if (discountValue == null) {
            throw new NotFoundException( "Valor do desconto nulo!");
        }

        List<DiscountSelectionProjection> discounts = discountRepository.getByDiscountValue(discountValue);

        if (discounts == null || discounts.isEmpty()) {
            throw new NotFoundException( "Nenhum desconto encontrado com este valor!");
        }

        return discounts;
    }

    @Transactional(readOnly = true)
    public List<DiscountSelectionProjection> getAllDiscountsActive(DiscountActive discountActive) {
        return discountRepository.getByActiveDiscount(discountActive);
    }

    @Transactional(readOnly = true)
    public List<DiscountSelectionProjection> getAllDiscountsInactive(DiscountActive discountActive) {
        return discountRepository.getByInativeDiscount(discountActive);
    }

}
