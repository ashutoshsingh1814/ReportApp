package com.ashu.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ashu.entity.Client;

public interface ClientRepo extends JpaRepository<Client, Integer> {
	
	@Query("select distinct(planName) from Client")
	public List<String> getPlanName();
	
	@Query("select distinct(planStatus) from Client")
	public List<String> getPlanStatus();
	

}
