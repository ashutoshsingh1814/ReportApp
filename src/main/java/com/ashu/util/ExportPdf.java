package com.ashu.util;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import com.ashu.entity.Client;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

@Component
public class ExportPdf {
	
	public void exportExcel(HttpServletResponse response, List<Client> plans, File f) throws Exception{
		
	
		Document document = new Document(PageSize.A4);
		PdfWriter.getInstance(document, response.getOutputStream());
		PdfWriter.getInstance(document, new FileOutputStream(f));
		document.open();

		Font fontTiltle = FontFactory.getFont(FontFactory.TIMES_ROMAN);
		fontTiltle.setSize(20);

		Paragraph paragraph = new Paragraph("Client Information List For Insurance");
		paragraph.setAlignment(Paragraph.ALIGN_CENTER);
		document.add(paragraph);
		PdfPTable table = new PdfPTable(7);
		table.addCell("Client Id");
		table.addCell("Client Name");
		table.addCell("Plan Name");
		table.addCell("Plan Status");
		table.addCell("Gender");
		table.addCell("Plan Start Date");
		table.addCell("Plan End Date");

		

		for (Client plan : plans) {
			table.addCell(String.valueOf(plan.getClientId()));
			table.addCell(plan.getClientName());
			table.addCell(plan.getPlanName());
			table.addCell(plan.getPlanStatus());
			table.addCell(plan.getGender());
			table.addCell(plan.getPlanStartDate() + "");
			table.addCell(plan.getPlanEndDate() + "");

		}
		document.add(table);
		
		
		

		document.close();
	}

}
