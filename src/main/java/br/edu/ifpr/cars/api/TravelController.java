package br.edu.ifpr.cars.api;

import java.util.List;

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

    @GetMapping
    public List<TravelRequest> listarTodos() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public TravelRequest buscarPorId(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    public TravelRequest criar(@RequestBody @Valid TravelRequestDTO dto) {
        TravelRequest t = new TravelRequest();
        t.setOrigem(dto.getOrigem());
        t.setDestino(dto.getDestino());
        
        return service.create(t, dto.getPassengerId());
    }

    @PostMapping("/{id}/accept")
    public TravelRequest aceitar(@PathVariable Long id, @RequestParam Long driverId) {
        return service.acceptTravel(id, driverId);
    }

    @PostMapping("/{id}/refuse")
    public TravelRequest recusar(@PathVariable Long id, @RequestParam Long driverId) {
        return service.refuseTravel(id, driverId);
    }
    
    @PostMapping("/{id}/finish")
    public TravelRequest finalizar(@PathVariable Long id) {
        return service.finishTravel(id);
    }
}