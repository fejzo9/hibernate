package com.example.hibernate.service;

import com.example.hibernate.exception.EntityNotFoundException;
import com.example.hibernate.model.*;
import com.example.hibernate.repository.ManufacturerRepository;
import com.example.hibernate.repository.ModelRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ModelService {

    private final ModelRepository modelRepository;
    private final ManufacturerRepository manufacturerRepository;

    public ModelService(ModelRepository modelRepository, ManufacturerRepository manufacturerRepository) {
        this.modelRepository = modelRepository;
        this.manufacturerRepository = manufacturerRepository;
    }

    public List<Model> getModels() {
        return modelRepository.findAll();
    }

    public Model getModelById(UUID id) {
        Optional<Model> optModel = modelRepository.findById(id);

        if (optModel.isEmpty()) {
            throw new EntityNotFoundException("Model", id);
        } else
            return optModel.get();
    }

    public Model addModel(final AddModel addModel) {
        return modelRepository.save(new Model(addModel.name(), addModel.parts(), addModel.manufacturer()));
    }

    public Model updateModel(UUID id, UpdateModel model) throws EntityNotFoundException {

        Optional<Model> optModel = modelRepository.findById(id);
        if (optModel.isEmpty()) {
            throw new EntityNotFoundException("Car", id);
        }
        Model modelPom = optModel.get();
        modelPom.setName(model.name());
        modelPom.setParts(model.parts());
        return modelRepository.save(modelPom);


    }

    public Model deleteById(UUID id) throws EntityNotFoundException {
        Optional<Model> optionalModel = modelRepository.findById(id);

        if (optionalModel.isEmpty()) {
            throw new EntityNotFoundException("Model", id);
        } else {
            modelRepository.deleteById(id);
            return optionalModel.get();
        }

    }

    public void deleteAllModels() {
        modelRepository.deleteAll();
    }

    public Model hasManufacturer(final UUID modelId, final UUID manufacturerId) throws EntityNotFoundException {
        Optional<Model> optModel = modelRepository.findById(modelId);
        Optional<Manufacturer> optManufacturer = manufacturerRepository.findById(manufacturerId);

        if (optModel.isEmpty() || optManufacturer.isEmpty()) {
            throw new EntityNotFoundException("Model", modelId, manufacturerId);
        }
        Model model = optModel.get();
        Manufacturer manufacturer = optManufacturer.get();
        model.setManufacturer(manufacturer);
        return modelRepository.save(model);
    }
}
