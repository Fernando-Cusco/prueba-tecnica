package app.store.microserviceclient.domain.repository;

import app.store.microserviceclient.domain.entity.PersonEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends CrudRepository<PersonEntity, Long> {
}
