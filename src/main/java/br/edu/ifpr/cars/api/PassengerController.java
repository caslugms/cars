package br.edu.ifpr.cars.api;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.edu.ifpr.cars.domain.Passenger;
import br.edu.ifpr.cars.domain.PassengerRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/passengers", produces = MediaType.APPLICATION_JSON_VALUE)
public class PassengerController {

    @Autowired
    private PassengerRepository passengerRepository;

    private Passenger findPassenger(Long id) {
        return passengerRepository.findById(id).orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Passageiro não encontrado."));
    }

    @PostMapping
    public Passenger createPassenger(@RequestBody @Valid Passenger passenger) {
        return passengerRepository.save(passenger);
    }

    @GetMapping
    public List<Passenger> listPassengers() {
        return passengerRepository.findAll();
    }
    
    @GetMapping("/{id}")
    public Passenger getPassenger(@PathVariable("id") Long id) {
        return findPassenger(id);
    }

    @PutMapping("/{id}")
    public Passenger fullUpdatePassenger(@PathVariable("id") Long id, @RequestBody @Valid Passenger passenger) {
        Passenger foundPassenger = findPassenger(id);
        foundPassenger.setName(passenger.getName());
        foundPassenger.setPhone(passenger.getPhone());
        return passengerRepository.save(foundPassenger);
    }

    @PatchMapping("/{id}")
    public Passenger incrementalUpdatePassenger(@PathVariable("id") Long id, @RequestBody Passenger passenger) {
        Passenger foundPassenger = findPassenger(id);
        
        foundPassenger.setName(Optional.ofNullable(passenger.getName()).orElse(foundPassenger.getName()));
        foundPassenger.setPhone(Optional.ofNullable(passenger.getPhone()).orElse(foundPassenger.getPhone()));
        
        return passengerRepository.save(foundPassenger);
    }

    @DeleteMapping("/{id}")
    public void deletePassenger(@PathVariable("id") Long id) {
        findPassenger(id); 
        passengerRepository.deleteById(id);
    }
}