package com.prominent.thymeleafdemo01.dao;

import com.prominent.thymeleafdemo01.entity.Role;

public interface RoleDao {

	public Role findRoleByName(String theRoleName);
	
}
