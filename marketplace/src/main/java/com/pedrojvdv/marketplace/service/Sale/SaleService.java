package com.pedrojvdv.marketplace.service.Sale;

import com.pedrojvdv.marketplace.database.model.Discount.DiscountEntity;
import com.pedrojvdv.marketplace.database.model.Product.ProductEntity;
import com.pedrojvdv.marketplace.database.model.Sale.SaleEntity;

import com.pedrojvdv.marketplace.database.model.User.UserEntity;
import com.pedrojvdv.marketplace.database.repository.Discount.IDiscountRepository;
import com.pedrojvdv.marketplace.database.repository.Product.IProductRepository;
import com.pedrojvdv.marketplace.database.repository.Sale.ISaleRepository;
import com.pedrojvdv.marketplace.database.repository.User.IUserRepository;
import com.pedrojvdv.marketplace.dto.Sale.SaleDto;
import com.pedrojvdv.marketplace.dto.Sale.SaleResponseDto;
import com.pedrojvdv.marketplace.enums.User.UserRole;
import com.pedrojvdv.marketplace.exception.BadRequestException;
import com.pedrojvdv.marketplace.exception.NotFoundException;
import com.pedrojvdv.marketplace.service.Auth.AuthenticatedUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SaleService {

    private final ISaleRepository saleRepository;
    private final IUserRepository userRepository;
    private final IProductRepository productRepository;
    private final IDiscountRepository discountRepository;
    private final AuthenticatedUserService authenticatedUserService;

    @Transactional(rollbackFor = Exception.class)
    @PreAuthorize("hasRole('SELLER') or hasRole('ADMIN')")
    public void createSale(SaleDto saleDto) throws NotFoundException, BadRequestException {

        UserEntity user = authenticatedUserService.getCurrentAuthenticatedUser();

        ProductEntity product = productRepository.findById(saleDto.getProductId())
                .orElseThrow(() -> new NotFoundException("Produto não encontrado!"));

        DiscountEntity discount = discountRepository.findById(saleDto.getDiscountId())
                .orElseThrow(() -> new NotFoundException("Desconto não encontrado!"));

        if (user.getRole() != UserRole.SELLER && user.getRole() != UserRole.ADMIN) {
            throw new BadRequestException("Usuário sem permissão de vendedor!");
        }

        SaleEntity saleEntity = SaleEntity.builder()
                .saleLocation(saleDto.getSaleLocation())
                .quantity(saleDto.getQuantity())
                .publishDate(LocalDateTime.now())
                .product(product)
                .users(user)
                .discount(discount)
                .build();
        saleRepository.save(saleEntity);
    }

    @Transactional(rollbackFor = Exception.class)
    @PreAuthorize("hasRole('SELLER') or hasRole('ADMIN')")
    public void updateSale(SaleDto saleDto) throws NotFoundException {
        saleRepository.findById(saleDto.getUserId())
                .ifPresentOrElse(sale -> {
                            sale.setSaleLocation(saleDto.getSaleLocation());
                            sale.setQuantity(saleDto.getQuantity());
                            sale.setPublishDate(saleDto.getPublishDate().toLocalDate().atTime(LocalTime.now()));
                            saleRepository.save(sale);
                        },
                        () -> {
                            throw new NotFoundException("Venda não encontrada!");
                        });
    }

    @Transactional(rollbackFor = Exception.class)
    @PreAuthorize("hasRole('SELLER') or hasRole('ADMIN')")
    public void deleteSale(Long id) throws NotFoundException {
        saleRepository.findById(id)
                .ifPresentOrElse(saleRepository::delete,
                        () -> {
                            throw new NotFoundException("Venda não encontrada!");
                        });
    }

    @PreAuthorize("isAuthenticated() and hasRole('SELLER') or hasRole('ADMIN')")
    @Transactional(readOnly = true)
    public Optional<SaleResponseDto> getSalesById(Long id) throws NotFoundException {

        Optional<SaleResponseDto> sale = saleRepository.findById(id)
                .stream()
                .map(this::toDtoFromUser)
                .findFirst();
        if (sale.isPresent()) {
            return sale;
        } else {
            throw new NotFoundException("Venda não encontrada!");
        }
    }

    @PreAuthorize("permitAll()")
    @Transactional(readOnly = true)
    public List<SaleResponseDto> getAllSales() throws NotFoundException {

        List<SaleResponseDto> sale = saleRepository.findAll()
                .stream()
                .map(this::toDtoFromUser)
                .toList();
        if (sale.isEmpty()) {
            throw new NotFoundException("Nenhuma venda encontrada no sistema!");
        }
        return sale;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Transactional(readOnly = true)
    public Optional<SaleDto> getSaleByUserId(Long userId) throws NotFoundException {

        Optional<SaleDto> sale = saleRepository.findByUsers_Id(userId)
                .stream()
                .map(this::toDto)
                .findFirst();
        if (userRepository.existsById(userId)) {
            return sale;
        } else {
            throw new NotFoundException("Usuário com ID informado não encontrado!");
        }
    }

    @PreAuthorize("permitAll()")
    @Transactional(readOnly = true)
    public List<SaleResponseDto> getByPublishDate(LocalDate publishDate) throws NotFoundException {
        LocalDateTime startOfDay = publishDate.atStartOfDay();
        LocalDateTime startOfNextDay = publishDate.plusDays(1).atStartOfDay();

        List<SaleResponseDto> sale = saleRepository.findByPublishDateGreaterThanEqualAndPublishDateLessThan
                        (startOfDay, startOfNextDay)
                .stream()
                .map(this::toDtoFromUser)
                .toList();
        if (sale.isEmpty()) {
            throw new NotFoundException("Nenhuma venda encontrada com a data especificada!");
        }
        return sale;
    }

    @PreAuthorize("permitAll()")
    @Transactional(readOnly = true)
    public List<SaleResponseDto> getByQuantity(Integer quantity) throws NotFoundException {

        List<SaleResponseDto> sale = saleRepository.findByQuantity(quantity)
                .stream()
                .map(this::toDtoFromUser)
                .toList();
        if (sale.isEmpty()) {
            throw new NotFoundException("Nenhuma venda encontrada com a quantidade especificada!");
        }
        return sale;
    }

    @PreAuthorize("permitAll()")
    @Transactional(readOnly = true)
    public List<SaleResponseDto> getSaleByCep(String cepNumber) throws NotFoundException, BadRequestException {

        List<SaleResponseDto> sale = saleRepository.findByCep_Number(cepNumber)
                .stream()
                .map(this::toDtoFromUser)
                .toList();
        if (sale.isEmpty()) {
            throw new NotFoundException("Nenhuma venda encontrada com o CEP especificado!");
        }

        if (saleRepository.findByCep_Number(cepNumber).size() > 5) {
            throw new BadRequestException("Número do CEP está incorreto!");
        }
        return sale;
    }

    @PreAuthorize("permitAll()")
    @Transactional(readOnly = true)
    public List<SaleResponseDto> getSaleByCity(String city) throws NotFoundException, BadRequestException {

        List<SaleResponseDto> sale = saleRepository.findByCity_Name(city)
                .stream()
                .map(this::toDtoFromUser)
                .toList();
        if (sale.isEmpty()) {
            throw new NotFoundException("Nenhuma venda encontrada com a cidade especificada!");
        }
        return sale;
    }

    @PreAuthorize("permitAll()")
    @Transactional(readOnly = true)
    public List<SaleResponseDto> getSalesByFullAdress(String cep, String city, String hood, String streetName, String
            houseNumber) throws NotFoundException, BadRequestException {

        List<SaleResponseDto> sale = saleRepository.findByAdress(cep, city, hood, streetName, houseNumber)
                .stream()
                .map(this::toDtoFromUser)
                .collect(Collectors.toList());
        if (sale.isEmpty()) {
            throw new NotFoundException("Nenhuma venda encontrada com este endereço!");
        }
        return sale;
    }

    private SaleResponseDto toDtoFromUser(SaleEntity p) {
        SaleResponseDto dto = new SaleResponseDto();

        dto.setPublishDate(p.getPublishDate());
        dto.setQuantity(p.getQuantity());
        dto.setSaleLocation(p.getSaleLocation());
        dto.setUsername(p.getUsers().getUsername());
        dto.setProductName(p.getProduct().getName());
        dto.setDiscountValue(p.getDiscount().getDiscountValue());

        return dto;
    }

    private SaleDto toDto(SaleEntity p) {
        SaleDto dto = new SaleDto();

        dto.setPublishDate(p.getPublishDate());
        dto.setQuantity(p.getQuantity());
        dto.setSaleLocation(p.getSaleLocation());
        dto.setProductId(p.getProduct().getId());
        dto.setDiscountId(p.getDiscount().getId());
        dto.setUserId(p.getUsers().getId());

        return dto;
    }

}