package food.delivery.infra;

import food.delivery.domain.*;
import java.util.List;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "infos", path = "infos")
public interface InfoRepository
    extends PagingAndSortingRepository<Info, Long> {}
