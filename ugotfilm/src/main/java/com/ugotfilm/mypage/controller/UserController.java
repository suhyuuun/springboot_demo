package com.ugotfilm.mypage.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RestController;

import com.ugotfilm.data.dto.MovieDTO;
import com.ugotfilm.mypage.repository.UserRepository;

@RestController
public class UserController {
	
	@Autowired
	private UserRepository UserRepository;
	
	public UserController() {
	
	}

	@GetMapping("/mypage/favorite")
	public Map<String, Object> listMethod() {
		Map<String, Object> map = new HashMap<>();
		List<MovieDTO> aList = new ArrayList<>();
		map.put("aList", aList);
		return map;
	}//end listMethod()

}//end class
