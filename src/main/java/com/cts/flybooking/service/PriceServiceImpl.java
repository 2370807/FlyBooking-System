package com.cts.flybooking.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cts.flybooking.dto.PriceDTO;
import com.cts.flybooking.model.Price;
import com.cts.flybooking.repository.PriceRepository;
import com.cts.flybooking.repository.SeatRepository;

@Service
public class PriceServiceImpl implements PriceService {

	private static final Logger logger = LoggerFactory.getLogger(PriceServiceImpl.class);
	
	@Autowired
	private PriceRepository priceRepository;
	
	@Autowired
	private SeatRepository seatRepository;
	
	@Override
	public Price createclass(PriceDTO priceDTO) {
		// TODO Auto-generated method stub
		logger.info("Creating price class with class name: {}", priceDTO.getClassname());
		Price price = Price.builder()
					.classname(priceDTO.getClassname())
					.price(priceDTO.getPrice())
					.build();
		logger.info("Price class created successfully done");
		return priceRepository.save(price);
	}

	@Override
	public Price updateclassandprice(long id,PriceDTO pricedto) {
		// TODO Auto-generated method stub
		logger.info("Updating price class with id: {}", id);
		Price price1= priceRepository.findById(id)
				.orElseThrow(()-> new RuntimeException("Class not found"));
		price1.setClassname(pricedto.getClassname());
		price1.setPrice(pricedto.getPrice());
		logger.info("Price class updated successfully done");
		return priceRepository.save(price1);
	}

	@Override
	public Optional<Price> getbyclass(String classname) {
		// TODO Auto-generated method stub
		logger.info("Fetching price class by class name: {}",classname);
		return priceRepository.findByClassname(classname);
	}

	@Override
	@Transactional
	public void removeClass(String classname) {
		// TODO Auto-generated method stub
		logger.info("Removing price class with class name: {}", classname);
		priceRepository.deleteByClassname(classname);
		logger.info("Price class removed successfully for class name: {}", classname);	
	}

	@Override
	public List<Price> getAll() {
		// TODO Auto-generated method stub
		logger.info("Fetching all price classes");
		return priceRepository.findAll();
	}
	
}
