
package com.sqli.logparser.service;

import java.util.Date;
import java.util.List;

import com.sqli.logparser.model.Fichier;

public interface FichierService {

	public Fichier ajouter(Fichier f);

	public Fichier fichierexiste(String nom);

	public void modifier(Date dateModification);

	public void ajouter(List<Fichier> d);
}
