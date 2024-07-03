package br.com.microservices.bookservice.proxy;

import br.com.microservices.cambioservice.dto.CambioDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "cambio-service", url = "localhost:8000", path = "/cambio-service")
public interface CambioProxy {

    @GetMapping("/cambio/{amount}/{from}/{to}")
    CambioDTO getCambio(@PathVariable Double amount, @PathVariable String from, @PathVariable String to);

}
