package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.dao.MovieDAO;
import com.example.dto.MovieDTO;
import com.example.service.MovieService;

//@CrossOrigin(origins = { "http://localhost:3000" })
@CrossOrigin(origins = "*")
@RestController
public class TestController {

	@Autowired
	private MovieService movieService;

	public TestController() {

	}

	@GetMapping("/movie")
	public List<MovieDTO> getList() throws Exception {
		System.out.println("all");
		return movieService.list();
	}
	
	@GetMapping("/test")
	public String test() {
		System.out.println("테스트");
		return "테스트";
	}

}
