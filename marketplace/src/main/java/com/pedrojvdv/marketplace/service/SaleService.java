package com.pedrojvdv.marketplace.service;

import com.pedrojvdv.marketplace.database.model.SaleEntity;
import com.pedrojvdv.marketplace.database.model.UserEntity;
import com.pedrojvdv.marketplace.database.model.embedabbles.Adress;
import com.pedrojvdv.marketplace.database.repository.ISaleRepository;
import com.pedrojvdv.marketplace.database.repository.IUserRepository;
import com.pedrojvdv.marketplace.dto.SaleDto;
import com.pedrojvdv.marketplace.exception.BadRequestException;
import com.pedrojvdv.marketplace.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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


    //TODO: CHANGES IN THIS LINE -- NEW CREATE....
    @Transactional(rollbackFor = Exception.class)
    public void createSale(SaleDto saleDto) throws NotFoundException {
        saleRepository.findBySaleLocation(saleDto.getSaleLocation())
                .ifPresent(sale -> saleRepository.save(SaleEntity.builder()
                        .saleLocation(saleDto.getSaleLocation())
                        .quantity(saleDto.getQuantity())
                        .publishDate(saleDto.getPublishDate())
                        .build()));
    }

    public void updateSale(SaleDto saleDto, Long id) throws NotFoundException {
        saleRepository.findById(id)
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

    public Optional<SaleEntity> getSalesById(Long id) throws NotFoundException {

        Optional<SaleEntity> sale = saleRepository.findById(id);

        if (sale.isPresent()) {
            return sale;
        }
        throw new NotFoundException("Venda não encontrada!");
    }

    public List<SaleEntity> getAllSales() throws NotFoundException {

        List<SaleEntity> sale = saleRepository.findAll();

        if (sale.isEmpty()) {
            throw new NotFoundException("Nenhuma venda encontrada no sistema!");
        }
        return sale;
    }

    public List<SaleEntity> getSaleByUserId(Long userId) throws NotFoundException {

        List<SaleEntity> sale = saleRepository.findByUser_Id(userId);

        if (userRepository.existsById(userId)) {
            return sale;
        } else {
            throw new NotFoundException("Usuário não encontrado!");
        }
    }

    public List<SaleEntity> getByPublishDate(LocalDateTime publishDate) throws NotFoundException {

        List<SaleEntity> sale = saleRepository.findByPublishDate(publishDate);

        if (sale.isEmpty()) {
            throw new NotFoundException("Nenhuma venda encontrada com a data especificada!");
        }
        return sale;
    }

    public List<SaleEntity> getByQuantity(Integer quantity) throws NotFoundException {

        List<SaleEntity> sale = saleRepository.findByQuantity(quantity);

        if (sale.isEmpty()) {
            throw new NotFoundException("Nenhuma venda encontrada com a quantidade especificada!");
        }
        return sale;
    }

    public List<SaleEntity> getSaleByCep(String cepNumber) throws NotFoundException, BadRequestException {

        List<SaleEntity> sale = saleRepository.findByCep_Number(cepNumber);

        if (sale.isEmpty()) {
            throw new NotFoundException("Nenhuma venda encontrada com o CEP especificado!");
        }

        if (saleRepository.findByCep_Number(cepNumber).size() > 5) {
            throw new BadRequestException("Número do CEP está incorreto!");
        }
        return sale;
    }

    public List<SaleEntity> getSaleByCity(String city) throws NotFoundException, BadRequestException {

        List<SaleEntity> sale = saleRepository.findByCity_Name(city);

        if (sale.isEmpty()) {
            throw new NotFoundException("Nenhuma venda encontrada com a cidade especificada!");
        }
        return sale;
    }

    public List<SaleEntity> getByFullAdress(String cep, String city, String hood, String streetName, Integer houseNumber) throws NotFoundException, BadRequestException {

        List<SaleEntity> sale = saleRepository.findByAdress(cep, city, hood, streetName, houseNumber);

        if (sale.isEmpty()) {
            throw new NotFoundException("Nenhuma venda encontrada com este endereço!");
        }
        return sale;
    }

}