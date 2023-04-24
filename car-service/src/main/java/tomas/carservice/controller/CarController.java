package tomas.carservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tomas.carservice.entity.CarEntity;
import tomas.carservice.service.CarService;

@RestController
@RequestMapping("/car")
public class CarController {

    @Autowired
    CarService carService;

    @GetMapping("/all")
    public ResponseEntity<List<CarEntity>> getAll() {
        List<CarEntity> cars = carService.getAll();
        if (cars.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(cars);
    }

    @GetMapping("{id}")
    public ResponseEntity<CarEntity> getById(@PathVariable("id") int id) {
        CarEntity car = carService.getUserById(id);
        if (car == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(car);
    }

    @PostMapping("/create")
    public ResponseEntity<CarEntity> save(@RequestBody CarEntity car) {
        CarEntity carNew = carService.save(car);
        return ResponseEntity.ok(carNew);
    }

    @GetMapping("/byuser/{userId}")
    public ResponseEntity<List<CarEntity>> getByUserId(@PathVariable("userId") int userId) {
        List<CarEntity> cars = carService.byUserId(userId);
        // if (cars.isEmpty()) {
        //     return ResponseEntity.noContent().build();
        // }
        return ResponseEntity.ok(cars);
    }
    
}
