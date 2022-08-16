package com.example.hibernate.service;

import com.example.hibernate.exception.EntityNotFoundException;
import com.example.hibernate.model.AddCar;
import com.example.hibernate.model.Car;
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

    public Car updateCar(UUID id, Car car) {

        Car carPom = carRepository.findById(id).get();
        if (carPom.getId() == id) {
            carPom.setRegisterNumber(car.getRegisterNumber());
            carPom.setYearOfManufacture(car.getYearOfManufacture());
            carPom.setUser(car.getUser());
            carPom.setManufacturer(car.getManufacturer());
            return carPom;
        }
        return car;
    }

    public Car deleteById(UUID id) throws EntityNotFoundException {
        Optional<Car> optCar = carRepository.findById(id);

        if (optCar.isEmpty()) {
            throw new EntityNotFoundException("error.../nSorry but we could not find a car with that ID/nPlease try again.");
        } else {
            carRepository.deleteById(id);
            return optCar.get();
        }
    }

    public void deleteAllCars() {
        carRepository.deleteAll();
    }
}
