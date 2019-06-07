package com.cldiaz.selfImprove.affirmRest.Controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.cldiaz.selfImprove.affirmRest.models.Content;
import com.cldiaz.selfImprove.affirmRest.models.RestApiReponse;

@RestController
@RequestMapping("/rest")
public class GetAffirmController {

	private static final Logger log = LoggerFactory.getLogger(GetAffirmController.class);
	
	@GetMapping(value="/getAffirm", produces="application/json")
	@ResponseBody
	public RestApiReponse getAffirmatons() {
		
		RestTemplate restTemp = new RestTemplate();
		
		RestApiReponse affirm = restTemp.getForObject("https://quotes.rest/qod", RestApiReponse.class);

		log.info(affirm.toString());
		
		return affirm;
	}
	
}
