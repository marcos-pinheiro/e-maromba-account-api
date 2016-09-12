package org.marking.util.convertes.jpa;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import org.marking.util.types.Password;

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
