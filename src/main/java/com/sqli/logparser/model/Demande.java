package com.sqli.logparser.model;
// Generated 22 juil. 2019 11:41:41 by Hibernate Tools 5.4.3.Final

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Demande generated by hbm2java
 * 
 * @param <dateModification>
 */
@Entity
@Data
@NoArgsConstructor
public class Demande {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotNull
	private java.sql.Timestamp date;
	private String thread;
	@NotNull
	private String correlation;
	@NotNull
	private String protocole;
	@NotNull
	@Enumerated(EnumType.STRING)
	private HttpMethod methode;
	@NotNull
	@Type(type = "text")
	private String uri;

	private Integer duree;
	@NotNull
	private String remote;
	@Enumerated(EnumType.STRING)
	private HttpStatus status;
	@Column(name = "DateCreation")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateCreation;
	@Column(name = "DateModification")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateModification;
	@OneToOne
	@JoinColumn(nullable=false)
	private Reponse reponse;

	@ManyToOne
	@JsonManagedReference
	private Utilisateur utilisateur;

	@ManyToOne
	@JsonManagedReference
	private Fichier fichier;

	@OneToOne
	@JoinColumn(nullable=false)
	private Requete requete;

}
