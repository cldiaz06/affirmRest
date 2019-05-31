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
public class Quotes {
	private String quote;
	private String author;
	private int length;
	private ArrayList<String> tags;
	private String category;
	private String title;
	private Date postDate;
	private int id;
}
