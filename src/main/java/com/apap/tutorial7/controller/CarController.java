package com.apap.tutorial7.controller;

import com.apap.tutorial7.service.DealerService;
import com.apap.tutorial7.model.*;
import com.apap.tutorial7.rest.DealerDetail;
import com.apap.tutorial7.rest.Setting;
import com.apap.tutorial7.service.*;
import com.apap.tutorial7.rest.DealerDetail;
import com.apap.tutorial7.rest.Setting;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/car")
public class CarController {
	@Autowired
	private CarService carService;
	
	@Autowired
	private DealerService dealerService;
	
	// POST add car
	@PostMapping(value = "/add")
	private CarModel addCarSubmit(@RequestBody CarModel car) {
		return carService.addCar(car);
	}
	
	// GET car
	@GetMapping(value = "/{carId}")
	private CarModel viewCar(@PathVariable ("carId") long carId, Model model) {
		CarModel car = carService.getCar(carId).get();
		car.setDealer(null);
		return car;
	}
	
	// Delete car
	@DeleteMapping(value = "/delete")
	private String deleteCar(@RequestParam("carId") long id, Model model) {
		CarModel car = carService.getCar(id).get();
		carService.deleteCar(car);
		return "car has been deleted";
	}
	
	// PUT update car
	@PutMapping(value = "/{carId}")
	private String updateCarSubmit(
			@PathVariable (value = "carId") long id,
			@RequestParam(value = "brand", required = false) String brand,
			@RequestParam(value = "type", required = false) String type,
			@RequestParam(value = "price", required = false) Long price,
			@RequestParam(value = "amount", required = false) Integer amount,
			@RequestParam(value = "dealerId", required = false) Long dealerId) {
		CarModel car = (CarModel) carService.getCar(id).get();
		if (car.equals(null)) {
			return "Couldn't find your dealer";
		}
		
		if (brand != null) {
			car.setBrand(brand);
		}
		
		if (type != null) {
			car.setType(type);
		}

		if (price != null) {
			car.setPrice(price);
		}
		
		if (amount != null) {
			car.setAmount(amount);
		}
		
		if (dealerId != null) {
			DealerModel dealer = dealerService.getDealerDetailById(dealerId).get();
			car.setDealer(dealer);
		}

		carService.updateCar(id, car);
		return "car update success";
	}
	
	// GET all car
	@GetMapping()
	private List<CarModel> viewAllCar(Model model) {
		List<CarModel> listCar = carService.viewAllCar();
		for (CarModel car : listCar) {
			car.setDealer(null);
		}
		return listCar;
	}

}
