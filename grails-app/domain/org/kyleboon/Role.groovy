package org.kyleboon

class Role {

    public final static String ROLE_NORMAL = "ROLE_NORMAL"

    String authority

	static mapping = {
		cache true
	}

	static constraints = {
		authority blank: false, unique: true
	}
}
