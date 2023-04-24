package tomas.bikeservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tomas.bikeservice.entity.BikeEntity;
import tomas.bikeservice.service.BikeService;

@RestController
@RequestMapping("/bike")
public class BikeController {

    @Autowired
    BikeService bikeService;

    @GetMapping("/all")
    public ResponseEntity<List<BikeEntity>> getAll() {
        List<BikeEntity> bikes = bikeService.getAll();
        if (bikes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(bikes);
    }

    @GetMapping("{id}")
    public ResponseEntity<BikeEntity> getById(@PathVariable("id") int id) {
        BikeEntity bike = bikeService.getUserById(id);
        if (bike == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(bike);
    }

    @PostMapping("/create")
    public ResponseEntity<BikeEntity> save(@RequestBody BikeEntity bike) {
        BikeEntity bikeNew = bikeService.save(bike);
        return ResponseEntity.ok(bikeNew);
    }

    @GetMapping("/byuser/{userId}")
    public ResponseEntity<List<BikeEntity>> getByUserId(@PathVariable("userId") int userId) {
        List<BikeEntity> bikes = bikeService.byUserId(userId);
        // if (bikes.isEmpty()) {
        //     return ResponseEntity.noContent().build();
        // }
        return ResponseEntity.ok(bikes);
    }
    
}
