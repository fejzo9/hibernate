package com.example.hibernate.service;

import com.example.hibernate.exception.EntityNotFoundException;
import com.example.hibernate.model.*;
import com.example.hibernate.repository.PartRepository;
import com.example.hibernate.repository.ShopRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PartService {

    private final PartRepository partRepository;
    private final ShopRepository shopRepository;

    public PartService(PartRepository partRepository, ShopRepository shopRepository) {
        this.partRepository = partRepository;
        this.shopRepository = shopRepository;
    }

    public List<Part> getPart() {
        return partRepository.findAll();
    }

    public Part getPartById(UUID id) throws Exception {
        Optional<Part> optPart = partRepository.findById(id);

        if (optPart.isEmpty()) {
            throw new Exception("error.../n The value is not present!");
        } else
            return optPart.get();
    }

    public Part addPart(final AddPart addPart) {
        return partRepository.save(new Part(addPart.name(), addPart.price(), addPart.rang(), addPart.models(), addPart.shop()));
    }

    public Part updatePart(UUID id, UpdatePart part) throws EntityNotFoundException {
        Optional<Part> optPart = partRepository.findById(id);
        if (optPart.isEmpty()) {
            throw new EntityNotFoundException("Part", id);
        }
        Part partPom = optPart.get();
        partPom.setName(part.name());
        partPom.setPrice(part.price());
        partPom.setRang(part.rang());
        partPom.setModels(part.models());
        return partRepository.save(partPom);

    }

    public Part deleteById(UUID id) throws EntityNotFoundException {
        Optional<Part> optionalPart = partRepository.findById(id);

        if (optionalPart.isEmpty()) {
            throw new EntityNotFoundException("Part", id);
        } else {
            partRepository.deleteById(id);
            return optionalPart.get();
        }
    }

    public void deleteAllModels() {
        partRepository.deleteAll();
    }

    public Part isSelling(final UUID partId, final UUID shopId) throws EntityNotFoundException {
        Optional<Part> optPart = partRepository.findById(partId);
        Optional<Shop> optShop = shopRepository.findById(shopId);

        if (optPart.isEmpty() || optShop.isEmpty()) {
            throw new EntityNotFoundException("Car", partId, shopId);
        }
        Part part = optPart.get();
        Shop shop = optShop.get();
        part.setShop(shop);
        return partRepository.save(part);
    }
}
