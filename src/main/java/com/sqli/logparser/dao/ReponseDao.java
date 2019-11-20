package com.sqli.logparser.dao;

import java.io.Serializable;

import org.springframework.data.repository.CrudRepository;

import com.sqli.logparser.model.Reponse;

public interface ReponseDao extends CrudRepository< Reponse , Serializable>  {

}
