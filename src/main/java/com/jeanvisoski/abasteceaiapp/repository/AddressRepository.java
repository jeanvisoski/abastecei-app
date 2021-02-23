package com.jeanvisoski.abasteceaiapp.repository;

import com.jeanvisoski.abasteceaiapp.entity.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<AddressEntity, Integer> {

    Optional<AddressEntity> findByCep(String cep);

    Boolean existsByCep (String cep);

    Boolean existsByBairroAndCepAndCidadeAndEnderecoAndNumeroAndUf (String bairro,
                                                                    String cep,
                                                                    String cidade,
                                                                    String endereco,
                                                                    Integer numero,
                                                                    String uf);

}
