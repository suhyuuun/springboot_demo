package com.example.login.repository;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.example.login.dto.User;

@Repository
@Mapper
public interface UserRepository {
	void saveUser(User user);
	
	User getUserAccount(String username);
}
