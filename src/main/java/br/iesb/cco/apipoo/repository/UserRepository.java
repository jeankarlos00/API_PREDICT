package br.iesb.cco.apipoo.repository;

import br.iesb.cco.apipoo.model.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends MongoRepository<UserEntity, Long> {

    List<UserEntity> findByEmailContaining(String email);
    Optional<UserEntity> findUserEntityByEmail(String email);

}