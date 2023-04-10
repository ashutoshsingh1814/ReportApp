package com.ashu.runner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.ashu.entity.Client;
import com.ashu.repo.ClientRepo;

@Component
public class DataLoader implements ApplicationRunner {

	@Autowired
	private ClientRepo repo;
	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		
		repo.deleteAll();
		// creating data for Cash -  Approved
		Client c1 = new Client();
		c1.setClientName("Smith");
		c1.setPlanName("Cash");
		c1.setPlanStatus("Approved");
		c1.setGender("Male");
		c1.setPlanStartDate(LocalDate.now());
		c1.setPlanEndDate(LocalDate.now().plusMonths(6));
		c1.setBenifitAmt(5000.00);
		
		
		// Denied
		Client c2 = new Client();
		c2.setClientName("John");
		c2.setPlanName("Cash");
		c2.setPlanStatus("Denied");
		c2.setGender("Male");
        c2.setDeniedReason("Regular Income");
        
        // Terminated
		Client c3 = new Client();
		c3.setClientName("Hemanth");
		c3.setPlanName("Cash");
		c3.setPlanStatus("Terminated");
		c3.setGender("Male");
		c3.setPlanStartDate(LocalDate.now().minusMonths(4));
		c3.setPlanEndDate(LocalDate.now().plusMonths(2));
		c3.setBenifitAmt(5000.00);
		c3.setTerminatedDate(LocalDate.now());
        c3.setTerminatedReason("Employed ");
        
        
    	// creating data for Food  -  Approved
		Client c4 = new Client();
		c4.setClientName("Singham");
		c4.setPlanName("Food");
		c4.setPlanStatus("Approved");
		c4.setGender("Male");
		c4.setPlanStartDate(LocalDate.now());
		c4.setPlanEndDate(LocalDate.now().plusMonths(6));
		c4.setBenifitAmt(5000.00);
		
		
		// Denied
		Client c5 = new Client();
		c5.setClientName("Joesh");
		c5.setPlanName("Food");
		c5.setPlanStatus("Denied");
		c5.setGender("Male");
        c5.setDeniedReason("Regular Income");
        
        // Terminated
		Client c6 = new Client();
		c6.setClientName("Vishwas");
		c6.setPlanName("Food");
		c6.setPlanStatus("Terminated");
		c6.setGender("Male");
		c6.setPlanStartDate(LocalDate.now().minusMonths(4));
		c6.setPlanEndDate(LocalDate.now().plusMonths(2));
		c6.setBenifitAmt(5000.00);
		c6.setTerminatedDate(LocalDate.now());
        c6.setTerminatedReason("Employed ");
        
        
    	// creating data for Medical -  Approved
		Client c7 = new Client();
		c7.setClientName("Hupau");
		c7.setPlanName("Medical");
		c7.setPlanStatus("Approved");
		c7.setGender("Female");
		c7.setPlanStartDate(LocalDate.now());
		c7.setPlanEndDate(LocalDate.now().plusMonths(6));
		c7.setBenifitAmt(5000.00);
		
		
		// Denied
		Client c8 = new Client();
		c8.setClientName("Shema");
		c8.setPlanName("Medical");
		c8.setPlanStatus("Denied");
		c8.setGender("Female");
        c8.setDeniedReason("Regular Income");
        
        // Terminated
		Client c9 = new Client();
		c9.setClientName("Harshit");
		c9.setPlanName("Medical");
		c9.setPlanStatus("Terminated");
		c9.setGender("Male");
		c9.setPlanStartDate(LocalDate.now().minusMonths(4));
		c9.setPlanEndDate(LocalDate.now().plusMonths(2));
		c9.setBenifitAmt(5000.00);
		c9.setTerminatedDate(LocalDate.now());
        c9.setTerminatedReason("Employed ");
        

    	// creating data for Employment -  Approved
		Client c10 = new Client();
		c10.setClientName("Raja");
		c10.setPlanName("Employment");
		c10.setPlanStatus("Approved");
		c10.setGender("Male");
		c10.setPlanStartDate(LocalDate.now());
		c10.setPlanEndDate(LocalDate.now().plusMonths(6));
		c10.setBenifitAmt(5000.00);
		
		
		// Denied
		Client c11 = new Client();
		c11.setClientName("Rahul");
		c11.setPlanName("Employment");
		c11.setPlanStatus("Denied");
		c11.setGender("Male");
        c11.setDeniedReason("Regular Income");
        
        // Terminated
		Client c12 = new Client();
		c12.setClientName("Sami");
		c12.setPlanName("Employment");
		c12.setPlanStatus("Terminated");
		c12.setGender("Male");
		c12.setPlanStartDate(LocalDate.now().minusMonths(4));
		c12.setPlanEndDate(LocalDate.now().plusMonths(2));
		c12.setBenifitAmt(5000.00);
		c12.setTerminatedDate(LocalDate.now());
        c12.setTerminatedReason("Employed ");
        
       ArrayList<Client> list = new ArrayList<>(Arrays.asList(c1,c2,c3,c4,c5,c6,c7,c8,c9,c10,c11,c12));
       
       
        
        repo.saveAll(list);
        
	}

}
