package com.jeanvisoski.abasteceaiapp.repository;

import com.jeanvisoski.abasteceaiapp.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<UserEntity, Long> {

    Optional<UserEntity> findByEmail (String email);

    Boolean existsByEmail (String email);

    Boolean existsByNameAndSurname (String name, String surname);
}
