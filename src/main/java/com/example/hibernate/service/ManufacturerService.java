package com.example.hibernate.service;

import com.example.hibernate.exception.EntityNotFoundException;
import com.example.hibernate.model.*;
import com.example.hibernate.repository.ManufacturerRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ManufacturerService {
    private final ManufacturerRepository manufacturerRepository;

    public ManufacturerService(ManufacturerRepository manufacturerRepository) {
        this.manufacturerRepository = manufacturerRepository;
    }

    public List<Manufacturer> getManufacturers() {
        return manufacturerRepository.findAll();
    }

    public Manufacturer getManufacturerById(UUID id) throws EntityNotFoundException {
        Optional<Manufacturer> optManufacturer = manufacturerRepository.findById(id);

        if (optManufacturer.isEmpty()) {
            throw new EntityNotFoundException("Manufacturer", id);
        } else return optManufacturer.get();
    }

    public Manufacturer addManufacturer(final AddManufacturer addManufacturer) {
        return manufacturerRepository.save(new Manufacturer(addManufacturer.name(), addManufacturer.cars(), addManufacturer.models()));
    }

    public Manufacturer updateManufacturer(UUID id, UpdateManufacturer updateManufacturer) throws EntityNotFoundException{

        Optional<Manufacturer> optManufacturer = manufacturerRepository.findById(id);
        if (optManufacturer.isEmpty()) {
            throw new EntityNotFoundException("Manufacturer", id);
        }
        Manufacturer manufacturerPom = optManufacturer.get();
        manufacturerPom.setName(updateManufacturer.name());
        manufacturerPom.setCars(updateManufacturer.cars());
        manufacturerPom.setModels(updateManufacturer.models());
        return manufacturerRepository.save(manufacturerPom);

    }

    public Manufacturer deleteById(UUID id) throws EntityNotFoundException {
        Optional<Manufacturer> optionalManufacturer = manufacturerRepository.findById(id);

        if (optionalManufacturer.isEmpty()) {
            throw new EntityNotFoundException("Manufacturer", id);
        } else {
            manufacturerRepository.deleteById(id);
            return optionalManufacturer.get();
        }

    }

    public void deleteAllManufacturers() {
        manufacturerRepository.deleteAll();
    }


}
