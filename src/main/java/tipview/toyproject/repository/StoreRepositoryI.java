package tipview.toyproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tipview.toyproject.domain.Store;

public interface StoreRepositoryI extends JpaRepository<Store,Long> {
}
