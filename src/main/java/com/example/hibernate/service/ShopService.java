package com.example.hibernate.service;

import com.example.hibernate.exception.EntityNotFoundException;
import com.example.hibernate.model.*;
import com.example.hibernate.repository.ShopRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ShopService {

    private final ShopRepository shopRepository;

    public ShopService(ShopRepository shopRepository) {
        this.shopRepository = shopRepository;
    }

    public List<Shop> getShops() {
        return shopRepository.findAll();
    }

    public Shop getShopById(UUID id) throws Exception {
        Optional<Shop> optShop = shopRepository.findById(id);

        if (optShop.isEmpty()) {
            throw new Exception("error.../n The value is not present!");
        } else
            return optShop.get();
    }

    public Shop addShop(final AddShop addShop) {
        return shopRepository.save(new Shop(addShop.name(), addShop.parts()));
    }

    public Shop updateShop(UUID id, UpdateShop updateShop) {
        //        return shopRepository
        //                .findById(id)
        //                .map(existingShop -> {
        //                    existingShop.setName(shop.getName());
        //                    existingShop.setParts(shop.getParts());
        //                    return shopRepository.save(existingShop);
        //                })
        //                .orElseThrow(() -> new EntityNotFoundException("Shop", id));
        final Optional<Shop> optionalShop = shopRepository.findById(id);
        if (optionalShop.isEmpty()) {
            throw new EntityNotFoundException("Shop", id);
        }
        Shop shopPom = optionalShop.get();
        shopPom.setName(updateShop.name());
        shopPom.setParts(updateShop.parts());
        return shopRepository.save(shopPom);

    }

    public Shop deleteById(UUID id) throws EntityNotFoundException {
        Optional<Shop> optionalShop = shopRepository.findById(id);

        if (optionalShop.isEmpty()) {
            throw new EntityNotFoundException("Shop", id);
        } else {
            shopRepository.deleteById(id);
            return optionalShop.get();
        }

    }

    public void deleteAllModels() {
        shopRepository.deleteAll();
    }
}
