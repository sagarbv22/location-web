package com.pscube.location.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pscube.location.entities.Location;

public interface LocationRepository extends JpaRepository<Location, Integer> {

}
