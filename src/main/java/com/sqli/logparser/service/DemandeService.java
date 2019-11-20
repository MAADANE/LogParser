package com.sqli.logparser.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;

import com.sqli.logparser.model.Demande;
import com.sqli.logparser.model.Reponse;

public interface DemandeService {

	public List<Demande> getAll();

	public void modifier(HttpStatus status, int duree, Date dateModification, Reponse reponse, String correlation);

	public List<Demande> getDemandeByCondition(String sql);

	public List<String> getStatus();

	public List<String> getMethode();

	public Demande getDemande(List<Demande> list, String correlation);

	public int DemandeCount(String sql);

	public Optional<Demande> getDemandeById(int id);

	public void ajouter(Demande d);

	public void ajouter(List<Demande> d);

	public void verifierDemande(List<Demande> d);

	public Demande getDemande(String correlation);
}
