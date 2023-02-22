package com.ugotfilm.login.repository;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.ugotfilm.login.dto.UserDTO;

@Repository
@Mapper
public interface IndexRepository {
	//회원가입
	void saveUser(UserDTO user);
	
	//로그인(로그아웃은 프론트에서 처리)
	UserDTO getUserAccount(String username);
	
	//정보 수정
	
	//탈퇴(유저의 권한을 수정하는 형태)
	
	//아이디 중복 체크
	
	//닉네임 중복 체크
}
