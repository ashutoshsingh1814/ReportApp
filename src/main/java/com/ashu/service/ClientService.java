package com.ashu.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.ashu.entity.Client;
import com.ashu.request.SearchRequest;

public interface ClientService {
	
	public List<String> getPlanName();
	public List<String> getPlanStatus();
	
	public List<Client> searchResult(SearchRequest request);
	
	public boolean exportPdf();
	public boolean exportExcel(HttpServletResponse response) throws Exception;

}
