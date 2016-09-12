package org.marking.util.convertes.spring;

import org.marking.util.types.Password;
import org.springframework.core.convert.converter.Converter;

public class StringToPasswordConverter implements Converter<String, Password> {
	
	@Override
	public Password convert(String secret) {
		return Password.of(secret);
	}
	
}
