package food.delivery.infra;

import food.delivery.domain.*;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import java.util.List;

@RepositoryRestResource(collectionResourceRel="infos", path="infos")
public interface InfoRepository extends PagingAndSortingRepository<Info, Long> {

    

    
}
