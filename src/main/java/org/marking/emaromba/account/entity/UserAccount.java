package org.marking.emaromba.account.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author Marcos Pinheiro
 * @since 0.0.1
 *
 */
@Entity @Table(name = "account")
public class UserAccount implements Serializable {
	

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@OneToOne(cascade = CascadeType.ALL)
	private User user;
	 
	@OneToOne(cascade = CascadeType.ALL)
	private Roles roles;
	
	private boolean inactive;
	
	
	UserAccount() {
	}
	
	private UserAccount(final User user, final Roles roles) {
		this.user = user;
		this.roles = roles;
	}
	
	private UserAccount(final User user, final Role role) {
		this.user = user;
		this.roles = new Roles(role);
	}
	
	public static final UserAccount of(final User user, final Role role) {
		return new UserAccount(user, role);
	}
	
	public static final UserAccount ofCustomRoles(final User user, final Roles roles) {
		return new UserAccount(user, roles);
	}
	
	
	public boolean userIsAdmin() {
		return roles.isAdmin();
	}

	public void changeRole(Role role) {
		this.roles = new Roles(role);
	}
	
	public void changeRoleWithSamePermissions(Role role) {
		this.roles = new Roles(role);
	}
	
	public boolean isInactive() {
		return inactive;
	}
	
	public void activate() {
		this.inactive = false;
	}
	
	public void inactivate() {
		this.inactive = true;
	}
	
	
	public User getUser() {
		return user;
	}
	
	public Roles getRoles() {
		return roles;
	}
	
	public long getId() {
		return id;
	}
	
	private static final long serialVersionUID = 1L;
}
