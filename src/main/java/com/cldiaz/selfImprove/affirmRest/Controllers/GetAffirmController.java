package com.cldiaz.selfImprove.affirmRest.Controllers;

import java.io.IOException;
import java.security.GeneralSecurityException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cldiaz.selfImprove.affirmRest.Interfaces.GetAffirmRestService;
import com.cldiaz.selfImprove.affirmRest.models.AffirmResponse;
import com.cldiaz.selfImprove.affirmRest.services.GoogleCalApi;

@RestController
@RequestMapping("/rest")
public class GetAffirmController {
	
	
	private GetAffirmRestService getAffirmRestService;
	
	@Autowired
	public void setAffirmRestService(ApplicationContext context) {
		this.getAffirmRestService = (GetAffirmRestService) context.getBean("primary");
	}
	
	@Autowired
	private GoogleCalApi googleEvent;
	
	private static final Logger log = LoggerFactory.getLogger(GetAffirmController.class);
		
	@GetMapping(value="/getAffirm", produces="application/json")
	@ResponseBody
	public AffirmResponse getAffirmatons() {
		
		AffirmResponse affirm = getAffirmRestService.getAffirmRestApi();

		log.info(affirm.toString());
		
		return affirm;
	}
	
	@GetMapping(value="/getEvent", produces="application/json")
	public String getGmailEvents() throws IOException, GeneralSecurityException {
		googleEvent.getEvents();
		return "check console";
	}
	
	@GetMapping(value="/createEvent")
	public String createGoogleEvents() throws IOException, GeneralSecurityException{
		
		String result = null;
		
		if(!googleEvent.eventExists()) {
		
			try {
				AffirmResponse affirm = getAffirmRestService.getAffirmRestApi();
			
				result = googleEvent.createEvent(affirm);

			} catch (IOException e) {

			e.printStackTrace();
			} catch (GeneralSecurityException e) {
	
				e.printStackTrace();
			}
		} else {
			System.out.println("Affirm event already created.");
		}
			
		return result;
	}
}
