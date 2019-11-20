package com.sqli.logparser.dto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public abstract class Ligne {

	private String date;
	private String thread;
	private String correlation;
	private String protocole;
	private String headers;
	private String origine;
	private Integer utilisateur;

	public abstract Boolean validerLigne(

	);

	public boolean DateValid(String date, String dateFromat) {

		if (date == null) {
			return false;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(dateFromat);
		sdf.setLenient(false);

		try {

			// if not valid, it will throw ParseException
			Date dat = sdf.parse(date);
		} catch (ParseException e) {

			e.printStackTrace();
			return false;
		}
		return true;
	}

}
