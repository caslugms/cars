package br.edu.ifpr.cars.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.edu.ifpr.cars.domain.*;

@Service
public class TravelService {

    @Autowired
    private TravelRequestRepository repository;

    @Autowired
    private PassengerRepository passengerRepository;

    private TravelRequest findTravel(Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, 
                                                           "Viagem com ID " + id + " não encontrada."));
    }

    public TravelRequest create(TravelRequest request, Long passengerId) {
        
        Passenger passenger = passengerRepository.findById(passengerId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, 
                                                           "Passageiro com ID " + passengerId + " não encontrado."));

        request.setPassenger(passenger);
        request.setStatus(TravelRequestStatus.CREATED);
        
        return repository.save(request);
    }

    public TravelRequest acceptTravel(Long id, Long driverId) {
        TravelRequest travel = findTravel(id);

        if (travel.getStatus() != TravelRequestStatus.CREATED) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Viagem só pode ser aceita se estiver no status CREATED. Status atual: " + travel.getStatus());
        }

        travel.setStatus(TravelRequestStatus.ACCEPTED);
        travel.setDriverId(driverId);

        return repository.save(travel);
    }
    
    public TravelRequest refuseTravel(Long id, Long driverId) {
        TravelRequest travel = findTravel(id);

        if (travel.getStatus() != TravelRequestStatus.CREATED) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Viagem só pode ser recusada se estiver no status CREATED. Status atual: " + travel.getStatus());
        }

        travel.setStatus(TravelRequestStatus.REFUSED);
        travel.setDriverId(driverId); 

        return repository.save(travel);
    }
    
    public TravelRequest finishTravel(Long id) {
        TravelRequest travel = findTravel(id);

        if (travel.getStatus() != TravelRequestStatus.ACCEPTED) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Viagem só pode ser finalizada se estiver no status ACCEPTED. Status atual: " + travel.getStatus());
        }

        travel.setStatus(TravelRequestStatus.FINISHED);

        return repository.save(travel);
    }

    public List<TravelRequest> findAll() {
        return repository.findAll();
    }

    public TravelRequest findById(Long id) {
        return findTravel(id);
    }
}