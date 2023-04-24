package tomas.bikeservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tomas.bikeservice.entity.BikeEntity;
import tomas.bikeservice.repository.BikeRepository;

@Service
public class BikeService {

    @Autowired
    BikeRepository bikeRepository;

    public List<BikeEntity> getAll() {
        return (List<BikeEntity>) bikeRepository.findAll();
    }

    public BikeEntity getUserById(int id) {
        return bikeRepository.findById(id).orElse(null);
    }

    public BikeEntity save(BikeEntity bike) {
        return bikeRepository.save(bike);
    }

    public List<BikeEntity> byUserId(int userId) {
        return (List<BikeEntity>) bikeRepository.findByUserId(userId);
    }
    
}
