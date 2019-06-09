package com.cldiaz.selfImprove.affirmRest.services;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cldiaz.selfImprove.affirmRest.Interfaces.GetAffirmRestService;
import com.cldiaz.selfImprove.affirmRest.models.AffirmResponse;

@Service("Affirm_HC")
public class GetAffirm_HC implements GetAffirmRestService {

	@Override
	public AffirmResponse getAffirmRestApi() {
		// TODO Auto-generated method stub
		
		RestTemplate rest = new RestTemplate();
		AffirmResponse response = new AffirmResponse();
		
		return response = rest.getForObject("https://quotes.rest/qod", AffirmResponse.class);
	}

}
