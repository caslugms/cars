package br.edu.ifpr.cars.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.edu.ifpr.cars.domain.*;

@Service
public class TravelService {

    @Autowired
    private TravelRequestRepository repository;

    // Passageiro cria viagem
    public TravelRequest create(TravelRequest request) {
        request.setStatus(TravelRequestStatus.CREATED);
        return repository.save(request);
    }

    // Motorista aceita viagem
    public TravelRequest acceptTravel(Long id, Long driverId) {

        TravelRequest travel = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if (travel.getStatus() != TravelRequestStatus.CREATED) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Viagem já está em status " + travel.getStatus());
        }

        travel.setStatus(TravelRequestStatus.ACCEPTED);
        travel.setDriverId(driverId);

        return repository.save(travel);
    }
}
