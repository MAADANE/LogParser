package com.sqli.logparser.model;
// Generated 22 juil. 2019 11:41:41 by Hibernate Tools 5.4.3.Final

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Utilisateur generated by hbm2java
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Utilisateur implements java.io.Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(unique = true)
	private Integer code;

	@OneToMany(mappedBy = "utilisateur")
	@JsonBackReference
	List<Demande> listDemandes;
}