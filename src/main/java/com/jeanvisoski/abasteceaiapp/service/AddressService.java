package com.jeanvisoski.abasteceaiapp.service;

import com.jeanvisoski.abasteceaiapp.dtos.AddressDTO;
import com.jeanvisoski.abasteceaiapp.entity.AddressEntity;
import com.jeanvisoski.abasteceaiapp.repository.AddressRepository;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@AllArgsConstructor
public class AddressService {

    private final AddressRepository repository;

    @Autowired
    private final ModelMapper modelMapper;

    @Transactional
    public AddressEntity saveAddress(AddressDTO dto) {
        log.debug("AddressService.saveAddress - End - Input {}", dto);
        return repository.save(AddressDTO.toEntity(dto));
    }

    @Transactional(readOnly = true)
    @Cacheable(cacheNames = "SearchAddress", key="#cep")
    public AddressEntity searchAddress(String cep) throws NotFoundException {
        log.debug("AddressService.searchAddress - End - Input {}", cep);
        return repository.findByCep(cep).orElseThrow(() -> new NotFoundException("Cep não localizado"));
    }

    @Transactional(readOnly = true)
    @CacheEvict(cacheNames = "SearchAddress", key="#cep")
    public AddressEntity updateAddress(String cep, AddressDTO dto) throws NotFoundException {
        log.debug("AddressService.updateAddress - End - Input {}", dto);
        return repository.findByCep(cep)
                .map(record -> {
                    record.setBairro(dto.getBairro());
                    record.setCep(dto.getCep());
                    record.setCidade(dto.getCidade());
                    record.setEndereco(dto.getEndereco());
                    record.setNumero(dto.getNumero());
                    record.setUf(dto.getUf());
                    return repository.save(record);
                }).orElseThrow(() -> new NotFoundException("Cep não localizado"));
    }

    @Transactional(readOnly = true)
    @CacheEvict(cacheNames = "SearchAddress", key="#cep")
    public String deleteAddress(String cep) {
        log.debug("AddressService.deleteAddress - End - Input {}", cep);
        repository.deleteById(repository.findByCep(cep).get().getId());

        return "Deletado com sucesso!";
    }
}
