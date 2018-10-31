package com.apap.tutorial7.service;

import java.util.List;
import java.util.Optional;

import com.apap.tutorial7.model.CarModel;
import com.apap.tutorial7.repository.CarDb;

public interface CarService {
	CarModel addCar(CarModel car);
	
	List<CarModel> sortByPriceDesc(Long dealer_id);
	
	void deleteCar(CarModel car);
	
	Optional<CarModel> getCar(Long id);
	
	void updateCar(long id, CarModel car);
	
	List<CarModel> viewAllCar();
}