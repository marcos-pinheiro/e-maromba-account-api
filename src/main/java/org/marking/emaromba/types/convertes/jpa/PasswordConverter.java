package org.marking.emaromba.types.convertes.jpa;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import org.marking.emaromba.types.Password;

@Converter
public class PasswordConverter implements AttributeConverter<Password, String>{

	@Override
	public String convertToDatabaseColumn(Password password) {
		return password.toString();
	}

	@Override
	public Password convertToEntityAttribute(String data) {
		return Password.ofHashed(data);
	}
}
