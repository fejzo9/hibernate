package com.example.hibernate.controller;

import com.example.hibernate.model.AddManufacturer;
import com.example.hibernate.model.Car;
import com.example.hibernate.model.Manufacturer;
import com.example.hibernate.service.ManufacturerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/manufacturer")
public class ManufacturerController {

    private final ManufacturerService manufacturerService;

    public ManufacturerController(ManufacturerService manufacturerService) {
        this.manufacturerService = manufacturerService;
    }

    @GetMapping("")
    public ResponseEntity<List<Manufacturer>> getAllManufacturers(){
        try {
            List<Manufacturer> manufacturers = manufacturerService.getManufacturers();

            if (manufacturers.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(manufacturers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<Manufacturer> getManufacturerById(@PathVariable("id") UUID id){
        try {
            return new ResponseEntity<>(manufacturerService.getManufacturerById(id), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("")
    public ResponseEntity<Manufacturer> createManufacturer(@RequestBody AddManufacturer manufacturer) {
        try{
            return new ResponseEntity<>(manufacturerService.addManufacturer(manufacturer), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("{id}")
    public ResponseEntity<Manufacturer> updateManufacturer(@PathVariable("id") UUID id, @RequestBody Manufacturer manufacturer){
        try {
            return new ResponseEntity<>(manufacturerService.updateManufacturer(id,manufacturer), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Manufacturer> deleteManufacturer(@PathVariable("id") UUID id) {
        try {
            return new ResponseEntity<>(manufacturerService.deleteById(id), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("")
    public ResponseEntity<HttpStatus> deleteAllManufacturers() {
        try{
            manufacturerService.deleteAllManufacturers();
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
