package one.digitalinnovation.labdesignpatternsspring.api;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import one.digitalinnovation.labdesignpatternsspring.core.services.CustomerRegistrationService;
import one.digitalinnovation.labdesignpatternsspring.domain.requests.ClientRequest;
import one.digitalinnovation.labdesignpatternsspring.domain.responses.ClientResponse;
import one.digitalinnovation.labdesignpatternsspring.domain.responses.ExceptionResponse;

@RestController
@RequestMapping("/client")
@Tag(name = "Client Address Controller", description = "the Client Address API")
public class ClientController {

    @Autowired
    private CustomerRegistrationService customerService;

    /**
     * Create a new client.
     *
     * @param clientRequest The request containing client data.
     * @return The created client.
     */
    @PostMapping
    @Operation(summary = "Create a new client")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = ClientResponse.class)))),
            @ApiResponse(responseCode = "400", description = "Client not found", content = @Content(array = @ArraySchema(schema = @Schema(implementation = ExceptionResponse.class)))),
            @ApiResponse(responseCode = "422", description = "Unprocessable Entity", content = @Content(array = @ArraySchema(schema = @Schema(implementation = ExceptionResponse.class)))),
            @ApiResponse(responseCode = "500", description = "Internal error", content = @Content(array = @ArraySchema(schema = @Schema(implementation = ExceptionResponse.class)))) })
    public ResponseEntity<ClientResponse> createClient(@RequestBody ClientRequest request) {
        return ResponseEntity.ok(customerService.createClient(request));
    }

    /**
     * Get a list of all clients.
     *
     * @return A list of clients.
     */
    @GetMapping
    @Operation(summary = "Get a list of all clients")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = ClientResponse.class)))),
            @ApiResponse(responseCode = "400", description = "Client not found", content = @Content(array = @ArraySchema(schema = @Schema(implementation = ExceptionResponse.class)))),
            @ApiResponse(responseCode = "422", description = "Unprocessable Entity", content = @Content(array = @ArraySchema(schema = @Schema(implementation = ExceptionResponse.class)))),
            @ApiResponse(responseCode = "500", description = "Internal error", content = @Content(array = @ArraySchema(schema = @Schema(implementation = ExceptionResponse.class)))) })
    public ResponseEntity<List<ClientResponse>> getClients() {
        return ResponseEntity.ok(customerService.getClients());
    }

    /**
     * Get a client by ID.
     *
     * @param id The ID of the client to retrieve.
     * @return The client with the specified ID.
     */
    @GetMapping("/{id}")
    @Operation(summary = "Get a client by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = ClientResponse.class)))),
            @ApiResponse(responseCode = "400", description = "Client not found", content = @Content(array = @ArraySchema(schema = @Schema(implementation = ExceptionResponse.class)))),
            @ApiResponse(responseCode = "422", description = "Unprocessable Entity", content = @Content(array = @ArraySchema(schema = @Schema(implementation = ExceptionResponse.class)))),
            @ApiResponse(responseCode = "500", description = "Internal error", content = @Content(array = @ArraySchema(schema = @Schema(implementation = ExceptionResponse.class)))) })
    public ResponseEntity<ClientResponse> getClient(@PathVariable int id) {
        return ResponseEntity.ok(customerService.getClient(id));
    }

    /**
     * Update a client by ID.
     *
     * @param id            The ID of the client to update.
     * @param clientRequest The request containing updated client data.
     * @return The updated client.
     */
    @PutMapping("/{id}")
    @Operation(summary = "Update a client by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = ClientResponse.class)))),
            @ApiResponse(responseCode = "400", description = "Client not found", content = @Content(array = @ArraySchema(schema = @Schema(implementation = ExceptionResponse.class)))),
            @ApiResponse(responseCode = "422", description = "Unprocessable Entity", content = @Content(array = @ArraySchema(schema = @Schema(implementation = ExceptionResponse.class)))),
            @ApiResponse(responseCode = "500", description = "Internal error", content = @Content(array = @ArraySchema(schema = @Schema(implementation = ExceptionResponse.class)))) })
    public ResponseEntity<ClientResponse> updateClient(@PathVariable int id, @RequestBody ClientRequest request) {
        return ResponseEntity.ok(customerService.updateClient(id, request));
    }

    /**
     * Delete a client by ID.
     *
     * @param id The ID of the client to delete.
     * @return True if the client was successfully deleted, false if not found.
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a client by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = ClientResponse.class)))),
            @ApiResponse(responseCode = "400", description = "Client not found", content = @Content(array = @ArraySchema(schema = @Schema(implementation = ExceptionResponse.class)))),
            @ApiResponse(responseCode = "422", description = "Unprocessable Entity", content = @Content(array = @ArraySchema(schema = @Schema(implementation = ExceptionResponse.class)))),
            @ApiResponse(responseCode = "500", description = "Internal error", content = @Content(array = @ArraySchema(schema = @Schema(implementation = ExceptionResponse.class)))) })
    public ResponseEntity<Boolean> deleteClient(@PathVariable int id) {
        return ResponseEntity.ok(customerService.deleteClient(id));
    }
}
