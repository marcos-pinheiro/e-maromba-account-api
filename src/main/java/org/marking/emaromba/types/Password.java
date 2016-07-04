package org.marking.emaromba.types;

import static java.util.Objects.requireNonNull;

import org.mindrot.jbcrypt.BCrypt;


/**
 * @author Marcos Pinheiro
 * @since 0.0.1
 *
 */
public final class Password {
	
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
	
	public boolean thisIsTheSameSecret(String secret) {
		return this.hashedSecret.equals(hashPassword(secret));
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
		
		return ((String)obj).equals(this.hashedSecret);
	}
	
	@Override
	public int hashCode() {
		return hashedSecret.hashCode();
	}
	
	@Override
	public String toString() {
		return hashedSecret;
	}
}
