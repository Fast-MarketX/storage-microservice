package fast.market.storage_microservice.repository;

import fast.market.storage_microservice.entity.Storage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StorageRepository extends JpaRepository<Storage, Long> {

    boolean existsByStorageName(String storageName);
}
