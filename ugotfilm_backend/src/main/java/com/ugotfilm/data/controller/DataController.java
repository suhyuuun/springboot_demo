package com.ugotfilm.data.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ugotfilm.data.dto.GenreDTO;
import com.ugotfilm.data.dto.MovieDTO;
import com.ugotfilm.data.dto.CastDTO;
import com.ugotfilm.data.dto.CrewDTO;
import com.ugotfilm.data.service.DataService;

@CrossOrigin(origins = "*")
@RestController
public class DataController {

	@Autowired
	private DataService service;

	public DataController() {

	}

	@PostMapping("/save/movie")
	public String saveMovieMethod(@RequestBody MovieDTO data) {
		System.out.println("---------------movie--------------------");
		System.out.println("moviecode: " + data.getMoviecode());
		System.out.println("title: " + data.getTitle());
		System.out.println("poster_path: " + data.getPoster_url());
		service.saveMovieProcess(data);
		return "영화정보 추가 완료";
	}// end listMethod()

	@PostMapping("/save/crew")
	public String saveCrewMethod(@RequestBody CrewDTO data) {
		System.out.println("---------------crew--------------------");
		System.out.println("crewcode: " + data.getPersoncode());
		System.out.println("name: " + data.getName());
		System.out.println("profile_path: " + data.getProfile_url());
		service.saveCrewProcess(data);
		return "감독정보 추가 완료";
	}// end listMethod()
	

	@PostMapping("/save/cast")
	public String saveCastMethod(@RequestBody CastDTO data) {
		System.out.println("---------------cast--------------------");
		System.out.println("castcode: " + data.getPersoncode());
		System.out.println("name: " + data.getName());
		System.out.println("profile_path: " + data.getProfile_url());
//		int res = service.saveMovieProcess(data);
		return "배우정보 추가 완료";
	}// end listMethod()
	

	@PostMapping("/save/moviechoice")
	public String choiceMovieMethod(@RequestParam(required = false) int usercode, @RequestParam(required = false) int moviecode) {
		service.choiceMovieProcess(usercode, moviecode);
		System.out.println("---------------moviechoice--------------------");
		System.out.println("usercode: " + usercode);
		System.out.println("moviecode: " + moviecode);
		return "영화취향 저장 완료";
	}// end listMethod()
	
	@GetMapping("/save/crewchoice")
	public String choicecrewMethod(@RequestParam(required = false) int usercode, @RequestParam(required = false) int personcode) {
		System.out.println("---------------crewchoice--------------------");
		System.out.println("usercode: " + usercode);
		System.out.println("personcode: " + personcode);
//		service.choiceCrewProcess(usercode, personcode);
		return "감독취향 저장 완료";
	}// end listMethod()
	
	@GetMapping("/save/castchoice/{usercode}/{castcode}")
	public String choiceCastMethod(@PathVariable("usercode") int usercode,
			@PathVariable("personcode") int personcode) {
		service.choiceCastProcess(usercode, personcode);                                                                                                                                                                                                                                                                                                                    
		return "유저 클릭 영화 저장 완료";
	}// end listMethod()
	
	@GetMapping("/save/genrechoice/{usercode}/{genrecode}")
	public String choiceGenreMethod(@PathVariable("usercode") int usercode, @PathVariable("genrecode") int genrecode) {
		service.choiceGenreProcess(usercode, genrecode);
		return "유저 클릭 영화 저장 완료";
	}// end listMethod()
}// end class
