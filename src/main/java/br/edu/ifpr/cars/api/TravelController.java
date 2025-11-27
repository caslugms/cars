package br.edu.ifpr.cars.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import br.edu.ifpr.cars.domain.TravelRequest;
import br.edu.ifpr.cars.service.TravelService;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/travels", produces = MediaType.APPLICATION_JSON_VALUE)
public class TravelController {

    @Autowired
    private TravelService service;

    @PostMapping
    public TravelRequest criar(@RequestBody @Valid TravelRequestDTO dto) {
        TravelRequest t = new TravelRequest();
        t.setOrigem(dto.getOrigem());
        t.setDestino(dto.getDestino());
        t.setPassengerId(dto.getPassengerId());
        return service.create(t);
    }

    @PostMapping("/{id}/accept")
    public TravelRequest aceitar(@PathVariable Long id, @RequestParam Long driverId) {
        return service.acceptTravel(id, driverId);
    }
}
