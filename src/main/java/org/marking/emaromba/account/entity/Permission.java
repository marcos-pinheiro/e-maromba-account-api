package org.marking.emaromba.account.entity;

import java.io.Serializable;

/**
 * @author Marcos Pinheiro
 * @since 0.0.1
 *
 */
public enum Permission implements Serializable {
	
	//Account
	INACTIVE_OTHER_ACCOUNT,
	MODIFY_OTHER_ACCOUNT,
	
	//Site
	NAGIVATE_IN_SITE, 
	SHOPPING_CART,
	BUY,
		
	//Product
	POST_PRODUCT,
	UPDATE_PRODUCT,
	
	//Order
	CANCEL_ORDER,
}