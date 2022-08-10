package com.example.hibernate.service;

import com.example.hibernate.model.Part;
import com.example.hibernate.model.Shop;
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

    public List<Shop> getShops(){ return shopRepository.findAll();}

    public Shop getShopById(UUID id) throws Exception{
        Optional<Shop> optShop = shopRepository.findById(id);

        if (optShop.isEmpty()) {
            throw new Exception("error.../n The value is not present!");
        } else
            return optShop.get();
    }

    public Shop addShop(Shop shop) { return shopRepository.save(shop);}

    public Shop updateShop(@PathVariable("id") UUID id, @RequestBody Shop shop){
        if (shop.getId() == id) {
            Shop shopPom= shopRepository.getById(id);
            shopPom.setName(shop.getName());
            shopPom.setParts(shop.getParts());
            return shopPom;
        }
        return shop;
    }

    public Shop deleteById(@PathVariable("id") UUID id) {
        shopRepository.deleteById(id);
        return new Shop();
    }

    public void deleteAllModels(){
        shopRepository.deleteAll();
    }
}
