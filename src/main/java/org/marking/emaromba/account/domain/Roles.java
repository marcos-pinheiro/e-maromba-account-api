package org.marking.emaromba.account.domain;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Table;

import com.google.common.collect.ImmutableSet;

import lombok.EqualsAndHashCode;

/**
 * @author Marcos Pinheiro
 * @since 0.0.1
 *
 */
@Entity @Table(name = "role") @EqualsAndHashCode
public final class Roles implements Serializable {	

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Enumerated(EnumType.STRING) @Column(name = "name")
	private final Role role;
	
	@ElementCollection(fetch = FetchType.EAGER) @JoinTable(name = "permission", joinColumns = @JoinColumn(name = "id"))
	@Enumerated(EnumType.STRING) @Column(name = "name")
	private Set<Permission> permissions;
	
	Roles() {
		this(null, null);
	}
	
	public Roles(Role role, Set<Permission> permissions) {
		this.role = role;
		this.permissions = permissions;
	}
	
	public Roles(Role role) {
		this.role = role;
		this.permissions = role.getDefaultPermissions();
	}
	
	
	public boolean isAdmin() {
		return permissions.equals(Role.ADMIN.getDefaultPermissions());
	}
	
	public boolean havePermissionFor(Permission permission) {
		return permissions.stream().filter(p -> p == permission).findFirst().isPresent();
	}
	
	void reset() {
		permissions = role.getDefaultPermissions();
	}
	
	public void addPermission(Permission permission) {
		this.permissions.add(permission);
	}
	
	public Iterable<Permission> getPermissions() {
		return listPermissions();
	}
	
	public Set<Permission> listPermissions() {
		
		if(collectionIsNullOrEmpty(permissions)) {
			this.reset();
			return permissions;
		}
		
		return ImmutableSet.copyOf(permissions);
	}
	
	public Role getRole() {
		return role;
	}
	
	
	private static final boolean collectionIsNullOrEmpty(Collection<?> collection) {
		return collection == null || collection.isEmpty();
	}
	
	private static final long serialVersionUID = 1L;
}
