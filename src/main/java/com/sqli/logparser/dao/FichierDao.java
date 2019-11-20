package com.sqli.logparser.dao;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.sqli.logparser.model.Fichier;

/**
 * 
 * 
 * @author sqli
 *
 */
public interface FichierDao extends CrudRepository<Fichier, Serializable> {
	/**
	 * 
	 * 
	 * methode qui retourne un fichier on se basant sur son nom
	 * 
	 * @param nom du fichier
	 * @return
	 */
	@Query("from Fichier where nom=:nom")
	public Fichier getFichier(@Param("nom") String nom);

	/**
	 * 
	 * 
	 * methode qui permet de  modifier la date de modification d'un fichier
	 * 
	 * @param dateModification du fichier
	 */
	@Modifying
	@Query("update Fichier set dateModification=:DateModification")
	public void modifier(@Param("DateModification") Date dateModification);
}
