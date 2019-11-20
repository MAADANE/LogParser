package com.sqli.logparser.dto;

import java.util.ArrayList;
import java.util.List;

import com.sqli.logparser.model.Demande;

public class LigneDatatable {

	public String draw;
	public int recordsTotal;
	public int recordsFiltered;

	public List<Demande> data = new ArrayList<Demande>();

}
