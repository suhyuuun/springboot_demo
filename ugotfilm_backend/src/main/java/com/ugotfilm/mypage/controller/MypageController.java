package com.ugotfilm.mypage.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ugotfilm.data.dto.GenreDTO;
import com.ugotfilm.data.dto.MovieDTO;
import com.ugotfilm.mypage.service.MypageService;

@RestController
public class MypageController {
	
	@Autowired
	private MypageService service;
	
	public MypageController() {
	
	}

	//sample
	@GetMapping("/mypage/favorite")
	public Map<String, Object> listMethod() {
		Map<String, Object> map = new HashMap<>();
		List<MovieDTO> aList = new ArrayList<>();
		map.put("aList", aList);
		return map;
	}//end listMethod()

	//선호 배우 리스트
	
	//선호 감독 리스트
	
	//선호 장르 리스트
	
	//기타
	
	//워드클라우드용 테스트
	@GetMapping("/mypage/wordcloud/{usercode}")
	public Map<String, Object> wordcloudMethod(@PathVariable("usercode") int usercode) {
		Map<String, Object> map = new HashMap<>();
		List<GenreDTO> aList = service.wordcloudProcess(usercode);
		System.out.println(aList.toString());
		map.put("aList", aList);
		return map;
	}//end listMethod()
}//end class
