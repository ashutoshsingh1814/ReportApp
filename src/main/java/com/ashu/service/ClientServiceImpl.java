package com.ashu.service;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.ashu.entity.Client;
import com.ashu.repo.ClientRepo;
import com.ashu.request.SearchRequest;
import com.ashu.util.EmailSender;
import com.ashu.util.ExportExcel;
import com.ashu.util.ExportPdf;

@Service
public class ClientServiceImpl implements ClientService {

	@Autowired
	private ClientRepo repo;
	@Autowired
	private ExportExcel excel;
	@Autowired
	private ExportPdf pdf;
	@Autowired
	private EmailSender emailsend;

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
		if (null != request.getPlanName() && !"".equals(request.getPlanName())) {
			entity.setPlanName(request.getPlanName());
		}
		if (null != request.getPlanStatus() && !"".equals(request.getPlanStatus())) {
			entity.setPlanStatus(request.getPlanStatus());
		}
		if (null != request.getPlanStatus() && !"".equals(request.getPlanStatus())) {
			entity.setPlanStatus(request.getPlanStatus());
		}
		if (null != request.getGender() && !"".equals(request.getGender())) {
			entity.setGender(request.getGender());
		}

		if (null != request.getPlanStartDate() && !"".equals(request.getPlanStartDate())) {
			String startDate = request.getPlanStartDate();
			// convert String to LocalDate Format
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate localDate = LocalDate.parse(startDate, formatter);
			entity.setPlanStartDate(localDate);

		}

		if (null != request.getPlanEndDate() && !"".equals(request.getPlanEndDate())) {
			String endDate = request.getPlanEndDate();
			// convert String to LocalDate Format
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate localDate1 = LocalDate.parse(endDate, formatter);
			entity.setPlanEndDate(localDate1);

		}

		return repo.findAll(Example.of(entity));

	}

	@Override
	public boolean exportPdf(HttpServletResponse response) throws Exception {
		File f = new File("plans.pdf");
		List<Client> plans = repo.findAll();
		pdf.exportExcel(response, plans, f);
		
		

		String subject = "Test Mail";
		String body = "<h1>This mail from Spring boot Application</h1>";
		String to = "ashutoshsinghvit@gmail.com";

		emailsend.sendMail(subject, body, to, f);
		f.delete();

		return true;
	}

	@Override
	public boolean exportExcel(HttpServletResponse response) throws Exception {
		List<Client> records = repo.findAll();
		excel.getExportExcel(response,records);
		File f = new File("plans.xls");

		String subject = "Test Mail";
		String body = "<h1>This mail from Spring boot Application</h1>";
		String to = "ashutoshsinghvit@gmail.com";

		emailsend.sendMail(subject, body, to, f);
		f.delete();
		
		
		
		return true;
	}

}
