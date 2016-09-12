package org.marking.util.convertes.json;

import java.io.IOException;

import org.marking.util.types.Password;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class PasswordDeserialize extends JsonDeserializer<Password> {

	@Override
	public Password deserialize(JsonParser parser, DeserializationContext context) throws IOException, JsonProcessingException {
		return Password.of(parser.getText());
	}

}
