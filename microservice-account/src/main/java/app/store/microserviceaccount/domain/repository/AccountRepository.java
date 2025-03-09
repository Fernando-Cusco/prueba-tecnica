package app.store.microserviceaccount.domain.repository;

import app.store.microserviceaccount.domain.entity.AccountEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends CrudRepository<AccountEntity, Long> {
    List<AccountEntity> findAllByClientId(Long clientId);
}
