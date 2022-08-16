package com.example.hibernate.service;

import com.example.hibernate.model.AddModel;
import com.example.hibernate.model.Manufacturer;
import com.example.hibernate.model.Model;
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

    public ModelService(ModelRepository modelRepository) {
        this.modelRepository = modelRepository;
    }

    public List<Model> getModels(){ return modelRepository.findAll();}

    public Model getModelById(UUID id) throws Exception{
        Optional<Model> optModel = modelRepository.findById(id);

        if (optModel.isEmpty()) {
            throw new Exception("error.../n The value is not present!");
        } else
            return optModel.get();
    }

    public Model addModel(final AddModel addModel)
    {
        return modelRepository.save(new Model(addModel.name(), addModel.parts()));
    }

    public Model updateModel(UUID id, Model model){
        Model modelPom= modelRepository.findById(id).get();
        if (modelPom.getId() == id) {
            modelPom.setName(model.getName());
            modelPom.setParts(model.getParts());
            return modelPom;
        }
        return model;
    }

    public Model deleteById(UUID id) throws Exception {
        Optional<Model> optionalModel = modelRepository.findById(id);

        if (optionalModel.isEmpty()) {
            throw new Exception("error.../n The value is not present!/nDid not find the shop!");
        } else{ modelRepository.deleteById(id);
            return optionalModel.get();}

    }

    public void deleteAllModels(){
        modelRepository.deleteAll();
    }
}
