package com.cldiaz.selfImprove.affirmRest.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/rest")
public class GetAffirmController {

	@GetMapping("/getAffirm")
	@ResponseBody
	public String getAffirmatons() {
		
		RestTemplate restTemp = new RestTemplate();
		
		
		
		return "done";
	}
	
}
