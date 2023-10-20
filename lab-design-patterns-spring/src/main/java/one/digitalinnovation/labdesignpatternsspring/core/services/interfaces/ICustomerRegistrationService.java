package one.digitalinnovation.labdesignpatternsspring.core.services.interfaces;

import java.util.List;

import one.digitalinnovation.labdesignpatternsspring.domain.requests.ClientRequest;
import one.digitalinnovation.labdesignpatternsspring.domain.responses.ClientResponse;

public interface ICustomerRegistrationService {
    ClientResponse createClient(ClientRequest client);
    ClientResponse getClient(int id);
    List<ClientResponse> getClients();
    ClientResponse updateClient(int id, ClientRequest client);
    Boolean deleteClient(int id);
}
