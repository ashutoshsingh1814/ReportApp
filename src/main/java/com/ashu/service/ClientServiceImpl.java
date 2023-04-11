package com.ashu.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.ashu.entity.Client;
import com.ashu.repo.ClientRepo;
import com.ashu.request.SearchRequest;
@Service
public class ClientServiceImpl implements ClientService {

	@Autowired
	private ClientRepo repo;
	
	
	@Override
	public List<String> getPlanName() {
		
		return repo.getPlanName();
	}

	@Override
	public List<String> getPlanStatus() {
		
		return repo.getPlanStatus();
	}

	@Override
	public List<Client> searchResult(SearchRequest request) {
		
		Client entity = new Client();
		if(null!=request.getPlanName() && !"".equals(request.getPlanName())) {
			entity.setPlanName(request.getPlanName());
		}
		if(null!=request.getPlanStatus() && !"".equals(request.getPlanStatus())) {
			entity.setPlanStatus(request.getPlanStatus());
		}
		if(null!=request.getPlanStatus() && !"".equals(request.getPlanStatus())) {
			entity.setPlanStatus(request.getPlanStatus());
		}
		if(null!=request.getGender() && !"".equals(request.getGender())) {
			entity.setGender(request.getGender());
		}
	
		if(null!=request.getPlanStartDate() && !"".equals(request.getPlanStartDate())) {
			String startDate = request.getPlanStartDate();
			// convert String to LocalDate Format
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); 
			LocalDate localDate = LocalDate.parse(startDate, formatter);
            entity.setPlanStartDate(localDate);
		
		}
		
		if(null!=request.getPlanEndDate() && !"".equals(request.getPlanEndDate())) {
			String endDate = request.getPlanEndDate();
			// convert String to LocalDate Format
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); 
			LocalDate localDate1 = LocalDate.parse(endDate, formatter);
            entity.setPlanEndDate(localDate1);
		
		}
		
		
		
		
		
		return repo.findAll(Example.of(entity));
		 
	}

	@Override
	public boolean exportPdf() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean exportExcel() {
		// TODO Auto-generated method stub
		return false;
	}

}
