package com.cldiaz.selfImprove.affirmRest.Interfaces;

import org.springframework.web.client.RestTemplate;

import com.cldiaz.selfImprove.affirmRest.models.AffirmResponse;

public interface GetAffirmRestService {
	
	public AffirmResponse getAffirmRestApi();
}
