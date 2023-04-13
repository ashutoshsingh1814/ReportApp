package com.ashu.controller;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.ashu.entity.Client;
import com.ashu.request.SearchRequest;
import com.ashu.service.ClientService;
import com.ashu.util.EmailSender;

@Controller
public class ReportController {

	@Autowired
	private ClientService serv;

	@Autowired
	private EmailSender emailsend;

	@PostMapping("/search")
	public String handleSearch(@ModelAttribute("searchReq") SearchRequest request, Model model) {

		System.out.println(request);

		List<Client> plans = serv.searchResult(request);
		model.addAttribute("plans", plans); // sending data to UI
		init(model);
		return "index";

	}

	@GetMapping("/")
	public String indexPage(Model model) {

		model.addAttribute("searchReq", new SearchRequest());
		init(model);
		return "index";
	}

	private void init(Model model) {

		model.addAttribute("plansName", serv.getPlanName());
		model.addAttribute("status", serv.getPlanStatus());

	}

	@GetMapping("/excel")
	public void excelExport(HttpServletResponse response) throws Exception {
		response.setContentType("application/octet-stream");
		response.addHeader("content-disposition", "attachment;filename=plans.xls");

		serv.exportExcel(response);

	}

	@GetMapping("/pdf")
	public void pdfExport(HttpServletResponse response) throws Exception {
		response.setContentType("application/pdf");
		response.addHeader("content-disposition", "attachment;filename=plans.pdf");

//		String subject="Test Mail";
//		String body="<h1>This mail from Spring boot Application</h1>";
//		String to="ashutoshsinghvit@gmail.com";
//		emailsend.sendMail(subject, body, to);
		serv.exportPdf(response);

	}

}
