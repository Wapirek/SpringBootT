package dev.norka.homework;

import dev.norka.homework.controller.CarApi;
import dev.norka.homework.model.Car;
import dev.norka.homework.service.CarService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@WebMvcTest(CarApi.class)
class CarApiTest {


    Car car1, car2;
    private CarApi carApi;

    private final CarService carService;

    @Autowired
    CarApiTest(CarService carService) {
        this.carService = carService;
        carApi = new CarApi(carService);
    }


    @Test
    void getAllCars() {
        ResponseEntity<List<Car>> response = carApi.getAllCars();
        List<Car> cars = new ArrayList<>(List.of(car1, car2));
        assertEquals(cars, response.getBody());
    }

    @Test
    void getCarById() {
        ResponseEntity<Car> response = carApi.getCarById(1);
        assertEquals(car1, response.getBody());
    }

    @Test
    void getCarsByColors() {
        ResponseEntity<List<Car>> response = carApi.getCarsByColors("green");
        assertEquals(List.of(car2), response.getBody());
    }

    @Test
    void addNewCar() {
        Car car = new Car(3, "Honda", "Accord", "red");
        ResponseEntity<Car> response = carApi.addNewCar(car);
        assertEquals(car, response.getBody());

    }
//  TODO:Finish test!
//    @Test
//    void modCarById() {
//    }

    @Test
    void deleteCarById() {
        Car car = new Car(3, "Honda", "Accord", "red");
        carApi.deleteCarById(3);
        ResponseEntity<List<Car>> response = carApi.getAllCars();
        List<Car> cars = new ArrayList<>(List.of(car1, car2));
        assertEquals(cars, response.getBody());
    }


}