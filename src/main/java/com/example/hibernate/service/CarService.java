package com.example.hibernate.service;

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

    public List<Car> getCars(){ return carRepository.findAll();}

    public Car getCarById(UUID id) throws Exception{
        Optional<Car> optCar = carRepository.findById(id);

        if (optCar.isEmpty()) {
            throw new Exception("error.../n The value is not present!");
        } else
            return optCar.get();
    }

    public Car addCar(Car car)
    {
        return carRepository.save(new Car(car.getYearOfManufacture(),car.getRegisterNumber()));
    }

    public Car updateCar(@PathVariable("id") UUID id, @RequestBody Car car){

        if (car.getId() == id) {//? Enil pitanje
            Car carPom = carRepository.getReferenceById(id);
            carPom.setRegisterNumber(car.getRegisterNumber());
            carPom.setYearOfManufacture(car.getYearOfManufacture());
            return carPom;
        }
        return car;
    }

    public Car deleteById(@PathVariable("id") UUID id) {
        carRepository.deleteById(id);
        return new Car();
    }

    public void deleteAllCars(){
        carRepository.deleteAll();
    }
}
