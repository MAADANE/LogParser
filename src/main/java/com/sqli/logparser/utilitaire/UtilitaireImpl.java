package com.sqli.logparser.utilitaire;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.Paths;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

@Component
public class UtilitaireImpl implements Utilitaire {

	@Override
	public List<File> extraireFichiers(String path) throws IOException {
		// TODO Auto-generated method stub
		return Files.walk(Paths.get(path)).filter(Files::isRegularFile).map(Path::toFile).collect(Collectors.toList());
	}

	@Override
	public boolean verifierExtension(File f) {
		// TODO Auto-generated method stub
		PathMatcher matcher = FileSystems.getDefault().getPathMatcher("glob:**/*.{log}");
		if (matcher.matches(f.toPath())) {
			return true;
		}
		return false;
	}

	@Override
	public Timestamp getDate(String date) {
		// TODO Auto-generated method stub
		return Timestamp.valueOf(date);
	}

	@Override
	public Time getTime(String time) {
		// TODO Auto-generated method stub
		return Time.valueOf(time);
	}

	@Override
	public String getURI(String uri) throws URISyntaxException {
		// TODO Auto-generated method stub
		URI u = new URI(uri);
		return u.getHost() + u.getPath();
	}

	@Override
	public String getDate(Date date) {
		// TODO Auto-generated method stub
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return f.format(date);

	}

}
