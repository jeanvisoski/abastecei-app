package com.jeanvisoski.abasteceaiapp.repository;

import com.jeanvisoski.abasteceaiapp.entity.ConfirmationTokenEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConfirmationTokenRepository extends CrudRepository<ConfirmationTokenEntity, Long> {

    Optional<ConfirmationTokenEntity> findByConfirmationToken(String token);

}
