package com.ashu.util;

import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Component;

import com.ashu.entity.Client;
@Component
public class ExportExcel {
	
	public void getExportExcel(HttpServletResponse response, List<Client> records) throws Exception {
		
		
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

		

		int dataRowIndex = 1;
		for (Client plan : records) {
			Row dataRow = sheet.createRow(dataRowIndex);
			dataRow.createCell(0).setCellValue(plan.getClientId());
			dataRow.createCell(1).setCellValue(plan.getClientName());
			dataRow.createCell(2).setCellValue(plan.getPlanName());
			dataRow.createCell(3).setCellValue(plan.getPlanStatus());
			dataRow.createCell(4).setCellValue(plan.getGender());
			dataRow.createCell(5).setCellValue(plan.getPlanStartDate() + "");
			dataRow.createCell(6).setCellValue(plan.getPlanEndDate() + "");
			if (null != plan.getBenifitAmt()) {
				dataRow.createCell(7).setCellValue(plan.getBenifitAmt());
			} else {
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

		
		
	}
	

}
