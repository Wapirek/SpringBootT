package dev.norka.homework.service;

import dev.norka.homework.model.Car;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CarService {
    private List<Car> carList;

    public CarService() {
        this.carList = new ArrayList<>();
        this.carList.add(new Car(1L, "Honda", "Civic", "red"));
        this.carList.add(new Car(2L, "Toyota", "Yaris", "green"));
    }

    public List<Car> getAllCars(){
        return carList;
    }

    public Optional<Car> getCarById(Long id){
        return carList.stream().filter(car1 -> car1.getId() == id).findFirst();
    }

    public Optional<List<Car>> getCarsByColors(String color){
        return Optional.of(carList.stream().filter(e -> e.getColor().equals(color)).toList());
    }

    public boolean addCar(Car car){
        return carList.add(car);
    }

    public Car modifyCar(Car car){
        Optional<Car> first = carList.stream().filter(c -> c.getId() == car.getId()).findFirst();
        if (first.isPresent()) {
            carList.remove(first.get());
            addCar(car);
            return car;
        }
        return null;
    }

    public boolean deleteCarBuId(Long id){
        Optional<Car> first = carList.stream().filter(car -> car.getId() == id).findFirst();
        if (first.isPresent()) {
            carList.remove(first.get());
            return true;
        }
        return false;
    }



}
