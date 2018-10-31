package com.apap.tutorial7.service;

import java.util.List;
import java.util.Optional;

import com.apap.tutorial7.model.DealerModel;
import com.apap.tutorial7.repository.DealerDb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DealerServiceImpl implements DealerService{
	@Autowired
	private DealerDb dealerDb;
	
	@Override
	public Optional<DealerModel> getDealerDetailById(Long id) {
		return dealerDb.findById(id);
	}
	
	@Override
	public DealerModel addDealer(DealerModel dealer) {
		return dealerDb.save(dealer);
	}
	
	@Override
	public void deleteDealer(DealerModel dealer) {
		dealerDb.delete(dealer);
	}
	
	@Override
	public void updateDealer(long id, DealerModel newDealer) {
		DealerModel dealerUpdated = dealerDb.getOne(id);
		dealerUpdated.setAlamat(newDealer.getAlamat());
		dealerUpdated.setNoTelp(newDealer.getNoTelp());
		dealerDb.save(dealerUpdated);
	}
	
	@Override
	public List<DealerModel> viewAllDealer() {
		return dealerDb.findAll();
	}
}
