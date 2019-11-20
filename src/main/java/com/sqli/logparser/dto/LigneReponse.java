package com.sqli.logparser.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LigneReponse extends Ligne {
	
	private String daterep;
	private String body;
	private Integer duree;
	private Integer status;

	@Override
	public Boolean validerLigne() {

		if (!this.DateValid(this.getDate(), "yyyy-MM-dd HH:mm:ss") || this.getDuree().equals(null)
				|| this.getStatus().equals(null) || this.getUtilisateur().equals(null)) {
			return false;
		}
		return true;
	}

}
