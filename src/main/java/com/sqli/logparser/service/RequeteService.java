package com.sqli.logparser.service;

import java.util.List;

import com.sqli.logparser.model.Demande;
import com.sqli.logparser.model.Requete;

public interface RequeteService {

	public Requete ajouter(Requete r);

	public void ajouter(List<Requete> r);


}
