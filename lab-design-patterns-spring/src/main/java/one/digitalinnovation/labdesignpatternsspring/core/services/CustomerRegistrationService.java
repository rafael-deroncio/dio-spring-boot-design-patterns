package one.digitalinnovation.labdesignpatternsspring.core.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import one.digitalinnovation.labdesignpatternsspring.core.enums.ResponsesErrorType;
import one.digitalinnovation.labdesignpatternsspring.core.exceptions.AddressException;
import one.digitalinnovation.labdesignpatternsspring.core.exceptions.ApiBusinessException;
import one.digitalinnovation.labdesignpatternsspring.core.exceptions.ClientException;
import one.digitalinnovation.labdesignpatternsspring.core.models.AddressModel;
import one.digitalinnovation.labdesignpatternsspring.core.models.ClientModel;
import one.digitalinnovation.labdesignpatternsspring.core.repositories.AddressRepository;
import one.digitalinnovation.labdesignpatternsspring.core.repositories.ClientRepository;
import one.digitalinnovation.labdesignpatternsspring.core.services.interfaces.ICustomerRegistrationService;
import one.digitalinnovation.labdesignpatternsspring.domain.requests.ClientRequest;
import one.digitalinnovation.labdesignpatternsspring.domain.responses.AddressResponse;
import one.digitalinnovation.labdesignpatternsspring.domain.responses.ClientResponse;

/**
 * Service class responsible for customer registration and management.
 * 
 * @author Rafael Deroncio
 */
@Service
public class CustomerRegistrationService implements ICustomerRegistrationService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private ViaCepService cepService;

    @Autowired
    private ModelMapper mapper;

    /**
     * Creates a new client with the provided data.
     *
     * @param client The data of the client to be created.
     * @return The created client as a ClientResponse object.
     * @author Rafael Deroncio
     */
    @Override
    public ClientResponse createClient(ClientRequest client) {
        try {
            String cep = fortamtCep(client.getAddress().getCep());
            AddressModel addressModel = getAddressModelValidated(cep);
            ClientModel clientModel = mapper.map(client, ClientModel.class);
            clientModel.setAddress(addressModel);

            return mapper.map(clientRepository.save(clientModel), ClientResponse.class);
        } catch (Exception e) {
            throw new ApiBusinessException(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY, ResponsesErrorType.CRITICAL);
        }
    }

    /**
     * Gets a client based on the provided ID.
     *
     * @param id The ID of the client to be retrieved.
     * @return The retrieved client as a ClientResponse object, or null if not
     *         found.
     * @author Rafael Deroncio
     */
    @Override
    public ClientResponse getClient(int id) {
            Optional<ClientModel> client = clientRepository.findById(id);
            
            if (client.isPresent())
                return mapper.map(client.get(), ClientResponse.class);

            throw new ClientException("cliente with id '" + id + "' not found", HttpStatus.BAD_REQUEST, ResponsesErrorType.ERROR);
    }

    /**
     * Retrieves a list of all clients.
     *
     * @return A list of clients as ClientResponse objects.
     * @author Rafael Deroncio
     */
    @Override
    public List<ClientResponse> getClients() {
        try {
            return StreamSupport.stream(clientRepository.findAll().spliterator(), false)
                    .map(client -> mapper.map(client, ClientResponse.class))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new ApiBusinessException(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY, ResponsesErrorType.CRITICAL);
        }
    }

    /**
     * Updates an existing client with the provided data.
     *
     * @param id     The ID of the client to be updated.
     * @param client The new client data.
     * @return The updated client as a ClientResponse object, or null if the client
     *         is not found.
     * @author Rafael Deroncio
     */
    @Override
    public ClientResponse updateClient(int id, ClientRequest client) {
        Optional<ClientModel> clientModel = clientRepository.findById(id);

        if (!clientModel.isPresent())
            throw new ClientException("cliente with id '" + id + "' not found", HttpStatus.BAD_REQUEST, ResponsesErrorType.ERROR);

        try {
            ClientModel clientUpdated = clientModel.get();
            clientUpdated.setName(client.getName());

            if (clientUpdated.getAddress().getCep() != client.getAddress().getCep()) {

                String cep = fortamtCep(client.getAddress().getCep());
                AddressModel addressModel = getAddressModelValidated(cep);

                clientUpdated.setAddress(addressModel);
            }

            return mapper.map(clientRepository.save(clientUpdated), ClientResponse.class);
        } catch (Exception e) {
            throw new ApiBusinessException(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY, ResponsesErrorType.CRITICAL);
        }
    }

    /**
     * Deletes a client based on the provided ID.
     *
     * @param id The ID of the client to be deleted.
     * @return true if the client was successfully deleted, false if the client is
     *         not found.
     * @author Rafael Deroncio
     */
    @Override
    public Boolean deleteClient(int id) {
        if (clientRepository.findById(id).isPresent()) 
            throw new ClientException("cliente with id '" + id + "' not found", HttpStatus.BAD_REQUEST, ResponsesErrorType.ERROR);

        try {
            clientRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            throw new ApiBusinessException(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY, ResponsesErrorType.CRITICAL);
        }
    }

    private AddressModel validateAddress(String cep) {
        Optional<AddressModel> address = addressRepository.findById(cep);
        return address.isPresent() ? address.get() : null;
    }

    private AddressModel integrateAddress(String cep) {
        AddressResponse response = cepService.getCep(cep);
        AddressModel address = mapper.map(response, AddressModel.class);
        addressRepository.save(address);
        return address;
    }

    private static String fortamtCep(String cep) {
        try {
            cep = cep.trim().replace("-", "");
            cep = String.format("%08d", Integer.parseInt(cep));
            cep = cep.substring(0, 5) + "-" + cep.substring(5, 8);

            if (Pattern.matches("[0-9]{5}-[0-9]{3}", cep)) {
                return cep;
            } else {
                throw new AddressException("Invalid CEP format: " + cep, HttpStatus.BAD_REQUEST, ResponsesErrorType.ERROR);
            }
                
        } catch(NumberFormatException e) {
            throw new NumberFormatException("Invalid CEP: " + cep);
        }
    }

    private AddressModel getAddressModelValidated(String cep) {
        return validateAddress(cep) == null ? integrateAddress(cep) : addressRepository.findById(cep).get();
    }
}
