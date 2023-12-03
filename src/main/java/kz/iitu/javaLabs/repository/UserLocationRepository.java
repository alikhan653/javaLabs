package kz.iitu.javaLabs.repository;

import kz.iitu.javaLabs.model.UserLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserLocationRepository extends JpaRepository<UserLocation, Long> {
    List<UserLocation> findAllByUser_Id(Long userId);
}