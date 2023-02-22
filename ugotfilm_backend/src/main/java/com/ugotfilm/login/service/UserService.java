package com.ugotfilm.login.service;

import java.util.List;

import org.springframework.stereotype.Service;


import com.ugotfilm.login.dto.UserDTO;

@Service
public interface UserService {
	
	public List<UserDTO> userProcess(int num) throws Exception;
}
