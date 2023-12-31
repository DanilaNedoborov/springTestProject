package searchengine.model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PageTableRepository extends CrudRepository<PageTable, Integer> {
}
