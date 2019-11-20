package com.sqli.logparser.dao;

import java.io.Serializable;

import org.springframework.data.repository.CrudRepository;

import com.sqli.logparser.model.Requete;

public interface RequeteDao extends CrudRepository< Requete , Serializable> {

}
