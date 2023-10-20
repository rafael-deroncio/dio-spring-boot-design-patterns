package one.digitalinnovation.labdesignpatternsspring.core.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import one.digitalinnovation.labdesignpatternsspring.core.models.AddressModel;

@Repository
public interface AddressRepository extends CrudRepository<AddressModel, String> {

}
