package com.example.hibernate.controller;

import com.example.hibernate.model.AddShop;
import com.example.hibernate.model.Model;
import com.example.hibernate.model.Shop;
import com.example.hibernate.model.UpdateShop;
import com.example.hibernate.service.ShopService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/shop")
public class ShopController {

    private final ShopService shopService;

    public ShopController(ShopService shopService) {
        this.shopService = shopService;
    }

    @GetMapping("")
    public ResponseEntity<List<Shop>> getAllShops() {
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

    @GetMapping("{id}")
    public ResponseEntity<Shop> getShopById(@PathVariable("id") UUID id) {
        try {
            return new ResponseEntity<>(shopService.getShopById(id), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("")
    public ResponseEntity<Shop> createShop(@RequestBody AddShop shop) {
        try {
            return new ResponseEntity<>(shopService.addShop(shop), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<Shop> updateShop(@PathVariable("id") UUID id, @RequestBody UpdateShop updateShop) {
        try {
            return new ResponseEntity<>(shopService.updateShop(id, updateShop), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Shop> deleteShop(@PathVariable("id") UUID id) {
        try {
            return new ResponseEntity<>(shopService.deleteById(id), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("")
    public ResponseEntity<HttpStatus> deleteAllShops() {
        try {
            shopService.deleteAllModels();
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
