package tomas.userservice.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import tomas.userservice.entity.UserEntity;
import tomas.userservice.feignclients.BikeFeignClient;
import tomas.userservice.feignclients.CarFeignClient;
import tomas.userservice.model.Bike;
import tomas.userservice.model.Car;
import tomas.userservice.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    CarFeignClient carFeignClient;

    @Autowired
    BikeFeignClient bikeFeignClient;

    public List<UserEntity> getAll() {
        return (List<UserEntity>) userRepository.findAll();
    }

    public UserEntity getUserById(int id) {
        return userRepository.findById(id).orElse(null);
    }

    public UserEntity save(UserEntity user) {
        return userRepository.save(user);
    }

    public List<Car> getCars(int userId) {
        return (List<Car>) restTemplate.getForObject("http://localhost:8002/car/byuser/" + userId, List.class);
    }

    public List<Bike> getBikes(int userId) {
        return (List<Bike>) restTemplate.getForObject("http://localhost:8003/bike/byuser/" + userId, List.class);
    }

    public Car saveCar(int userId, Car car) {
        car.setUserId(userId);
        return carFeignClient.save(car);
    }

    public Bike saveBike(int userId, Bike bike) {
        bike.setUserId(userId);
        return bikeFeignClient.save(bike);
    }

    public Map<String, Object> getUserAndVehicles(int userId) {
        Map<String, Object> result = new HashMap<>();
        Optional<UserEntity> user = userRepository.findById(userId);
        if (!user.isPresent()) {
            result.put("Message", "The user it doesn't exist.");
            return result;
        }
        result.put("User", user);
        List<Car> cars = carFeignClient.getCars(userId);
        if (cars.isEmpty()) {
            result.put("Cars", "The user doesn't have any cars.");
        } else {
            result.put("Cars", cars);
        }
        List<Bike> bikes = bikeFeignClient.getBikes(userId);
        if (bikes.isEmpty()) {
            result.put("Bikes", "The user doesn't have any bikes.");
        } else {
            result.put("Bikes", bikes);
        }
        return result;


    }
    
}
