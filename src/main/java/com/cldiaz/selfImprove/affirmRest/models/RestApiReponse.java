package com.cldiaz.selfImprove.affirmRest.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RestApiReponse {
	
	private SuccessRequest success;
	private Content contents;
}
