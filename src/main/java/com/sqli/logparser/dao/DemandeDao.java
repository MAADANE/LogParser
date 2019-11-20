package com.sqli.logparser.dao;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;

import com.sqli.logparser.model.Demande;
import com.sqli.logparser.model.Reponse;

/**
 * 
 * @author sqli
 *
 */
public interface DemandeDao extends CrudRepository<Demande, Serializable> {
	/**
	 * 
	 * une methode qui permet de  modifier une demande on se basant sur la correlation
	 * 
	 * @param status           status de la demande
	 * @param duree            durée de la demande
	 * @param dateModification date de modification de la demande
	 * @param reponse          réponse de la demande
	 * @param correlation      correlation de la demande
	 */
	@Modifying
	@Query("update Demande   set status =:status, duree =:duree, dateModification=:DateModification, reponse =:reponse   where correlation =:correlation")
	public void modifier(@Param("status") HttpStatus status, @Param("duree") int duree,
			@Param("DateModification") Date dateModification, @Param("reponse") Reponse reponse,
			@Param("correlation") String correlation);

	/**
	 * une methode qui retourne une liste des status sans doublons
	 * 
	 * @return liste des status de demande
	 */
	@Query("select DISTINCT status from Demande where status != NULL")
	public List<String> getStatus();

	/**
	 * 
	 * une methode qui retourne une liste des methodes sans doublons
	 * 
	 * @return liste des methodes
	 */
	@Query("select DISTINCT methode from Demande")
	public List<String> getMethode();

	/**
	 * une methode qui retourne un objet demande on se basant sur la correlation
	 * 
	 * @param correlation de la demande
	 * @return objet demande
	 */
	@Query("from Demande where correlation=:correlation")
	public Demande getDemande(@Param("correlation") String correlation);

}
