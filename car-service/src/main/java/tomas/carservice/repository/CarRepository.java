package tomas.carservice.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tomas.carservice.entity.CarEntity;

@Repository
public interface CarRepository extends CrudRepository<CarEntity, Integer> {

    List<CarEntity> findByUserId(int userId);
    
}
