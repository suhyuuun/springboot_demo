package com.ugotfilm.login.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.ugotfilm.login.dto.UserDTO;

@Mapper
@Repository
public interface UserDAO {
	
	List<UserDTO> userList(int usercode)throws Exception;
}
