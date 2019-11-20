package com.sqli.logparser.dao;

import java.io.Serializable;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.sqli.logparser.model.Utilisateur;

public interface UtilisateurDao extends CrudRepository<Utilisateur, Serializable> {

	@Query("from Utilisateur where code=:code")
	public Utilisateur getUtilisateur(@Param("code") int code);

}
