package com.ugotfilm.login.repository;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.ugotfilm.login.dto.User;

@Repository
@Mapper
public interface IndexRepository {
	//회원가입
	void saveUser(User user);
	
	//로그인
	User getUserAccount(String username);
}
