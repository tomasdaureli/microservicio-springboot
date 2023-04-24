package tomas.bikeservice.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tomas.bikeservice.entity.BikeEntity;

@Repository
public interface BikeRepository extends CrudRepository<BikeEntity, Integer> {

    List<BikeEntity> findByUserId(int userId);
    
}
