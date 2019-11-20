package com.sqli.logparser.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LigneRequete extends Ligne {

	private String remote;
	private String methode;
	private String uri;
	private String body;

	@Override
	public Boolean validerLigne() {

		if (!this.DateValid(this.getDate(), "yyyy-MM-dd HH:mm:ss") || this.getRemote().equals(null)
				|| !this.getMethode().matches("GET|HEAD|POST|PUT|PATCH|DELETE|OPTIONS|TRACE")
				|| this.getUtilisateur().equals(null) || this.getUri().equals(null)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return super.toString() + "Requete [remote=" + remote + ", methode=" + methode + ", uri=" + uri + "]";
	}

}
