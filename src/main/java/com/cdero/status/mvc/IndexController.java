package com.cdero.status.mvc;

/**
 * @author 	Christopher DeRoche
 * @version	0.0.1
 * @since	0.0.1
 * 
 */

import java.util.ArrayList;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {
	
	private ArrayList<StatusTableModel> statusTableData;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	protected String index() {
		
		return "index";
		
	}
	
}
