package one.digitalinnovation.labdesignpatternsspring.core.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import one.digitalinnovation.labdesignpatternsspring.core.models.ClientModel;

@Repository
public interface ClientRepository extends CrudRepository<ClientModel, Integer> {

}
