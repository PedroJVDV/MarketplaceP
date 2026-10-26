package com.pedrojvdv.marketplace.service.Product;

import com.pedrojvdv.marketplace.database.model.Discount.DiscountEntity;
import com.pedrojvdv.marketplace.database.model.Product.ProductEntity;
import com.pedrojvdv.marketplace.database.repository.Discount.IDiscountRepository;
import com.pedrojvdv.marketplace.database.repository.Product.IProductRepository;
import com.pedrojvdv.marketplace.dto.Discount.DiscountDto;
import com.pedrojvdv.marketplace.dto.Product.ProductDto;
import com.pedrojvdv.marketplace.dto.Product.ProductResponseDto;
import com.pedrojvdv.marketplace.exception.NotFoundException;
import com.pedrojvdv.marketplace.exception.BadRequestException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final IProductRepository productRepository;
    private final IDiscountRepository discountRepository;

    @Transactional(rollbackFor = Exception.class)
    public void createProduct(ProductDto productDto) throws BadRequestException {

        productRepository.save(ProductEntity.builder()
                .name(productDto.getName())
                .price(productDto.getPrice())
                .quantity(productDto.getQuantity())
                .build());
    }

    //TICKETS
    @Transactional(rollbackFor = Exception.class)
    public void addDiscountIntoProduct(Long productId, Long discountId) throws NotFoundException {

        ProductEntity product = productRepository.findById(productId)
                .orElseThrow(() -> new NotFoundException("Produto não encontrado!"));

        DiscountEntity discount = discountRepository.findById(discountId)
                .orElseThrow(() -> new NotFoundException("Desconto não encontrado!"));

        product.setDiscount(discount);
        productRepository.save(product);
    }

    @Transactional(rollbackFor = Exception.class)
    public void updateProduct(ProductResponseDto productResponseDto, Long id) throws BadRequestException {
        ProductEntity product = productRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Produto não encontrado!"));

        if (productResponseDto.getPrice() != null) {
            product.setPrice(productResponseDto.getPrice());
        }
        if (productResponseDto.getQuantity() != null) {
            product.setQuantity(productResponseDto.getQuantity());
        }
        productRepository.save(product);
    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteProduct(Long id) throws BadRequestException {
        ProductEntity product = productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("ID do produto inexistente"));
        productRepository.delete(product);
    }

    @Transactional(readOnly = true)
    public List<ProductResponseDto> getAllProducts() {
        List<ProductResponseDto> product = productRepository.findAll()
                .stream()
                .map(this::toDtoResponse)
                .toList();
        if (product.isEmpty()) {
            throw new NotFoundException("Nenhum produto encontrado!");
        }
        return product;
    }

    @Transactional(readOnly = true)
    public List<ProductResponseDto> getByPriceBetween(BigDecimal priceMin, BigDecimal priceMax) {
        List<ProductResponseDto> product = productRepository.findByPriceBetween(priceMin, priceMax)
                .stream()
                .map(this::toDtoResponse)
                .toList();
        if (product.isEmpty()) {
            throw new NotFoundException("Nenhum produto encontrado!");
        }
        return product;
    }

    @Transactional(readOnly = true)
    public Optional<ProductDto> getProductById(Long id) {
        Optional<ProductDto> product = productRepository.findById(id)
                .map(this::toDto);
        if (product.isEmpty()) {
            throw new NotFoundException("Produto inexistente!");
        }
        return product;
    }

    @Transactional(readOnly = true)
    public List<ProductResponseDto> getByPriceGreaterThan(BigDecimal price) throws NotFoundException {
        List<ProductResponseDto> product = productRepository.findByPriceGreaterThan(price)
                .stream()
                .map(this::toDtoResponse)
                .toList();
        if (product.isEmpty()) {
            throw new NotFoundException("Nenhum produto acima deste preço foi encontrado!");
        }
        return product;
    }

    @Transactional(readOnly = true)
    public List<ProductResponseDto> getByPriceLessThan(BigDecimal price) throws NotFoundException {
        List<ProductResponseDto> product = productRepository.findByPriceLessThan(price)
                .stream()
                .map(this::toDtoResponse)
                .toList();
        if (product.isEmpty()) {
            throw new NotFoundException("Nenhum abaixo deste preço foi encontrado!");
        }
        return product;
    }

    @Transactional(readOnly = true)
    public List<ProductResponseDto> getByPrice(BigDecimal price) throws NotFoundException {
        List<ProductResponseDto> product = productRepository.findByPrice(price)
                .stream()
                .map(this::toDtoResponse)
                .toList();
        if (product.isEmpty()) {
            throw new NotFoundException("Nenhum produto com este preço foi encontrado!");
        }
        return product;
    }

    @Transactional(readOnly = true)
    public List<ProductResponseDto> getByName(String name) throws NotFoundException {
        List<ProductResponseDto> product = productRepository.findByName(name)
                .stream()
                .map(this::toDtoResponse)
                .toList();
        if (product.isEmpty()) {
            throw new NotFoundException("Nenhum produto com o nome informado!");
        }
        return product;
    }

    @Transactional(readOnly = true)
    public List<ProductResponseDto> getByQuantity(Integer quantity) throws NotFoundException {
        List<ProductResponseDto> product = productRepository.findByQuantity(quantity)
                .stream()
                .map(this::toDtoResponse)
                .toList();
        if (product.isEmpty()) {
            throw new NotFoundException("Nenhum produto com a quantidade informada!");
        }
        return product;
    }

    @Transactional(readOnly = true)
    public List<ProductResponseDto> getProductWithDiscount() throws NotFoundException {
        List<ProductResponseDto> product = productRepository.getProductWithDiscount()
                .stream()
                .map(this::toDtoResponse)
                .toList();
        if (product.isEmpty()) {
            throw new NotFoundException("Nenhum produto com desconto encontrado!");
        }
        return product;
    }

    @Transactional(readOnly = true)
    public List<ProductResponseDto> getProductWithoutDiscount() throws NotFoundException {
        List<ProductResponseDto> product = productRepository.getProductWithoutDiscount()
                .stream()
                .map(this::toDtoResponse)
                .toList();
        if (product.isEmpty()) {
            throw new NotFoundException("nenhum produto sem desconto encontrado!");
        }
        return product;
    }

    private ProductDto toDto(ProductEntity p) {
        ProductDto dto = new ProductDto();
        dto.setName(p.getName());
        dto.setPrice(p.getPrice());
        dto.setQuantity(p.getQuantity());
        dto.setId(p.getId());
        dto.setDiscountId(p.getDiscount().getId());

        return dto;
    }

    private ProductResponseDto toDtoResponse(ProductEntity p) {
        ProductResponseDto dto = new ProductResponseDto();
        dto.setName(p.getName());
        dto.setPrice(p.getPrice());
        dto.setQuantity(p.getQuantity());
        return dto;
    }

}
