package com.example.demo.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.CityEntity;
@Repository
public interface CityRepository extends JpaRepository<CityEntity, Serializable>{
 
	
	public List<CityEntity> findByStateID(Integer stateID);
}
