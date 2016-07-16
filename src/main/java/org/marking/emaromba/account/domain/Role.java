package org.marking.emaromba.account.domain;

import static org.marking.emaromba.account.domain.Permission.*;

import java.io.Serializable;
import java.util.Set;
import java.util.function.Supplier;

import com.google.common.collect.ImmutableSet;

/**
 * @author Marcos Pinheiro
 * @since 0.0.1
 *
 */
public enum Role implements Serializable {
	USER				(() -> permissions(NAGIVATE_IN_SITE, SHOPPING_CART)),
	CUSTOMER			(() -> permissions(USER, BUY, CANCEL_ORDER)),
	EMPLOYEE_LEVEL_1	(() -> permissions(INACTIVE_OTHER_ACCOUNT, MODIFY_OTHER_ACCOUNT, POST_PRODUCT)),
	EMPLOYEE_LEVEL_2	(() -> permissions(EMPLOYEE_LEVEL_1, UPDATE_PRODUCT)),
	MANAGER				(() -> permissions(EMPLOYEE_LEVEL_2)),
	DIRECTOR			(() -> permissions(MANAGER)),
	ADMIN				(() -> permissions(Permission.values()));
	
	
	private final Supplier<Set<Permission>> defaultPermissions;
	
	private Role(Supplier<Set<Permission>> defaultPermissions) {
		this.defaultPermissions = defaultPermissions;
	}
	
	
	Set<Permission> getDefaultPermissions() {
		return defaultPermissions.get();
	}
	
	private static Set<Permission> permissions(Permission... actions) {
		return ImmutableSet.copyOf(actions);
	}
	
	private static Set<Permission> permissions(Role role, Permission... actions) {
		return ImmutableSet.copyOf(role.getDefaultPermissions().toArray(actions));
	}
}
