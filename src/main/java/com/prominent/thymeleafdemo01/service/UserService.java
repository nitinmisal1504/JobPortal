package com.prominent.thymeleafdemo01.service;


import com.prominent.thymeleafdemo01.entity.User;
import com.prominent.thymeleafdemo01.user.CrmUser;
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
