package com.cldiaz.selfImprove.affirmRest.Controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.cldiaz.selfImprove.affirmRest.Interfaces.GetAffirmRestService;
import com.cldiaz.selfImprove.affirmRest.models.AffirmResponse;

@RestController
@RequestMapping("/rest")
public class GetAffirmController {
	
	
	private GetAffirmRestService getAffirmRestService;
	
	@Autowired
	public void setAffirmRestService(ApplicationContext context) {
		this.getAffirmRestService = (GetAffirmRestService) context.getBean("primary");
	}
	
	private static final Logger log = LoggerFactory.getLogger(GetAffirmController.class);
		
	@GetMapping(value="/getAffirm", produces="application/json")
	@ResponseBody
	public AffirmResponse getAffirmatons() {
		
		AffirmResponse affirm = getAffirmRestService.getAffirmRestApi();

		log.info(affirm.toString());
		
		return affirm;
	}
	
}
