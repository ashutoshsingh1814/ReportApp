package com.ashu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.ashu.entity.Client;
import com.ashu.request.SearchRequest;
import com.ashu.service.ClientService;

@Controller
public class ReportController {
	
	@Autowired
	private ClientService serv;
	
	@PostMapping("/search")
	public String handleSearch(@ModelAttribute("searchReq")  SearchRequest request, Model model) {
		
		System.out.println(request);
		
	   List<Client> plans= serv.searchResult(request);
	   model.addAttribute("plans", plans);  // sending data to UI
		init(model);
		return "index";
		
	}
	
	
	@GetMapping("/")
	public String indexPage(Model model)
	{
		
		model.addAttribute("searchReq", new SearchRequest());
		init(model);
		return "index";
	}


	private void init(Model model) {
		
		
		model.addAttribute("plansName", serv.getPlanName());
		model.addAttribute("status", serv.getPlanStatus());
		
	}
	


}
