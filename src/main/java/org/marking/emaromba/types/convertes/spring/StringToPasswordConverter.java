package org.marking.emaromba.types.convertes.spring;

import org.marking.emaromba.types.Password;
import org.springframework.core.convert.converter.Converter;

public class StringToPasswordConverter implements Converter<String, Password> {
	
	@Override
	public Password convert(String secret) {
		return Password.of(secret);
	}
	
}
