package com.prominent.jobportal.dao;

import com.prominent.jobportal.entity.Role;

public interface RoleDao {

	public Role findRoleByName(String theRoleName);
	
}
