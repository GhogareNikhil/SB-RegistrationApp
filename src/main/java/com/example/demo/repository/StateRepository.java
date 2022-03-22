package com.example.demo.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.entity.StateEntity;

@Repository
public interface StateRepository extends JpaRepository<StateEntity, Serializable> {
  public List<StateEntity> findByCountryId(Integer countryId);
}
