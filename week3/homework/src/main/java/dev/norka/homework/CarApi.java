package dev.norka.homework;

import dev.norka.homework.model.Car;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/car")
public class CarApi {
    private List<Car> carList;

    public CarApi() {
        carList = new ArrayList<>();
    }

    @GetMapping()
    public ResponseEntity<List<Car>> getAllCars() {
        return new ResponseEntity<>(carList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable long id) {
        Optional<Car> first = carList.stream().filter(car1 -> car1.getId() == id).findFirst();
        return first.map(car -> new ResponseEntity<>(car, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/color/{color}")
    public ResponseEntity<List<Car>> getCarsByColors(@PathVariable String color) {
        Optional<List<Car>> cars = Optional.of(carList.stream().filter(e -> e.getColor().equals(color)).toList());
        return new ResponseEntity<>(cars.get(), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Car> addNewCar(@RequestBody Car car) {
        carList.add(car);
        return new ResponseEntity<>(car, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Car> modCarById(@PathVariable long id, @RequestBody Car car) {
        Optional<Car> first = carList.stream().filter(c -> c.getId() == id).findFirst();
        if (first.isPresent()) {
            carList.remove(first.get());
            carList.add(car);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Car> deleteCarById(@PathVariable long id) {
        Optional<Car> first = carList.stream().filter(car -> car.getId() == id).findFirst();
        if (first.isPresent()) {
            carList.remove(first.get());
            return new ResponseEntity<>(first.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


}
