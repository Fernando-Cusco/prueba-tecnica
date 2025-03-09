package app.store.microserviceaccount.domain.repository;

import app.store.microserviceaccount.domain.entity.MovementEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MovementRepository extends CrudRepository<MovementEntity, Long> {

}
