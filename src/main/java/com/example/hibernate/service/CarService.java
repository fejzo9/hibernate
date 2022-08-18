package com.example.hibernate.service;

import com.example.hibernate.exception.EntityNotFoundException;
import com.example.hibernate.model.AddCar;
import com.example.hibernate.model.Car;
import com.example.hibernate.model.UpdateCar;
import com.example.hibernate.model.User;
import com.example.hibernate.repository.CarRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class CarService {
    private final CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public List<Car> getCars() {
        return carRepository.findAll();
    }

    public Car getCarById(UUID id) throws Exception {
        Optional<Car> optCar = carRepository.findById(id);

        if (optCar.isEmpty()) {
            throw new Exception("error.../n The value is not present!");
        } else
            return optCar.get();
    }

    public Car addCar(final AddCar addCar) {
        return carRepository.save(new Car(addCar.yearOfManufacture(), addCar.registerNumber(), addCar.user(), addCar.manufacturer()));
    }

    public Car updateCar(UUID id, UpdateCar updateCar) throws EntityNotFoundException {

        Optional<Car> optCar = carRepository.findById(id);
        if (optCar.isEmpty()) {
            throw new EntityNotFoundException("Car", id);
        }
        Car carPom = optCar.get();
        carPom.setYearOfManufacture(updateCar.yearOfManufacture());
        carPom.setRegisterNumber(updateCar.registerNumber());
        carPom.setUser(updateCar.user());
        carPom.setManufacturer(updateCar.manufacturer());
        return carRepository.save(carPom);

    }

    public Car deleteById(UUID id) throws EntityNotFoundException {
        Optional<Car> optCar = carRepository.findById(id);

        if (optCar.isEmpty()) {
            throw new EntityNotFoundException("Car", id);
        } else {
            carRepository.deleteById(id);
            return optCar.get();
        }
    }

    public void deleteAllCars() {
        carRepository.deleteAll();
    }
}
