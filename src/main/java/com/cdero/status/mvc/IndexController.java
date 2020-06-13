package com.cdero.status.mvc;

import java.util.ArrayList;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.cdero.status.ping.PingScheduler;

/**
 * @author 	Christopher DeRoche
 * @version	0.2
 * @since	0.1
 * 
 */

@Controller
public class IndexController {
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	private String index(final Model model) {
		
		model.addAttribute("statusTableData", PingScheduler.getStatusTable());
		return "index";
		
	}
	
	private ArrayList<StatusTableModel> testTable() {
		
		ArrayList<StatusTableModel> testTable = new ArrayList<>();
		String operatingSystem = System.getProperty("os.name");
		
		StatusTableModel testModel = new StatusTableModel();
		testModel.setName("Default");
		testModel.setHost("127.0.0.1");
		testModel.setPort("ICMP");
		testModel.setOS(operatingSystem);
		testModel.setDescription("Application to host status.cdero.com");
		testModel.setEnabled("true");
		testModel.setStatus(true);
		
		testTable.add(testModel);
		
		return testTable;
		
	}
	
}
