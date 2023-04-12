package com.ashu.service;

import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
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
	public boolean exportPdf() {

		
		return false;
	}

	@Override
	public boolean exportExcel(HttpServletResponse response)throws Exception {
		Workbook workbook = new HSSFWorkbook();
		Sheet sheet = workbook.createSheet("Plan-data");
		Row headRow = sheet.createRow(0);
		headRow.createCell(0).setCellValue("ID");
		headRow.createCell(1).setCellValue("Client Name");
		headRow.createCell(2).setCellValue("Plan Name");
		headRow.createCell(3).setCellValue("Plan Status");
		headRow.createCell(4).setCellValue("Gender");
		headRow.createCell(5).setCellValue("Plan Start Date");
		headRow.createCell(6).setCellValue("Plan End Date");
		headRow.createCell(7).setCellValue("Benefit Amount");

		List<Client> records = repo.findAll();

		int dataRowIndex = 1;
		for (Client plan : records) {
			Row dataRow = sheet.createRow(dataRowIndex);
			dataRow.createCell(0).setCellValue(plan.getClientId());
			dataRow.createCell(1).setCellValue(plan.getClientName());
			dataRow.createCell(2).setCellValue(plan.getPlanName());
			dataRow.createCell(3).setCellValue(plan.getPlanStatus());
			dataRow.createCell(4).setCellValue(plan.getGender());
			dataRow.createCell(5).setCellValue(plan.getPlanStartDate()+"");
			dataRow.createCell(6).setCellValue(plan.getPlanEndDate()+"");
			if(null != plan.getBenifitAmt()) {
				dataRow.createCell(7).setCellValue(plan.getBenifitAmt());
			}else {
				dataRow.createCell(7).setCellValue("N/A");	
			}
			
			dataRowIndex++;

		}

//		FileOutputStream fos = new FileOutputStream(new File("plan.xlsx"));
//		workbook.write(fos);
//		workbook.close();
		
		
		ServletOutputStream outputStream = response.getOutputStream();
		workbook.write(outputStream);
		workbook.close();
		
		return false;
	}

}
