package com.ugotfilm.data.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ugotfilm.data.dto.GenreDTO;
import com.ugotfilm.data.dto.MovieDTO;
import com.ugotfilm.data.dto.CastDTO;
import com.ugotfilm.data.dto.CrewDTO;

@Mapper
@Repository
public interface DataDao {
	
	// 영화 정보 저장
	int saveMovie(MovieDTO data);
	
	// 감독 정보 저장
	int saveCrew(CrewDTO data);
	
	// 배우 정보 저장
	public List<CastDTO> saveCast();
	
	// 장르 정보 저장
	public List<GenreDTO> saveGenre();
	
	
	// 영화 선호 정보 저장
	int choiceMovie(@Param("usercode")int usercode, @Param("moviecode")int moviecode);
	
	// 배우 선호 정보 저장
	int choiceCast(int usercode, int personcode);
	
	// 감독 선호 정보 저장
	int choiceCrew(int usercode, int personcode);	

	// 장르 선호 정보 저장
	int choiceGenre(int usercode, int genrecode);	
}
