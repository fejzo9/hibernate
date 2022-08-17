package com.example.hibernate.service;

import com.example.hibernate.exception.EntityNotFoundException;
import com.example.hibernate.model.AddManufacturer;
import com.example.hibernate.model.Car;
import com.example.hibernate.model.Manufacturer;
import com.example.hibernate.model.Shop;
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

    public Manufacturer updateManufacturer(UUID id, Manufacturer manufacturer) {

        Manufacturer manufacturerPom = manufacturerRepository.findById(id).get();
        if (manufacturerPom.getId() == id) {
            manufacturerPom.setName(manufacturer.getName());
            manufacturerPom.setCars(manufacturer.getCars());
            manufacturerPom.setModels(manufacturer.getModels());
            return manufacturerPom;
        }
        return manufacturer;
    }

    public Manufacturer deleteById(UUID id) throws Exception {
        Optional<Manufacturer> optionalManufacturer = manufacturerRepository.findById(id);

        if (optionalManufacturer.isEmpty()) {
            throw new Exception("error.../n The value is not present!/nDid not find the manufecturer!");
        } else {
            manufacturerRepository.deleteById(id);
            return optionalManufacturer.get();
        }

    }

    public void deleteAllManufacturers() {
        manufacturerRepository.deleteAll();
    }


}
