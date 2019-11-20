package com.sqli.logparser.service;

import java.util.List;

import com.sqli.logparser.model.Reponse;

public interface ReponseService {

	public Reponse ajouter(Reponse r);

	public void ajouter(List<Reponse> r);

}
