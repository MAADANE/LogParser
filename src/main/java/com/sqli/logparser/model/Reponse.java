package com.sqli.logparser.model;
// Generated 22 juil. 2019 11:41:41 by Hibernate Tools 5.4.3.Final

import java.sql.Time;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Reponse generated by hbm2java
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reponse implements java.io.Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotNull
	private String origine;
	@NotNull
	private java.sql.Timestamp date;
	@NotNull
	@Type(type = "text")
	private String headers;
	@Type(type = "text")
	private String body;
	@Transient
	private String correlation;
}