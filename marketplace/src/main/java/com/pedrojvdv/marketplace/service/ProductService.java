package com.pedrojvdv.marketplace.service;

import com.pedrojvdv.marketplace.database.model.ProductEntity;
import com.pedrojvdv.marketplace.database.repository.IDiscountRepository;
import com.pedrojvdv.marketplace.database.repository.IProductRepository;
import com.pedrojvdv.marketplace.dto.ProductDto;
import com.pedrojvdv.marketplace.exception.NotFoundException;
import com.pedrojvdv.marketplace.exception.BadRequestException;
import lombok.RequiredArgsConstructor;
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

    @Transactional(rollbackFor = Exception.class)
    public void createProduct(ProductDto productDto) throws BadRequestException {
        ProductEntity product = productRepository.findById(productDto.getId())
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
        ProductEntity product = productRepository.findById(productDto.getId())
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
    public List<ProductEntity> getAllProducts() {
        List<ProductEntity> product = productRepository.getAllProduct();

        if (product.isEmpty()) {
            throw new NotFoundException("Nenhum produto encontrado!");
        }
        return product;
    }

    @Transactional(readOnly = true)
    public List<ProductEntity> getByPriceBetween(BigDecimal priceMin, BigDecimal priceMax) {
        List<ProductEntity> product = productRepository.findByPriceBetween(priceMin, priceMax);
        if (product.isEmpty()) {
            throw new NotFoundException("Nenhum produto encontrado!");
        }
        return product;
    }

    @Transactional(readOnly = true)
    public Optional<ProductEntity> getProductById(Long id) {
        Optional<ProductEntity> product = productRepository.findById(id);

        if (product.isEmpty()) {
            throw new NotFoundException("Produto inexistente!");
        }
        return product;
    }

    @Transactional(readOnly = true)
    public List<ProductEntity> getByPriceGreaterThan(BigDecimal price) throws NotFoundException {
        List<ProductEntity> product = productRepository.findByPriceGreaterThan(price);
        if (product.isEmpty()) {
            throw new NotFoundException("Nenhum produto acima deste preço foi encontrado!");
        }
        return product;
    }

    @Transactional(readOnly = true)
    public List<ProductEntity> getByPriceLessThan(BigDecimal price) throws NotFoundException {
        List<ProductEntity> product = productRepository.findByPriceLessThan(price);

        if (product.isEmpty()) {
            throw new NotFoundException("Nenhum abaixo deste preço foi encontrado!");
        }
        return product;
    }

    @Transactional(readOnly = true)
    public List<ProductEntity> getByPrice(BigDecimal price) throws NotFoundException {
        List<ProductEntity> product = productRepository.findByPrice(price);

        if (product.isEmpty()) {
            throw new NotFoundException("Nenhum produto com este preço foi encontrado!");
        }
        return product;
    }

    @Transactional(readOnly = true)
    public List<ProductEntity> getByName(String name) throws NotFoundException {
        List<ProductEntity> product = productRepository.findByName(name);

        if (product.isEmpty()) {
            throw new NotFoundException("Nenhum produto com o nome informado!");
        }
        return product;
    }

    @Transactional(readOnly = true)
    public List<ProductEntity> getByQuantity(Integer quantity) throws NotFoundException {
        List<ProductEntity> product = productRepository.getByQuantity(quantity);

        if (product.isEmpty()) {
            throw new NotFoundException("Nenhum produto com a quantidade informada!");
        }
        return product;
    }

    @Transactional(readOnly = true)
    public List<ProductEntity> getProductWithDiscount() throws NotFoundException {
        List<ProductEntity> product = productRepository.getProductWithDiscount();

        if (product.isEmpty()) {
            throw new NotFoundException("Nenhum produto com desconto encontrado!");
        }
        return product;
    }

    @Transactional(readOnly = true)
    public List<ProductEntity> getProductWithoutDiscount() throws NotFoundException {
        List<ProductEntity> product = productRepository.getProductWithoutDiscount();

        if (!product.isEmpty()) {
            throw new NotFoundException("Nenenhum produto sem desconto encontrado!");
        }
        return product;
    }
}
