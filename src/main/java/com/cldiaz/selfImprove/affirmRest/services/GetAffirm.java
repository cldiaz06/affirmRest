package com.cldiaz.selfImprove.affirmRest.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cldiaz.selfImprove.affirmRest.Interfaces.GetAffirmRestService;
import com.cldiaz.selfImprove.affirmRest.models.AffirmResponse;

@Service("primary")
public class GetAffirm implements GetAffirmRestService {
	
	@Value("${affirmRest.url}")
	private String url;
	
	private AffirmResponse response;
	
	@Override
	public AffirmResponse getAffirmRestApi() {
		
		RestTemplate rest = new RestTemplate();
		
		return response = rest.getForObject(url, AffirmResponse.class);
	}


}
