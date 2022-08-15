package com.example.hibernate.controller;

import com.example.hibernate.model.AddCar;
import com.example.hibernate.model.AddUser;
import com.example.hibernate.model.Car;
import com.example.hibernate.model.User;
import com.example.hibernate.service.CarService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class CarController {

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/cars")
    public ResponseEntity<List<Car>> getAllCars() {
        try {
            List<Car> cars = carService.getCars();

            if (cars.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(cars, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/cars/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable("id") UUID id) {
        try {
            Car car = carService.getCarById(id);
            return new ResponseEntity<>(car, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/cars")
    public ResponseEntity<Car> createCar(@RequestBody AddCar car) {
        try {
            return new ResponseEntity<>(carService.addCar(car), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/cars/{id}")
    public ResponseEntity<Car> updateCar(@PathVariable("id") UUID id, @RequestBody Car car) {
        try {
            return new ResponseEntity<>(carService.updateCar(id,car), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/cars/{id}")
    public ResponseEntity<Car> deleteUser(@PathVariable("id") UUID id) {
        try {
            return new ResponseEntity<>(carService.deleteById(id), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/cars")
    public ResponseEntity<HttpStatus> deleteAllUsers() {
         try{
             carService.deleteAllCars();
             return new ResponseEntity<>(HttpStatus.ACCEPTED);
         }
         catch (Exception e) {
             return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
         }
    }

}
