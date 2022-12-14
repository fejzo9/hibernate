package com.example.hibernate.service;

import com.example.hibernate.exception.EntityNotFoundException;
import com.example.hibernate.model.*;
import com.example.hibernate.repository.CarRepository;
import com.example.hibernate.repository.ManufacturerRepository;
import com.example.hibernate.repository.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class CarService {
    private final CarRepository carRepository;
    private final UserRepository userRepository;
    private final ManufacturerRepository manufacturerRepository;

    public CarService(CarRepository carRepository, UserRepository userRepository, ManufacturerRepository manufacturerRepository) {
        this.carRepository = carRepository;
        this.userRepository = userRepository;
        this.manufacturerRepository = manufacturerRepository;
    }

    public List<Car> getCars() {
        return carRepository.findAll();
    }

    public Car getCarById(UUID id) throws Exception {
        Optional<Car> optCar = carRepository.findById(id);

        if (optCar.isEmpty()) {
            throw new Exception("error.../n The value is not present!");
        } else return optCar.get();
    }

    public Car addCar(final AddCar addCar) {
        return carRepository.save(new Car(addCar.yearOfManufacture(), addCar.registerNumber(), addCar.user(), addCar.manufacturer()));
    }

    public Car updateCar(final UUID id, final UpdateCar updateCar) throws EntityNotFoundException {

        Optional<Car> optCar = carRepository.findById(id);
        if (optCar.isEmpty()) {
            throw new EntityNotFoundException("Car", id);
        }
        Car carPom = optCar.get();
        carPom.setYearOfManufacture(updateCar.yearOfManufacture());
        carPom.setRegisterNumber(updateCar.registerNumber());
//        carPom.setUser(updateCar.user());
        carPom.setManufacturer(updateCar.manufacturer());
        return carRepository.save(carPom);

    }

    public Car buyCar(final UUID carId, final UUID userId) throws EntityNotFoundException {
        Optional<Car> optCar = carRepository.findById(carId);
        if (optCar.isEmpty()) {
            throw new EntityNotFoundException("Car", carId);
        }

        Optional<User> optUser = userRepository.findById(userId);
        if (optUser.isEmpty()) {
            throw new EntityNotFoundException("User", carId);
        }

        Car car = optCar.get();
        User user = optUser.get();
        car.setUser(user);
        return carRepository.save(car);
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

    public List<Car> getCarsByManufacturer(UUID manufacturerId) {
        Optional<Manufacturer> optionalManufacturer = manufacturerRepository.findById(manufacturerId);
        if (optionalManufacturer.isEmpty()) {
            throw new EntityNotFoundException("Manufacturer", manufacturerId);
        } else {
            List<Car> cars = carRepository.findAll();
            List<Car> carsFromManufacturer = new ArrayList<>();
            for (int i = 0; i < cars.size(); i++) {
                if (cars.get(i).getManufacturer() == optionalManufacturer.get())
                    carsFromManufacturer.add(cars.get(i));
            }
            return carsFromManufacturer;
        }
    }
}
