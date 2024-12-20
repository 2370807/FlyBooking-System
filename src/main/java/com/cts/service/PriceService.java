package com.cts.service;

import java.util.List;
import java.util.Optional;

import com.cts.dto.PriceDTO;
import com.cts.model.Price;

public interface PriceService {
	
	public Price createclass(PriceDTO priceDTO);
	public Price updateclassandprice(long id,PriceDTO price);
	public Optional<Price> getbyclass(String classname);
	public void removeClass(String classname);
	public List<Price> getAll();
	
}
