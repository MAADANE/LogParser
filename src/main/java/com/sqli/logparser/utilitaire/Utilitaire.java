package com.sqli.logparser.utilitaire;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

public interface Utilitaire {

	public List<File> extraireFichiers(String path) throws IOException;

	public boolean verifierExtension(File f);

	public Timestamp getDate(String date);

	public Time getTime(String time);

	public String getURI(String uri) throws URISyntaxException;

	public String getDate(java.util.Date date);

}
