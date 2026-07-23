package com.pedrojvdv.marketplace.service;

import com.pedrojvdv.marketplace.database.model.ProductEntity;
import com.pedrojvdv.marketplace.database.repository.IDiscountRepository;
import com.pedrojvdv.marketplace.database.repository.IProductRepository;
import com.pedrojvdv.marketplace.dto.ProductDto;
import com.pedrojvdv.marketplace.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final IProductRepository productRepository;
    private final IDiscountRepository discountRepository;

    @Transactional(rollbackFor = Exception.class)
    public void createProduct(ProductDto productDto) throws BadRequestException {

        ProductEntity product = productRepository.findByName(productDto.getName())
                .orElse(null);
        if (product != null) {
            throw new BadRequestException("Já existe um produto com este nome!");
        }

        productRepository.save(ProductEntity.builder()
                .name(productDto.getName())
                .price(productDto.getPrice())
                .quantity(productDto.getQuantity())
                .build());
    }

    @Transactional(rollbackFor = Exception.class)
    public void updateProduct(ProductDto productDto) throws BadRequestException {
        ProductEntity product = productRepository.findByName(productDto.getName())
                .orElseThrow(() -> new BadRequestException("Produto não encontrado!"));

        if (productDto.getPrice() != null) {
            product.setPrice(productDto.getPrice());
        }
        if (productDto.getQuantity() != null) {
            product.setQuantity(productDto.getQuantity());
        }
        productRepository.save(product);
    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteProduct(Long id) throws BadRequestException {
        ProductEntity product = productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("ID do produto inexistente"));
        productRepository.delete(product);
    }

    public List<ProductDto> getAllProducts() {
        return productRepository.getAllProduct()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public List<ProductDto> getByPriceBetween(BigDecimal priceMin, BigDecimal priceMax) {
        return productRepository.findByPriceBetween(priceMin, priceMax)
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public List<ProductDto> getProductById(Long id){
        return productRepository.findById(id)
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public List<ProductDto> getByPriceGreaterThan(BigDecimal price) {
        return productRepository.findByPriceGreaterThan(price)
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public List<ProductDto> getByPriceLessThan(BigDecimal price) {
        return productRepository.findByPriceLessThan(price)
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public List<ProductDto> getByPrice(BigDecimal price) {
        return productRepository.findByPrice(price)
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public List<ProductDto> getByName(String name){
        return productRepository.findByName(name)
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public List<ProductDto> getByQuantity(Integer quantity){
        return productRepository.getByQuantity(quantity)
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public List<ProductDto> getProductWithDiscount(){
        return productRepository.getProductWithDiscount()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public List<ProductDto> getProductWithoutDiscount(){
        return productRepository.getProductWithoutDiscount()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private ProductDto convertToDto(ProductEntity entity) {
        return new ProductDto(entity.getName(), entity.getPrice(), entity.getQuantity());
    }
}
