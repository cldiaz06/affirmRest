package com.cldiaz.selfImprove.affirmRest.models;

import java.util.ArrayList;
import java.util.Date;

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
public class Content {
	private ArrayList<Quote> quotes;
	private String copyRight;
}
