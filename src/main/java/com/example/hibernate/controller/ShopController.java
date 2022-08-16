package com.example.hibernate.controller;

import com.example.hibernate.model.Model;
import com.example.hibernate.model.Shop;
import com.example.hibernate.service.ShopService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class ShopController {

    private final ShopService shopService;

    public ShopController(ShopService shopService) {
        this.shopService = shopService;
    }

    @GetMapping("/shops")
    public ResponseEntity<List<Shop>> getAllShops(){
        try {
            List<Shop> shops = shopService.getShops();

            if (shops.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(shops, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/shops/{id}")
    public ResponseEntity<Shop> getShopById(@PathVariable("id") UUID id){
        try {
            Shop shop = shopService.getShopById(id);
            return new ResponseEntity<>(shop,HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("shops")
    public ResponseEntity<Shop> createShop(@RequestBody Shop shop) {
        try{
            return new ResponseEntity<>(shopService.addShop(shop), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/shops/{id}")
    public ResponseEntity<Shop> updateShop(@PathVariable("id") UUID id, @RequestBody Shop shop){
        try {
            return new ResponseEntity<>(shopService.updateShop(id,shop), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Shop> deleteShop(@PathVariable("id") UUID id) {
        try {
            return new ResponseEntity<>(shopService.deleteById(id), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/shops")
    public ResponseEntity<HttpStatus> deleteAllShops() {
        try{
            shopService.deleteAllModels();
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
