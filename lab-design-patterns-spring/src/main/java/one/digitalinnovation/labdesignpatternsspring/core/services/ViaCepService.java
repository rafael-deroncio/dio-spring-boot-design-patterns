package one.digitalinnovation.labdesignpatternsspring.core.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import one.digitalinnovation.labdesignpatternsspring.domain.responses.AddressResponse;

@FeignClient(name = "viacep", url = "https://viacep.com.br/ws")
public interface ViaCepService {

    @GetMapping("/{cep}/json/")
    AddressResponse getCep(@PathVariable("cep") String cep);
}
