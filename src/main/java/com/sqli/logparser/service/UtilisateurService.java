package com.sqli.logparser.service;

import java.util.List;

import com.sqli.logparser.model.Utilisateur;

public interface UtilisateurService {

	public Utilisateur ajouter(Utilisateur u);

	public Utilisateur getUtilisateur(int code);

	public void ajouter(List<Utilisateur> u);

}
