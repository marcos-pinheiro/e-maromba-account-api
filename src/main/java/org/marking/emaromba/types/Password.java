package org.marking.emaromba.types;

import static java.util.Objects.requireNonNull;

import java.io.Serializable;

import org.marking.emaromba.types.convertes.json.PasswordDeserialize;
import org.mindrot.jbcrypt.BCrypt;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;


/**
 * @author Marcos Pinheiro
 * @since 0.0.1
 *
 */
@JsonDeserialize(using = PasswordDeserialize.class) //JACKSON
public final class Password implements Serializable {

	private static final int WORKLOAD = 0xC;
	
	private final String hashedSecret;
	
	private Password(String hashedSecret) {
		this.hashedSecret = hashedSecret;
	}
	
	
	public static final Password of(String secret) {
		requireNonNull(secret, "Secret is null");
		return new Password(hashPassword(secret));
	}
	
	public static final Password ofHashed(String hashedSecret) {
		requireNonNull(hashedSecret, "Hashed Secret is null");
		return new Password(hashedSecret);
	}
	
	public boolean isEquals(String secret) {
		return check(secret, this.hashedSecret);
	}
	
	
	public static String hashPassword(String secret) {
		return BCrypt.hashpw(secret, BCrypt.gensalt(WORKLOAD));
	}
	
	public static boolean check(String secret, String hashedPassword) {
		
		if(null == hashedPassword || ! hashedPassword.startsWith("$2a$")) {
			throw new IllegalArgumentException("Invalid hash provided for comparison");
		}
		
		return BCrypt.checkpw(secret, hashedPassword);
	}
	
	
	@Override
	public boolean equals(Object obj) {
		
		if(obj == null) return false;
		if(! (obj instanceof String)) return false;
		
		return ((Password)obj).isEquals(this.toString());
	}
	
	@Override
	public int hashCode() {
		return hashedSecret.hashCode();
	}
	
	@Override
	public String toString() {
		return hashedSecret;
	}
	
	
	private static final long serialVersionUID = -6508627189492585387L;
}
