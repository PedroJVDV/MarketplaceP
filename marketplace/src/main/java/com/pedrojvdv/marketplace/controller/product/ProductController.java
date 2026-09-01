package com.pedrojvdv.marketplace.controller.product;

import com.pedrojvdv.marketplace.dto.Product.ProductDto;
import com.pedrojvdv.marketplace.exception.BadRequestException;
import com.pedrojvdv.marketplace.exception.NotFoundException;
import com.pedrojvdv.marketplace.service.Product.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/v1/product")
@RequiredArgsConstructor
@Validated
public class ProductController {

    private final ProductService productService;

    //POST
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@Valid @RequestBody ProductDto productDto)throws BadRequestException {
        productService.createProduct(productDto);
    }

    @PutMapping("/{productId}")
    @ResponseStatus(HttpStatus.OK)
    public void updateProduct(@Valid @RequestBody ProductDto productDto, @PathVariable("productId") Long productId) throws NotFoundException {
        productService.updateProduct(productDto, productId);
    }

    @DeleteMapping("/{productId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable("productId") Long id) throws NotFoundException {
        productService.deleteProduct(id);
    }

    //GET
    @GetMapping("/all-products")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductDto> findAll()throws NotFoundException {
        return productService.getAllProducts();
    }

    @GetMapping("/price-filter-bet")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductDto> findByBetweenFilter(@RequestParam BigDecimal minPrice, @RequestParam BigDecimal maxPrice) throws NotFoundException {
        return productService.getByPriceBetween(minPrice, maxPrice);
    }

    @GetMapping("/price-fitler-gre")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductDto> findByGreaterThan(@RequestParam BigDecimal price) throws NotFoundException {
         return productService.getByPriceGreaterThan(price);
    }

    @GetMapping("/price-filter-les")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductDto> findByLessThan(@RequestParam BigDecimal price) throws NotFoundException {
        return productService.getByPriceLessThan(price);
    }

    @GetMapping("/price")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductDto> findByPrice(@RequestParam BigDecimal price)throws NotFoundException{
        return productService.getByPrice(price);
    }

    @GetMapping("/quantity")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductDto> findByQuantity(@RequestParam Integer quantity)throws NotFoundException{
        return productService.getByQuantity(quantity);
    }

    @GetMapping("/discount-products")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductDto> findByDiscountProducts()throws NotFoundException{
        return productService.getProductWithDiscount();
    }

    @GetMapping("/no-discount-products")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductDto> findByNoDiscountProducts()throws NotFoundException{
        return productService.getProductWithoutDiscount();
    }

}
