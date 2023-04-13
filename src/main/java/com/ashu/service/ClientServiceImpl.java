package com.ashu.service;

import java.io.OutputStream;
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
import com.ashu.util.ExportExcel;
import com.ashu.util.ExportPdf;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.PageSize;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfTable;
import com.lowagie.text.pdf.PdfWriter;

@Service
public class ClientServiceImpl implements ClientService {

	@Autowired
	private ClientRepo repo;
	@Autowired
	private ExportExcel excel;
	@Autowired
	private ExportPdf pdf;

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

		List<Client> plans = repo.findAll();
		pdf.exportExcel(response, plans);

		return true;
	}

	@Override
	public boolean exportExcel(HttpServletResponse response) throws Exception {
		List<Client> records = repo.findAll();
		excel.getExportExcel(response,records);
		
		return true;
	}

}
