package com.example.hibernate.service;

import com.example.hibernate.model.AddPart;
import com.example.hibernate.model.Model;
import com.example.hibernate.model.Part;
import com.example.hibernate.repository.PartRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PartService {

    private final PartRepository partRepository;

    public PartService(PartRepository partRepository) {
        this.partRepository = partRepository;
    }

    public List<Part> getPart(){ return partRepository.findAll();}

    public Part getPartById(UUID id) throws Exception{
        Optional<Part> optPart = partRepository.findById(id);

        if (optPart.isEmpty()) {
            throw new Exception("error.../n The value is not present!");
        } else
            return optPart.get();
    }

    public Part addPart(final AddPart addPart) { return partRepository.save(new Part(addPart.name(),addPart.price(), addPart.rang(),addPart.models(), addPart.shop()));}

    public Part updatePart(UUID id, Part part){
        Part partPom= partRepository.findById(id).get();
        if (partPom.getId() == id) {
           partPom.setName(part.getName());
           partPom.setPrice(part.getPrice());
           partPom.setRang(part.getRang());
           partPom.setModels(part.getModels());
           partPom.setShop(part.getShop());
            return partPom;
        }
        return part;
    }

    public Part deleteById(UUID id) throws Exception {
        Optional<Part> optionalPart = partRepository.findById(id);

        if (optionalPart.isEmpty()) {
            throw new Exception("error.../n The value is not present!/nDid not find the shop!");
        } else{ partRepository.deleteById(id);
            return optionalPart.get();}
    }

    public void deleteAllModels(){
        partRepository.deleteAll();
    }
}
