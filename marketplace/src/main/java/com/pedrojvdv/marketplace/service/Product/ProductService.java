package com.pedrojvdv.marketplace.service.Product;

import com.pedrojvdv.marketplace.database.model.Product.ProductEntity;
import com.pedrojvdv.marketplace.database.repository.Product.IProductRepository;
import com.pedrojvdv.marketplace.dto.Product.ProductDto;
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

    @Transactional(rollbackFor = Exception.class)
    public void createProduct(ProductDto productDto) throws BadRequestException {

        productRepository.save(ProductEntity.builder()
                .name(productDto.getName())
                .price(productDto.getPrice())
                .quantity(productDto.getQuantity())
                .build());
    }

    @Transactional(rollbackFor = Exception.class)
    public void updateProduct(ProductDto productDto, Long id) throws BadRequestException {
        ProductEntity product = productRepository.findById(id)
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

    @Transactional(readOnly = true)
    public List<ProductDto> getAllProducts() {
        List<ProductDto> product = productRepository.findAll()
                .stream()
                .map(this::toDto)
                .toList();
        if (product.isEmpty()) {
            throw new NotFoundException("Nenhum produto encontrado!");
        }
        return product;
    }

    @Transactional(readOnly = true)
    public List<ProductDto> getByPriceBetween(BigDecimal priceMin, BigDecimal priceMax) {
        List<ProductDto> product = productRepository.findByPriceBetween(priceMin, priceMax)
                .stream()
                .map(this::toDto)
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
    public List<ProductDto> getByPriceGreaterThan(BigDecimal price) throws NotFoundException {
        List<ProductDto> product = productRepository.findByPriceGreaterThan(price)
                .stream()
                .map(this::toDto)
                .toList();
        if (product.isEmpty()) {
            throw new NotFoundException("Nenhum produto acima deste preço foi encontrado!");
        }
        return product;
    }

    @Transactional(readOnly = true)
    public List<ProductDto> getByPriceLessThan(BigDecimal price) throws NotFoundException {
        List<ProductDto> product = productRepository.findByPriceLessThan(price)
                .stream()
                .map(this::toDto)
                .toList();
        if (product.isEmpty()) {
            throw new NotFoundException("Nenhum abaixo deste preço foi encontrado!");
        }
        return product;
    }

    @Transactional(readOnly = true)
    public List<ProductDto> getByPrice(BigDecimal price) throws NotFoundException {
        List<ProductDto> product = productRepository.findByPrice(price)
                .stream()
                .map(this::toDto)
                .toList();
        if (product.isEmpty()) {
            throw new NotFoundException("Nenhum produto com este preço foi encontrado!");
        }
        return product;
    }

    @Transactional(readOnly = true)
    public List<ProductDto> getByName(String name) throws NotFoundException {
        List<ProductDto> product = productRepository.findByName(name)
                .stream()
                .map(this::toDto)
                .toList();
        if (product.isEmpty()) {
            throw new NotFoundException("Nenhum produto com o nome informado!");
        }
        return product;
    }

    @Transactional(readOnly = true)
    public List<ProductDto> getByQuantity(Integer quantity) throws NotFoundException {
        List<ProductDto> product = productRepository.findByQuantity(quantity)
                .stream()
                .map(this::toDto)
                .toList();
        if (product.isEmpty()) {
            throw new NotFoundException("Nenhum produto com a quantidade informada!");
        }
        return product;
    }

    @Transactional(readOnly = true)
    public List<ProductDto> getProductWithDiscount() throws NotFoundException {
        List<ProductDto> product = productRepository.getProductWithDiscount()
                .stream()
                .map(this::toDto)
                .toList();
        if (product.isEmpty()) {
            throw new NotFoundException("Nenhum produto com desconto encontrado!");
        }
        return product;
    }

    @Transactional(readOnly = true)
    public List<ProductDto> getProductWithoutDiscount() throws NotFoundException {
        List<ProductDto> product = productRepository.getProductWithoutDiscount()
                .stream()
                .map(this::toDto)
                .toList();
        if (!product.isEmpty()) {
            throw new NotFoundException("Nenenhum produto sem desconto encontrado!");
        }
        return product;
    }

    private ProductDto toDto(ProductEntity p) {
        ProductDto dto = new ProductDto();
        dto.setName(p.getName());
        dto.setPrice(p.getPrice());
        dto.setQuantity(p.getQuantity());
        return dto;
    }

}
