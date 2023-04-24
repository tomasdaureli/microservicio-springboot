package tomas.carservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tomas.carservice.entity.CarEntity;
import tomas.carservice.repository.CarRepository;

@Service
public class CarService {

    @Autowired
    CarRepository carRepository;

    public List<CarEntity> getAll() {
        return (List<CarEntity>) carRepository.findAll();
    }

    public CarEntity getUserById(int id) {
        return carRepository.findById(id).orElse(null);
    }

    public CarEntity save(CarEntity car) {
        return carRepository.save(car);
    }

    public List<CarEntity> byUserId(int userId) {
        return carRepository.findByUserId(userId);
    }
    
}
