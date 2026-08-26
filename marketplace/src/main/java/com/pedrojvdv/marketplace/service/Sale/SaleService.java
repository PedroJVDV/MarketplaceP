package com.pedrojvdv.marketplace.service.Sale;

import com.pedrojvdv.marketplace.database.model.Sale.SaleEntity;

import com.pedrojvdv.marketplace.database.repository.Sale.ISaleRepository;
import com.pedrojvdv.marketplace.database.repository.User.IUserRepository;
import com.pedrojvdv.marketplace.dto.Sale.SaleDto;
import com.pedrojvdv.marketplace.dto.Wish.WishListDto;
import com.pedrojvdv.marketplace.exception.BadRequestException;
import com.pedrojvdv.marketplace.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SaleService {

    private final ISaleRepository saleRepository;
    private final IUserRepository userRepository;


    @Transactional(rollbackFor = Exception.class)
    public void createSale(SaleDto saleDto) throws NotFoundException {
        saleRepository.findBySaleLocation(saleDto.getSaleLocation())
                .ifPresent(sale -> saleRepository.save(SaleEntity.builder()
                        .saleLocation(saleDto.getSaleLocation())
                        .quantity(saleDto.getQuantity())
                        .publishDate(saleDto.getPublishDate())
                        .build()));
    }

    public void updateSale(SaleDto saleDto) throws NotFoundException {
        saleRepository.findById(saleDto.getUserId())
                .ifPresentOrElse(sale -> {
                            sale.setSaleLocation(saleDto.getSaleLocation());
                            sale.setQuantity(saleDto.getQuantity());
                            sale.setPublishDate(saleDto.getPublishDate());
                            saleRepository.save(sale);
                        },
                        () -> {
                            throw new NotFoundException("Venda não encontrada!");
                        });
    }

    public void deleteSale(Long id) throws NotFoundException {
        saleRepository.findById(id)
                .ifPresentOrElse(saleRepository::delete,
                        () -> {
                            throw new NotFoundException("Venda não encontrada!");
                        });
    }

    public Optional<SaleDto> getSalesById(Long id) throws NotFoundException {

        Optional<SaleDto> sale = saleRepository.findById(id)
                .stream()
                .map(this::toDto)
                .findFirst();
        if (sale.isPresent()) {
            return sale;
        }
        throw new NotFoundException("Venda não encontrada!");
    }

    public List<SaleDto> getAllSales() throws NotFoundException {

        List<SaleDto> sale = saleRepository.findAll()
                .stream()
                .map(this::toDto)
                .toList();
        if (sale.isEmpty()) {
            throw new NotFoundException("Nenhuma venda encontrada no sistema!");
        }
        return sale;
    }

    public List<SaleDto> getSaleByUserId(Long userId) throws NotFoundException {

        List<SaleDto> sale = saleRepository.findByUsers_Id(userId)
                .stream()
                .map(this::toDto)
                .toList();
        if (userRepository.existsById(userId)) {
            return sale;
        } else {
            throw new NotFoundException("Usuário não encontrado!");
        }
    }

    public List<SaleDto> getByPublishDate(LocalDateTime publishDate) throws NotFoundException {

        List<SaleDto> sale = saleRepository.findByPublishDate(publishDate)
                .stream()
                .map(this::toDto)
                .toList();
        if (sale.isEmpty()) {
            throw new NotFoundException("Nenhuma venda encontrada com a data especificada!");
        }
        return sale;
    }

    public List<SaleDto> getByQuantity(Integer quantity) throws NotFoundException {

        List<SaleDto> sale = saleRepository.findByQuantity(quantity)
                .stream()
                .map(this::toDto)
                .toList();
        if (sale.isEmpty()) {
            throw new NotFoundException("Nenhuma venda encontrada com a quantidade especificada!");
        }
        return sale;
    }

    public List<SaleDto> getSaleByCep(String cepNumber) throws NotFoundException, BadRequestException {

        List<SaleDto> sale = saleRepository.findByCep_Number(cepNumber)
                .stream()
                .map(this::toDto)
                .toList();
        if (sale.isEmpty()) {
            throw new NotFoundException("Nenhuma venda encontrada com o CEP especificado!");
        }

        if (saleRepository.findByCep_Number(cepNumber).size() > 5) {
            throw new BadRequestException("Número do CEP está incorreto!");
        }
        return sale;
    }

    public List<SaleDto> getSaleByCity(String city) throws NotFoundException, BadRequestException {

        List<SaleDto> sale = saleRepository.findByCity_Name(city)
                .stream()
                .map(this::toDto)
                .toList();
        if (sale.isEmpty()) {
            throw new NotFoundException("Nenhuma venda encontrada com a cidade especificada!");
        }
        return sale;
    }

    public List<SaleDto> getByFullAdress(String cep, String city, String hood, String streetName, Integer houseNumber) throws NotFoundException, BadRequestException {

        List<SaleDto> sale = saleRepository.findByAdress(cep, city, hood, streetName, houseNumber)
                .stream()
                .map(this::toDto)
                .toList();
        if (sale.isEmpty()) {
            throw new NotFoundException("Nenhuma venda encontrada com este endereço!");
        }
        return sale;
    }

    private SaleDto toDto(SaleEntity p) {
        SaleDto dto = new SaleDto();

        dto.setPublishDate(p.getPublishDate());
        dto.setQuantity(p.getQuantity());
        dto.setSaleLocation(p.getSaleLocation());
        dto.setProductId(p.getProduct().getId());
        dto.setUserId(p.getUsers().getId());
        dto.setDiscountId(p.getDiscount().getId());

        return dto;
    }

}