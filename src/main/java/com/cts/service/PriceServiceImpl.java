package com.cts.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cts.dto.PriceDTO;
import com.cts.model.Price;
import com.cts.repository.PriceRepository;

@Service
public class PriceServiceImpl implements PriceService {

	@Autowired
	private PriceRepository priceRepository;
	@Override
	public Price createclass(PriceDTO priceDTO) {
		// TODO Auto-generated method stub
		Price price = Price.builder()
					.classname(priceDTO.getClassname())
					.price(priceDTO.getPrice())
					.build();
		
		return priceRepository.save(price);
	}

	@Override
	public Price updateclassandprice(long id,PriceDTO pricedto) {
		// TODO Auto-generated method stub
		Price price1= priceRepository.findById(id)
				.orElseThrow(()-> new RuntimeException("Class not found"));
		price1.setClassname(pricedto.getClassname());
		price1.setPrice(pricedto.getPrice());
		return priceRepository.save(price1);
	}

	@Override
	public Optional<Price> getbyclass(String classname) {
		// TODO Auto-generated method stub
		return priceRepository.findByClassname(classname);
	}

	@Override
	@Transactional
	public void removeClass(String classname) {
		// TODO Auto-generated method stub
//		TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionOperations.Propagation.REQUIRED);
		priceRepository.deleteByClassname(classname);
//		try 
//		{ 
//			priceRepository.deleteByClassname(classname); 
//		} catch (Exception e) 
//		{ 
//			transactionManager.rollback(status);
//		}
		
	}

	@Override
	public List<Price> getAll() {
		// TODO Auto-generated method stub
		return priceRepository.findAll();
	}

}
