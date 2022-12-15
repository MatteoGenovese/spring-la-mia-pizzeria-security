package org.generation.italy.demo.service;

import java.util.List;
import java.util.Optional;

import org.generation.italy.demo.pojo.Promoting;
import org.generation.italy.demo.repository.PromotingRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class PromotingService {
	
	@Autowired
	private PromotingRepository promotingRepository;
	
	public void save(Promoting promoting)
	{
		promotingRepository.save(promoting);
	}
	
	public List<Promoting> findAll(){
		return promotingRepository.findAll();
	}
	
	
	public void delete(int id)
	{
		promotingRepository.deleteById(id);
	}

	
	public Optional<Promoting> getPromotingById(int id)
	{
		return promotingRepository.findById(id);
	}

	@Transactional
	public List<Promoting> findAllWPizzaList() {
		
		List<Promoting> promotingList = promotingRepository.findAll();
		
		for (Promoting promoting : promotingList) {
			
			Hibernate.initialize(promoting.getPizzaList());
		}
		
		return promotingList;
	}
	



}
