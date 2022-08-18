package com.example.hibernate.service;

import com.example.hibernate.exception.EntityNotFoundException;
import com.example.hibernate.model.AddCar;
import com.example.hibernate.model.Car;
import com.example.hibernate.model.UpdateCar;
import com.example.hibernate.model.User;
import com.example.hibernate.repository.CarRepository;
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

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
        userRepository = null;
    }

    public CarService(){this.carRepository = null; this.userRepository=null; }
    public CarService(CarRepository carRepository, UserRepository userRepository) {
        this.carRepository = carRepository;
        this.userRepository = userRepository;
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

    public Car updateCar(final UUID id, final UpdateCar updateCar) throws EntityNotFoundException {

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

    public Car buyCar(final UUID carId, final UUID userId, final UpdateCar updateCar) throws EntityNotFoundException {
        Optional<Car> optCar = carRepository.findById(carId);
        Optional<User> optUser = userRepository.findById(userId);

        if (optCar.isEmpty() || optUser.isEmpty()) {
            throw new EntityNotFoundException("Car", carId, userId);
        }
        Car car = optCar.get();
        User user = optUser.get();
//        car.setManufacturer(updateCar.manufacturer());
//        car.setRegisterNumber(updateCar.registerNumber());
//        car.setYearOfManufacture(updateCar.yearOfManufacture());
        car.setUser(user);
//        List<Car> cars = new ArrayList<>();
//        cars.add(car);
//        user.setCars(cars);
//         userRepository.save(user);
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
}
