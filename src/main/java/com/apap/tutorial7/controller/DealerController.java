package com.apap.tutorial7.controller;

import java.util.List;
import java.util.Optional;

import com.apap.tutorial7.model.*;
import com.apap.tutorial7.service.*;
import com.apap.tutorial7.repository.CarDb;
import com.apap.tutorial7.repository.DealerDb;
import com.apap.tutorial7.rest.DealerDetail;
import com.apap.tutorial7.rest.Setting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;


@RestController
@RequestMapping("/dealer")
public class DealerController {
	@Autowired
	private DealerService dealerService;
	
	@Autowired
	RestTemplate restTemplate;
	
	@Bean
	public RestTemplate rest() {
		return new RestTemplate();
	}
	
	@PostMapping(value = "/add")
	private DealerModel addDealerSubmit(@RequestBody DealerModel dealer) {
		return dealerService.addDealer(dealer);
	}
	
	@GetMapping(value = "/{dealerId}")
	private DealerModel viewDealer(@PathVariable ("dealerId") long dealerId, Model model) {
		return dealerService.getDealerDetailById(dealerId).get();
	}
	
	@DeleteMapping(value = "/delete")
	private String deleteDealer(@RequestParam("dealerId") long id, Model model) {
		DealerModel dealer = dealerService.getDealerDetailById(id).get();
		dealerService.deleteDealer(dealer);
		return "success";
	}
	
	@PutMapping(value = "/{id}")
	private String updateDealerSubmit(
			@PathVariable(value = "id") long id,
			@RequestParam("alamat") String alamat,
			@RequestParam("telp") String telp) {
		DealerModel dealer = (DealerModel) dealerService.getDealerDetailById(id).get();
		if (dealer.equals(null)) {
			return "Couldn't find your dealer";
		}
		dealer.setAlamat(alamat);
		dealer.setNoTelp(telp);
		dealerService.updateDealer(id, dealer);
		return "dealer updated";
	}
	
	@GetMapping()
	private List<DealerModel> viewAllDealer(Model model) {
		return dealerService.viewAllDealer();
	}
	
	@GetMapping(value = "/status/{dealerId}")
	private String getStatus(@PathVariable ("dealerId") long dealerId) throws Exception {
		String path = Setting.dealerUrl + "/dealer?id=" + dealerId;
		return restTemplate.getForEntity(path, String.class).getBody();
	}
	
	@GetMapping(value = "/full/{dealerId}")
	private DealerDetail postStatus(@PathVariable ("dealerId") long dealerId) throws Exception {
		String path = Setting.dealerUrl + "/dealer";
		DealerModel dealer = dealerService.getDealerDetailById(dealerId).get();
		DealerDetail detail = restTemplate.postForObject(path, dealer, DealerDetail.class);
		return detail;
	}
}
