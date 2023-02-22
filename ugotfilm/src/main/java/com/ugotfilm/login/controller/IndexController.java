package com.ugotfilm.login.controller;

import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ugotfilm.security.service.PrincipalDetails;
import com.ugotfilm.login.dto.User;
import com.ugotfilm.login.repository.IndexRepository;

//@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
public class IndexController {

	@Autowired
	private BCryptPasswordEncoder encodePassword;

	@Autowired
	private IndexRepository UserRepository;

	public IndexController() {
		// TODO Auto-generated constructor stub
	}

	@PostMapping("/join")
	public String join(@RequestBody User user) {
		System.out.println(user.getPassword());
		user.setPassword(encodePassword.encode(user.getPassword()));
		UserRepository.saveUser(user);
		return "회원가입 완료";
	}

}
