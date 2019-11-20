package com.sqli.logparser.service;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;

import com.sqli.logparser.dto.Ligne;
import com.sqli.logparser.model.Fichier;

public interface LogParser {

	public void parser(File f, Fichier fichier) throws IOException, ParseException, URISyntaxException;

	public Ligne parser(String ligne , Fichier fichier) throws IOException;

	public void parserDossier(String path) throws IOException, ParseException, URISyntaxException;


}
