package com.ugotfilm.data.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.ugotfilm.data.dto.MovieDTO;
import com.ugotfilm.data.dto.PersonDTO;

@Repository
@Mapper
public interface DataDao {

	// 영화 정보 있는지 확인
	int existMovie(int moviecode);

	// 인물 정보 있는지 확인
	int existPerson(int personcode);
	
	// 영화 정보 저장
	int saveMovie(MovieDTO data);
	
	// 인물 정보 저장
	int savePerson(PersonDTO data);
	
	// 영화 선호 정보 저장
	int choiceMovie(int usercode, int moviecode);
	
	// 영화 선호 정보 저장
	int choicePerson(int usercode, int personcode);	
	
}
