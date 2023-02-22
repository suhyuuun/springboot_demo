package com.ugotfilm.login.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ugotfilm.login.dao.UserDAO;
import com.ugotfilm.login.dto.UserDTO;

@Service
public class UserServiceImp implements UserService{
	
	@Autowired
	private UserDAO userDao;
	
	public UserServiceImp() {
	}

	@Override
	public List<UserDTO> userProcess(int num) throws Exception {
		return userDao.userList(num);
	}

	

}
