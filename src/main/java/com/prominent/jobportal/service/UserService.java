package com.prominent.jobportal.service;


import com.prominent.jobportal.entity.User;
import com.prominent.jobportal.user.CrmUser;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

	public User findByUserName(String userName);

	public void save(CrmUser crmUser);

	public List<User> getAllUsers();

	void saveUser(User user);

	User getUserById(int id);

	User getRecordById(int id);

	void deleteRecordById(int id);
}
