package com.example.hibernate.service;

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

    public Part addPart(Part part) { return partRepository.save(part);}

    public Part updatePart(@PathVariable("id") UUID id, @RequestBody Part part){
        if (part.getId() == id) {
            Part partPom= partRepository.getById(id);
           partPom.setName(part.getName());
           partPom.setPrice(part.getPrice());
           partPom.setRang(part.getRang());
           partPom.setModels(part.getModels());
           partPom.setShop(part.getShop());
            return partPom;
        }
        return part;
    }

    public Part deleteById(@PathVariable("id") UUID id) {
        partRepository.deleteById(id);
        return new Part();
    }

    public void deleteAllModels(){
        partRepository.deleteAll();
    }
}
