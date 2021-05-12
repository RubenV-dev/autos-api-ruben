package com.galvanize.autos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AutosRepository extends JpaRepository<Automobile, Long> {
    List<Automobile> findByColorContains(String color);
    List<Automobile> findByOwnerContains(String owner);
    List<Automobile> findByMakeContains(String make);
    List<Automobile> findByColorContainsAndOwnerContains(String color, String owner);
    List<Automobile> findByOwnerContainsAndMakeContains(String owner, String make);
    List<Automobile> findByMakeContainsAndColorContains(String make, String color);
    List<Automobile> findByColorContainsAndOwnerContainsAndMakeContains(String color, String owner, String make);
}
