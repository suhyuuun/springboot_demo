package com.ugotfilm.data.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ugotfilm.data.dao.DataDao;
import com.ugotfilm.data.dto.MovieDTO;
import com.ugotfilm.data.dto.PersonDTO;

@RestController
public class DataController {
	
	@Autowired
	private DataDao DataDao;
	
	public DataController() {
	
	}

	@PostMapping("/save/movie")
	public String saveMovieMethod(@RequestBody MovieDTO data) {
		DataDao.saveMovie(data);
		return "영화정보 추가 완료";
	}//end listMethod()

	@PostMapping("/save/person")
	public String savePersonMethod(@RequestBody PersonDTO data) {
		DataDao.savePerson(data);
		return "인물정보 추가 완료";
	}//end listMethod()
	
	@GetMapping("/save/moviechoice/{usercode}/{moviecode}")
	public String movieChoiceMethod(@PathVariable("usercode") int usercode,@PathVariable("moviecode") int moviecode) {
		DataDao.choiceMovie(usercode, moviecode);
		return "유저 클릭 영화 저장 완료";
	}//end listMethod()
	
	@GetMapping("/save/personchoice/{usercode}/{personcode}")
	public String personChoiceMethod(@PathVariable("usercode")int usercode, @PathVariable("personcode")int personcode) {
		DataDao.choicePerson(usercode, personcode);
		return "유저 클릭 영화 저장 완료";
	}//end listMethod()
}//end class
