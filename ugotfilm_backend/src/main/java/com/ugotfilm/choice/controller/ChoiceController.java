package com.ugotfilm.choice.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RestController;

import com.ugotfilm.data.dto.MovieDTO;

/* 생각해볼 수 있는 예시
 * 
 * 로그인한 유저의 나이, 성별을 이용하여 추천하는 방법, 
ex) 20대 남자 회원이 로그인한 경우
20대 회원들이 가장 많이 본 영화 장르
남자 회원들이 가장 선호하는 감독의 작품리스트

ex)클릭한 기간도 저장되기 때문에 일정기간으로 한정하여 데이터를 뽑을 수 도 있다. (조회수 순위, 검색순위)

 
*/

@RestController
public class ChoiceController {
	
	
	public ChoiceController() {
	
	}

	@GetMapping("/movie/best")
	public Map<String, Object> listMethod() {
		Map<String, Object> map = new HashMap<>();
		List<MovieDTO> aList = new ArrayList<>();
		map.put("aList", aList);
		return map;
	}//end listMethod()

}//end class
