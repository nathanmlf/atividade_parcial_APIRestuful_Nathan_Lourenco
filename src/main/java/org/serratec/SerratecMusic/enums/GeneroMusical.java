package org.serratec.SerratecMusic.enums;

import org.serratec.SerratecMusic.exception.EnumValidationException;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum GeneroMusical {
	ROCK, POP, SAMBA, FUNK, SERTANEJO;
	
	@JsonCreator
	public static GeneroMusical verificaEnum(String value) throws EnumValidationException {

		for (GeneroMusical genero: values()) {
			if (value.equals(genero.name())) {
				return genero;
			}
		}

		throw new EnumValidationException("Gênero musical inválido. Valores válidos: ROCK, POP, SAMBA, FUNK, SERTANEJO.");
	}
}
